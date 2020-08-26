package com.ahhTou.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Data
@ConfigurationProperties(prefix = "ahhtou.utils.bg-searcher")
public class BgSearcher {

    private List<String> imgRegs = Arrays.asList(".*.jpg", ".*.png");
    private String localUrl = "C:\\Users\\11979\\OneDrive\\图片";
    private String proxyUrl = "http://39.99.154.145/img/hello/";
    private int cycleTime = 1;


    private List<String> urls = new CopyOnWriteArrayList<>();
    private List<String> getUrls = new CopyOnWriteArrayList<>();
    private boolean isStart = false;
    private Consumer<Object> consumer = args -> {
        System.out.println("====  保存设置为空");
    };


    public boolean isImgUrl(String imgUrl) {
        return imgRegs.stream().anyMatch(regs -> Pattern.matches(regs, imgUrl));
    }

    public void search(String url) {
        File[] files = new File(url).listFiles();
        if (files == null) throw new RuntimeException("文件夹为空");

        Stream<File> stream = Arrays.stream(files);
        List<String> collect = stream
                .filter(File::isFile)
                .map(File::getPath)
                .map(String::toLowerCase)
                .filter(this::isImgUrl)
                .collect(Collectors.toList());
        urls.addAll(collect);

        stream = Arrays.stream(files);

        stream.filter(File::isDirectory).map(File::getPath).forEach(this::search);
    }

    public void start() {
        System.out.println("===== Searcher Is Started =====");
        new Thread(() -> {
            isStart = true;
            while (isStart) {
                try {
                    search(localUrl);
                    getUrls.clear();
                    getUrls.addAll(urls);
                    consumer.accept(urls);
                    urls.clear();
                    TimeUnit.SECONDS.sleep(cycleTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("========== SHUT DOWN ==========");
        }).start();
    }


    public void shutdown() {
        isStart = false;
    }

    public List<String> get() {
        return getUrls;
    }

    public List<String> getExcludeLocalPath() {
        return get().stream()
                .map(String::toLowerCase)
                .map(url -> url.replace(localUrl.toLowerCase(), ""))
                .collect(Collectors.toList());
    }

    public List<String> getProxyPath() {
        return getExcludeLocalPath().stream()
                .map(url -> proxyUrl.toLowerCase() + url)
                .map(url -> url.replaceAll("(/\\\\)|(\\\\)|(//)", "/"))
                .collect(Collectors.toList());
    }


    public BgSearcher saveSetting(Consumer<Object> setting) {
        consumer = setting;
        return this;
    }


}
