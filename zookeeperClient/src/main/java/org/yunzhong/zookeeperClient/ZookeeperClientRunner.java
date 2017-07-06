package org.yunzhong.zookeeperClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= "org.yunzhong")
@EnableAutoConfiguration
public class ZookeeperClientRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ZookeeperClientRunner.class, args);
    }
}
