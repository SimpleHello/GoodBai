package com.good.task01.job;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mg on 2018/1/15.
 */
public class JobTwo {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTwo.class);
    public  void run(){
        LOGGER.info("运行了 JobTwo ！！！");
    }
}
