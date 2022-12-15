package com.qing.rrvideo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
// 排除掉datasource的自动配置
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RrvideoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RrvideoGatewayApplication.class, args);
    }

}
