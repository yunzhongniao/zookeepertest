package org.yunzhong.eureka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages= "org.yunzhong")
@EnableEurekaClient
public class ClientConsumerRunner1 {
    
    public static void main(String[] args) {
        SpringApplication.run(ClientConsumerRunner1.class, args);
    }
}
