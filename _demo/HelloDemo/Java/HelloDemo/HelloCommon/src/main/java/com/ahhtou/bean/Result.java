package com.ahhtou.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result {

    Object data;
    Boolean success = true;

    public Result(Object data) {
        this.data = data;
    }
}
