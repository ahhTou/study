package com.ahhtou.utils;

import com.ahhtou.var.Properties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloUtils {

    Properties properties;

    public boolean isImgUrl(String imgUrl) {
        return properties.getImgSuffixReg().stream().anyMatch(reg -> Pattern.matches(reg, imgUrl));
    }
}
