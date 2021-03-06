package com.ahhtou.utils.img.zipper;

import com.ahhtou.common.utils.SimpleTools;

import java.io.File;
import java.util.regex.Pattern;

public class Utils {

    ImgZipperProperties imgZipperProperties;

    public Utils(ImgZipperProperties imgZipperProperties) {
        this.imgZipperProperties = imgZipperProperties;
    }

    // 判断是否已经有被压缩后的文件
    public boolean noZip(String url) {
        String prefix = SimpleTools.UrlUtils.getFileNameNoSuffix(url);

        String suffix = SimpleTools.UrlUtils.getSuffixName(url);

        String zipPath = prefix + imgZipperProperties.getSuffixName() + suffix;
        return !new File(zipPath).exists();


    }

    public boolean isImgUrl(String imgUrl) {
        return imgZipperProperties.getImgRegs()
                .stream()
                .anyMatch(regs -> Pattern.matches(regs, imgUrl));
    }

    public boolean noSuffixName(String imgUrl) {
        // 匹配后缀中含有_forShow的
        String reg = ".*" + imgZipperProperties.getSuffixName() + "\\..*";
        return !imgUrl.matches(reg);
    }


    public String newImageUrl(String oldUrl) {
        return SimpleTools.UrlUtils.getFileNameNoSuffix(oldUrl) + imgZipperProperties.getSuffixName() + SimpleTools.UrlUtils.getSuffixName(oldUrl);
    }

}
