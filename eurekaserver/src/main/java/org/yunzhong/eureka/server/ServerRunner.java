package org.yunzhong.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= "org.yunzhong")
@EnableAutoConfiguration
@EnableEurekaServer
public class ServerRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServerRunner.class, args);
    }
}
