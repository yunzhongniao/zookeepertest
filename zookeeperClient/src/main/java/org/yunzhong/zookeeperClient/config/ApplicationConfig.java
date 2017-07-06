package org.yunzhong.zookeeperClient.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.yunzhong.zookeeperClient.component.ZKConfig;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.yunzhong", excludeFilters = {})
public class ApplicationConfig {


    @Bean
    @ConfigurationProperties(prefix = "zookeeper")
    public ZKConfig zkConfig() {
        ZKConfig zkConfig = new ZKConfig();
        return zkConfig;
    }
}
