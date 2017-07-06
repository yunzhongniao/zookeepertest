package org.yunzhong.zookeeperClient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yunzhong.zookeeperClient.component.DefaultZKClient;
import org.yunzhong.zookeeperClient.component.ZookeeperClientFactory;

@RestController
public class ZookeeperClientRestController {

    private ExecutorService executor = Executors.newFixedThreadPool(100);
    @Autowired
    private ZookeeperClientFactory clientFactory;

    @RequestMapping("/create/zookeeper")
    public String create() {
        DefaultZKClient client = clientFactory.create();
        return client.getId();
    }

    @RequestMapping("/batch/{count}/create/zookeeper")
    public List<String> batchCreate(@PathVariable("count") int count) {
        final List<String> result = new ArrayList<String>();
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for (int i = 0; i < count; i++) {
            Future<String> future = executor.submit(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    DefaultZKClient client = clientFactory.create();
                    return client.getId();
                }
            });
            futures.add(future);
        }
        for (Future<String> future : futures) {
            String id = null;
            try {
                id = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            result.add(id);
        }
        return result;
    }

    @RequestMapping("/count/zookeeper")
    public int count() {
        return clientFactory.count();
    }

    @RequestMapping("/list/zookeeper")
    public Map<String, DefaultZKClient> list() {
        return clientFactory.clientMap;
    }

    @RequestMapping("/disconnect/zookeeper/{id}")
    public boolean disconnect(@PathVariable("id") String id) throws InterruptedException {
        return clientFactory.disconnect(id);
    }
    
    @RequestMapping("/listen/zookeeper")
    public boolean disconnect() throws InterruptedException {
        return clientFactory.listen();
    }
}
