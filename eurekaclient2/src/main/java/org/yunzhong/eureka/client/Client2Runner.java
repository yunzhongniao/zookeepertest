package org.yunzhong.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= "org.yunzhong")
@EnableAutoConfiguration
@EnableEurekaClient
public class Client2Runner {
    
    public static void main(String[] args) {
        SpringApplication.run(Client2Runner.class, args);
    }
}
