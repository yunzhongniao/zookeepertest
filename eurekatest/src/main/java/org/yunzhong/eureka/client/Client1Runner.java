package org.yunzhong.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableEurekaClient
public class Client1Runner {
    
    public static void main(String[] args) {
        SpringApplication.run(Client1Runner.class, args);
    }
}
