package org.yunzhong.zookeeperClient.component;

import java.util.List;
import java.util.UUID;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DefaultZKClient implements ZKClient, InitializingBean, Watcher {

    private static Logger logger = LoggerFactory.getLogger(DefaultZKClient.class);

    @Autowired
    private ZKConfig zkConfig;
    private ZooKeeper zooKeeper;
    private String id;

    @Override
    public byte[] getData(String path, ZKWatcher zkWatcher) throws KeeperException, InterruptedException {
        return zooKeeper.getData(path, zkWatcher == null ? null : zkWatcher, null);
    }

    @Override
    public List<String> getChildren(String path, ZKWatcher zkWatcher) throws KeeperException, InterruptedException {
        return zooKeeper.getChildren(path, zkWatcher == null ? null : zkWatcher);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        long start = System.currentTimeMillis();
        zooKeeper = new ZooKeeper(zkConfig.getAddress(), zkConfig.getTimeout(), this);
        while (States.CONNECTING == zooKeeper.getState()) {
            logger.info("Zookeeper client connecting...");
            Thread.sleep(2000L);
        }
        long end = System.currentTimeMillis();
        id = UUID.randomUUID().toString().replace("-", "");
        logger.info("create connection {} cost {}", id, (end - start));
        zooKeeper.create("/yunzhong/" + id, id.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        logger.info("Zookeeper 客户端初始化完成。");
    }

    public boolean listen() {
        try {
            long start = System.currentTimeMillis();
            List<String> children = zooKeeper.getChildren("/yunzhong", new ZKWatcher());
            long end = System.currentTimeMillis();
            logger.info("getchildren {} cost {}", children.size(), (end - start));
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void process(WatchedEvent event) {
        // nothing to do
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void close() throws InterruptedException {
        zooKeeper.close();
    }

}
