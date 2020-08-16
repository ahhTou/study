package com.ahhTou.untils;

import com.ahhTou.dao.AnimeTypesMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component
public class MyTypesFilter {

    @Resource
    AnimeTypesMapper animeTypesMapper;


    public <T> List<Integer> stringTypesToIntegerTypes(List<String> inputList) {
        try {

            List<Integer> outputList = new ArrayList<>();

            Map<String, Map<String, T>> allTypesMapper = animeTypesMapper.getAllTypesMapper();

            Set<Map.Entry<String, Map<String, T>>> entries = allTypesMapper.entrySet();

            for (Map.Entry<String, Map<String, T>> map : entries) {
                Map<String, T> value = map.getValue();
                if (inputList.contains((String) value.get("type_name"))) {
                    Integer type_id = (Integer) value.get("type_id");
                    outputList.add(type_id);
                }
            }
            return outputList;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("> stringTypesToIntegerTypes 失败");
            return null;
        }


    }

}
