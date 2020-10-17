package com.ahhtou.utils.img.zipper;

import lombok.Data;

import java.util.Map;

@Data
public class ImgZipper {

    ImgZipperProperties imgZipperProperties = new ImgZipperProperties();


    public boolean start() {
        try {
            this.getSearch().check();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ImgZipper create() {
        return new ImgZipper();
    }

    public Search getSearch() {
        return new Search(imgZipperProperties);
    }

    public ImgZipper setPath(String path) {
        imgZipperProperties.setSearchPath(path);
        return this;
    }

    public ImgZipper setSize(int width, int height) {
        this.imgZipperProperties.setWidth(width);
        this.imgZipperProperties.setHeight(height);
        return this;
    }

    public ImgZipper setSize(Map<String, Integer> map) {
        this.imgZipperProperties.setWidth(map.get("width"));
        this.imgZipperProperties.setHeight(map.get("height"));
        return this;
    }


}
