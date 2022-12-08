package com.qing.rrvideo.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient // 开启nacos服务注册与发现功能
@EnableFeignClients(basePackages = "com.qing.rrvideo.member.feign")    // 开启feign客户端远程调用功能
public class RrvideoMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(RrvideoMemberApplication.class, args);
    }

}
