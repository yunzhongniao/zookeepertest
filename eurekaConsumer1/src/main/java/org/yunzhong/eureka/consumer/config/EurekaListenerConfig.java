package org.yunzhong.eureka.consumer.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.CacheRefreshedEvent;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.EurekaEvent;
import com.netflix.discovery.EurekaEventListener;
import com.netflix.discovery.StatusChangeEvent;

@Component
public class EurekaListenerConfig implements InitializingBean {

    @Autowired
    private EurekaClient eurekaClient;

    @Override
    public void afterPropertiesSet() throws Exception {
        EurekaEventListener eventListener = new EurekaEventListener() {

            @Override
            public void onEvent(EurekaEvent event) {
                if (event instanceof StatusChangeEvent) {
                    StatusChangeEvent changeEvent = (StatusChangeEvent) event;
                    System.out.println("event type " + changeEvent.getClass());
                } else if (event instanceof CacheRefreshedEvent) {
                    CacheRefreshedEvent refreshedEvent = (CacheRefreshedEvent) event;
                    System.out.println("event type " + refreshedEvent.getClass());
                } else {
                    System.out.println("event type error " + event.getClass());
                }

            }
        };
        eurekaClient.registerEventListener(eventListener);
    }

}
