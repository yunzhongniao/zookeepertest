package org.yunzhong.eureka.consumer.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consume")
public class ConsumerController {

    @Autowired
    private DiscoveryClient discoveryClient;
    
    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "urls", method = RequestMethod.GET)
    public String consume() {
        StringBuilder builder = new StringBuilder();
        List<ServiceInstance> instances = discoveryClient.getInstances("EUREKA-CLIENT1");
        if (!CollectionUtils.isEmpty(instances)) {
            for (ServiceInstance instance : instances) {
                URI uri = instance.getUri();
                String serviceId = instance.getServiceId();
                builder.append(uri.toString() + serviceId);
                System.out.println(uri.toString() + serviceId);
                
                String result = null;
                try {
                    result = restTemplate.getForObject(uri, String.class);
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
                System.out.println(result);
                builder.append(" :"+ result);
            }
        }
        return builder.toString();
    }
}
