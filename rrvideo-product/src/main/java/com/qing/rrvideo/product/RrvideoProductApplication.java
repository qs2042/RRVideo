package com.qing.rrvideo.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务注册与发现功能
// TODO: 开启feign客户端远程调用功能 -> @EnableFeignClients(basePackages = "com.qing.rrvideo.xxx.feign")
public class RrvideoProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(RrvideoProductApplication.class, args);
    }

}
