package org.yunzhong.zookeeperClient.component;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.yunzhong.zookeeperClient.config.ApplicationContextFactory;

@Component
public class ZookeeperClientFactory {

    public final Map<String, DefaultZKClient> clientMap = new HashMap<String, DefaultZKClient>();

    /**
     * @return
     * @author yunzhong
     * @time 2017年7月5日下午5:42:29
     */
    public int count() {
        return clientMap.size();
    }

    public DefaultZKClient create() {
        DefaultZKClient zkClient = (DefaultZKClient) ApplicationContextFactory.getBean(ZKClient.class);
        clientMap.put(zkClient.getId(), zkClient);
        return zkClient;
    }
    
    public boolean disconnect(String id) throws InterruptedException{
        if(clientMap.containsKey(id)){
            clientMap.get(id).close();
            return true;
        }
        return false;
    }

    public boolean listen() {
        Iterator<DefaultZKClient> iterator = clientMap.values().iterator();
        while(iterator.hasNext()){
            DefaultZKClient next = iterator.next();
            next.listen();
        }
        return true;
    }
}
