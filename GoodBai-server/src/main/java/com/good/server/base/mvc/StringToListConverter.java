package com.good.server.base.mvc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class StringToListConverter implements Converter<String, List<Object>> {
	 
    @Override
    public List<Object> convert(String source) {
        List<Object> result = new ArrayList<Object>();
        if(!StringUtils.isEmpty(source)) {
            String[] array = source.split(",");
            for(Object obj : array) {
            	result.add(obj);
            }
        }
        return result;
    }
}