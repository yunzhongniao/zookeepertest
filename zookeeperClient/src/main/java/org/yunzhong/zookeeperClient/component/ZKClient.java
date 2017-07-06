package org.yunzhong.zookeeperClient.component;

import org.apache.zookeeper.KeeperException;

import java.util.List;

public interface ZKClient {
    
    /**
     * 获取Zookeeper节点上数据
     *
     * @param path      节点路径
     * @param zkWatcher 监听器
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    byte[] getData(String path, ZKWatcher zkWatcher) throws KeeperException, InterruptedException;
    
    /**
     * 获取子节点列表
     *
     * @param path 节点路径
     * @return
     */
    List<String> getChildren(String path, ZKWatcher zkWatcher) throws KeeperException, InterruptedException;
    
}
