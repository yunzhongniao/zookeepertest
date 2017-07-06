package org.yunzhong.zookeeperClient.component;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKWatcher implements Watcher {
    private static final Logger log = LoggerFactory.getLogger(ZKWatcher.class);

    @Override
    public void process(WatchedEvent event) {
        log.info("watch" + event.getPath() + " message:" + event.getType());
    }
}
