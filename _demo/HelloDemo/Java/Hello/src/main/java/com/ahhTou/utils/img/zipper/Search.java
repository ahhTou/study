package com.ahhTou.utils.img.zipper;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.util.Arrays;

@Data
@AllArgsConstructor
public class Search {

    ImgZipperProperties imgZipperProperties;

    public void check() {
        checkMethod(imgZipperProperties.getSearchPath());
    }

    public void check(String url) {
        checkMethod(url);
    }

    private void checkMethod(String url) {
        Utils utils = new Utils(imgZipperProperties);
        Zip zip = new Zip(imgZipperProperties);

        File[] files = new File(url).listFiles();

        if (files == null) throw new RuntimeException("该文件夹中未找到任何文件");

        Arrays.stream(files)
                .filter(File::isFile)               // 去除 非文件
                .map(File::getPath)                 // 得到 路径
                .filter(utils::isImgUrl)            // 判断 是否为图片
                .filter(utils::noSuffixName)        // 去除 zipper创建的图片
                .filter(utils::noZip)               // 去除 已经有zip的图片
                .forEach(zip::start);                 // 压缩

        Arrays.stream(files)
                .filter(File::isDirectory)      // 去除 非文件
                .map(File::getPath)             // 得到 路径
                .forEach(this::check);          // 遍历
    }

}
