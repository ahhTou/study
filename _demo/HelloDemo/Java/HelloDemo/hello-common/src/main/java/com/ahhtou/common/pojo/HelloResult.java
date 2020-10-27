package com.ahhtou.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HelloResult {

    Object data;
    String message;

    Boolean success = true;

    public HelloResult(String message) {
        this.message = message;
    }

    public HelloResult(Object data, String message) {
        this.data = data;
    }
}
