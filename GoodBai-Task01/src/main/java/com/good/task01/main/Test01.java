package com.good.task01.main;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * Created by Mg on 2018/1/16.
 */
public class Test01 {

    public static void main(String[] args) throws  Exception{
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, Integer.MAX_VALUE);
        CuratorFramework zkTools = CuratorFrameworkFactory
                .builder()
                .connectString("10.0.5.36:2181")
                .namespace("zk-scheduling")
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(30000)
                .sessionTimeoutMs(30000)
                .build();
        zkTools.start();
//        zkTools.create()
//                .withMode(CreateMode.PERSISTENT)
//                .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
//                .forPath("/simple/11");
//        System.out.println("创建独享锁队列节点成功！");
        List<String> nodes = zkTools.getChildren().watched().forPath("/simple");
        System.out.println("节点:"+nodes.size());
    }
}
