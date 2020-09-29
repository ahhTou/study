package com.ahhTou.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BgList {

    public static String REDIS_SAVE_KEY = "BgImagesForHelloProject";

    private Map<String, Integer> size;

    private List<String> BgList;

}
