package com.ahhtou.doSome.DoSomeImpl;

import com.ahhtou.doSome.DoSomeForSS;
import com.ahhtou.logger.HelloLogger;
import com.ahhtou.utils.HelloUtils;
import lombok.SneakyThrows;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class DoSearch implements DoSomeForSS {


    private HelloLogger log;
    private HelloUtils utils;
    GetThings<Set<String>> getThings = new GetThings<>();

    Set<String> things = new HashSet<>();

    @Override
    public GetThings<Set<String>> getThings() {
        return getThings;
    }

    @Override
    public DoSearch doSome(GetThings<Set<String>> getThings) {
        log = getThings.getHelloLogger();
        utils = getThings.getHelloUtils();
        getThings.getValue().forEach(this::search);
        this.getThings = getThings.set(things);
        return this;
    }

    @SneakyThrows
    private void search(String url) {
        log.logGreen("搜寻了地址 -> " + url);
        File[] temp;
        List<File> fileList;
        // 配置的 路径中得到 文件
        temp = new File(url).listFiles();

        if (temp == null) {
            log.logRed("无符合文件 -> " + url);
            return;
        }

        fileList = Arrays.asList(temp);

        // 得到文件夹
        List<File> directory = fileList.stream().filter(File::isDirectory).collect(Collectors.toList());

        // 得到文件
        List<File> files = new ArrayList<>(fileList);


        if (files.size() > directory.size()) {
            Set<String> collect = files.stream()
                    .filter(l -> !l.isDirectory())          // 去除 文件夹
                    .map(File::getPath)                     // 得到 路径
                    .map(String::toLowerCase)               // 得到 小写
                    .filter(utils::isImgUrl)                // 是否 为图片
                    .collect(Collectors.toSet());

            things.addAll(collect);

        }

        // 遍历
        if (directory.size() != 0) {
            directory.stream().map(File::getPath).forEach(this::search);
        }

    }



/*    public List<String> get() {
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
                .map(url -> this.proxyUrl.toLowerCase() + url)
                .collect(Collectors.toList());
    }*/
}
