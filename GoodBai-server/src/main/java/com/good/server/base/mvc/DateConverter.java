package com.good.server.base.mvc;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
	@Override    
	public Date convert(String source) {  
		if(source.equals(""))return null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
	    dateFormat.setLenient(false);    
	    try {    
	        return dateFormat.parse(source);    
	    } catch (ParseException e) {    
	    	dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	    	dateFormat.setLenient(false);
	    	try{
	    		return dateFormat.parse(source);
	    	}
	    	catch(ParseException ee){
	    		e.printStackTrace();
	    	}
	    }           
	    return null;    
	}    
}
