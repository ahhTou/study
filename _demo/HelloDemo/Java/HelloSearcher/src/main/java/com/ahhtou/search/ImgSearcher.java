package com.ahhtou.search;

import com.ahhtou.logger.HelloLogger;
import lombok.Data;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
public class ImgSearcher {

    private String path;


    /* New */
    private String name;
    private Set<String> paths;
    private Set<String> regs;
    private HelloLogger log;
    private String proxyUrl;


    public ImgSearcher(String name) {
        this.name = name;
    }


    /* 创建一个Searcher */
    public static ImgSearcher create(String name) {
        ImgSearcher imgSearcher = new ImgSearcher(name);
        HelloLogger hello = HelloLogger.create("Searcher", name);
        hello.log("实例被创建");
        imgSearcher.setLog(hello);
        return imgSearcher;
    }

    public ImgSearcher setPaths(Set<String> set) {
        this.paths = set;
        set.forEach(p -> log.log("设置了路径 : " + p));
        return this;
    }

    public ImgSearcher setSuffix(Set<String> set) {
        this.regs = set;
        set.forEach(r -> log.log("设置了后缀 : " + r));
        return this;
    }

    public ImgSearcher setProxyUrl(String proxy) {
        this.proxyUrl = proxy;
        log.log("设置了Proxy : " + proxy);
        return this;
    }

    private final List<String> urls = new CopyOnWriteArrayList<>();
    private final List<String> getUrls = new CopyOnWriteArrayList<>();
    private boolean isStart = false;
    private Consumer<Object> saveFuc = args -> System.out.println("Searcher -> 未进行保存");
    private BooleanSupplier startFuc = () -> false;


    public boolean isImgUrl(String imgUrl) {
        return properties.getImgRegs().stream().anyMatch(regs -> Pattern.matches(regs, imgUrl));
    }


    public void search(String url) {

        try {
            File[] TEMP;
            List<File> FILE_LIST;
            // 配置的 路径中得到 文件
            if (url != null)
                TEMP = new File(url).listFiles();
            else
                throw new RuntimeException("未填写路径");

            if (TEMP == null)
                throw new RuntimeException("没有找到任何符合条件的文件");

            FILE_LIST = Arrays.asList(TEMP);

            List<String> collect = FILE_LIST.stream()
                    .filter(File::isFile)           // 去除 非文件
                    .map(File::getPath)             // 得到 路径
                    .map(String::toLowerCase)       // 得到 小写
                    .filter(this::isImgUrl)         // 是否 为图片
                    .collect(Collectors.toList());

            urls.addAll(collect);
            FILE_LIST.stream().filter(File::isDirectory).map(File::getPath).forEach(this::search);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void start() {
        System.out.println("Searcher -> Started");
        new Thread(() -> {
            isStart = true;
            while (isStart) {
                try {
                    // 运行前执行
                    boolean asBoolean = startFuc.getAsBoolean();
                    if (asBoolean) System.out.println("Searcher -> StartFuc运行成功");
                    else System.out.println("Searcher -> StartFuc运行失败");

                    // 搜寻
                    search(path);

                    // 清楚并保存
                    getUrls.clear();
                    getUrls.addAll(urls);
                    // 存在proxyUrl的保存情况
                    if (properties.getProxyUrl() != null)
                        saveFuc.accept(this.getProxyPath());
                    else
                        saveFuc.accept(urls);

                    urls.clear();
                    // 休眠
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
                .map(url -> url.replaceAll("(/\\\\)|(\\\\)|(//)", "/"))
                .map(url -> url.replaceFirst("/", ""))
                .map(url -> properties.getProxyUrl().toLowerCase() + url)
                .collect(Collectors.toList());
    }


    public ImgSearcher setSaveFunc(Consumer<Object> setting) {
        saveFuc = setting;
        return this;
    }

    public ImgSearcher setBeforeStart(BooleanSupplier bfFuc) {
        startFuc = bfFuc;
        return this;
    }
}
