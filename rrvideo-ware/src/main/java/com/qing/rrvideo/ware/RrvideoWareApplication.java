package com.qing.rrvideo.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册与发现功能
public class RrvideoWareApplication {

    public static void main(String[] args) {
        SpringApplication.run(RrvideoWareApplication.class, args);
    }

}
