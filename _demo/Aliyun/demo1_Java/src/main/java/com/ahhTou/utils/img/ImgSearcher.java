package com.ahhTou.utils.img;

import com.ahhTou.utils.img.searcher.BgSearcherProperties;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImgSearcher {


    private String path;
    private String proxyUrl;

    BgSearcherProperties properties;

    public void setProperties(BgSearcherProperties properties) {
        this.properties = properties;
    }

    private final List<String> urls = new CopyOnWriteArrayList<>();
    private final List<String> getUrls = new CopyOnWriteArrayList<>();
    private boolean isStart = false;
    private Consumer<Object> save = args -> System.out.println("Searcher -> 未进行保存");


    public boolean isImgUrl(String imgUrl) {
        return properties.getImgRegs().stream().anyMatch(regs -> Pattern.matches(regs, imgUrl));
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
        System.out.println("Searcher -> Started");
        new Thread(() -> {
            isStart = true;
            while (isStart) {
                try {
                    search(path);
                    getUrls.clear();
                    getUrls.addAll(urls);
                    save.accept(urls);
                    urls.clear();
                    TimeUnit.MINUTES.sleep(properties.getCycleTime());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Searcher -> Shut Down");
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
                .map(url -> url.replace(path.toLowerCase(), ""))
                .collect(Collectors.toList());
    }

    public List<String> getProxyPath() {
        return getExcludeLocalPath().stream()
                .map(url -> proxyUrl.toLowerCase() + url)
                .map(url -> url.replaceAll("(/\\\\)|(\\\\)|(//)", "/"))
                .collect(Collectors.toList());
    }


    public ImgSearcher saveFunc(Consumer<Object> setting) {
        save = setting;
        return this;
    }


    public ImgSearcher create() {
        ImgSearcher imgSearcher = new ImgSearcher();
        imgSearcher.setProperties(properties);
        return imgSearcher;
    }

    public ImgSearcher setPath(String path) {
        this.path = path;
        return this;
    }

    public ImgSearcher setCycle(int time) {
        this.properties.setCycleTime(time);
        return this;
    }
}
