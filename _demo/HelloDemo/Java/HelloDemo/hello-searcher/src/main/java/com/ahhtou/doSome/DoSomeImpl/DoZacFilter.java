package com.ahhtou.doSome.DoSomeImpl;

import com.ahhtou.doSome.DoSomeForSS;
import com.ahhtou.doSome.GetThings;
import com.ahhtou.utils.UrlUtils;
import com.ahhtou.utils.ZipAndCutProperties;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

public class DoZacFilter implements DoSomeForSS {

    ZipAndCutProperties properties;

    GetThings<Set<String>> getThings = new GetThings<>();

    @Override
    public GetThings<Set<String>> getThings() {
        return this.getThings;
    }

    @Override
    public DoZacFilter doSome(GetThings<Set<String>> getThings) {
        this.properties = getThings.getZacProperties();

        Set<String> collect = getThings.getValue().stream()
                .filter(this::noSuffixName)     // 去除无后缀
                .filter(this::noZip)            // 去除已zip过
                .collect(Collectors.toSet());
        this.getThings = getThings.set(collect);
        return this;
    }


    public boolean noSuffixName(String imgUrl) {
        // 匹配后缀中含有_forShow的
        String reg = ".*" + properties.getSuffixName() + "\\..*";
        return !imgUrl.matches(reg);
    }

    // 判断是否已经有被压缩后的文件
    public boolean noZip(String url) {
        String prefix = UrlUtils.getFileNameNoSuffix(url);

        String suffix = UrlUtils.getSuffixName(url);

        String zipPath = prefix + properties.getSuffixName() + suffix;
        return !new File(zipPath).exists();


    }

}
