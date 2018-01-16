package com.good.task01.mulechina;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mg on 2018/1/16.
 */
public class MyElasticJob implements SimpleJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyElasticJob.class);
    @Override
    public void execute(ShardingContext context) {
        String str = context.getJobParameter();
        LOGGER.info("我执行了次方法.....参数:"+str);
        switch (context.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                LOGGER.info("do something by sharding item 0");
                break;
            case 1:
                // do something by sharding item 1
                LOGGER.info("do something by sharding item 1");
                break;
            case 2:
                // do something by sharding item 2
                LOGGER.info("do something by sharding item 2");
                break;
            default:
                LOGGER.info("do something by sharding item other");
        }
    }
}