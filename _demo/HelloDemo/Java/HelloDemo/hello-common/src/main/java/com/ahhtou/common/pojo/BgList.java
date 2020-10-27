package com.ahhtou.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BgList implements Serializable {

    private Integer width;
    private Integer height;
    private String proxyUrl;
    private Set<String> searchPaths;

    private Set<String> BgList;


}
