package com.jeiker.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * Description: 删除节点
 * User: jeikerxiao
 * Date: 2019/5/27 5:42 PM
 */
public class ZKDelete {


    private static ZooKeeper zk;
    private static ZooKeeperConnection conn;

    // Method to check existence of znode and its status, if znode is available.
    public static void delete(String path) throws KeeperException, InterruptedException {
        zk.delete(path, zk.exists(path, true).getVersion());
    }

    public static void main(String[] args) {
        //Assign path to the znode
        String path = "/MyFirstZnode";

        try {
            conn = new ZooKeeperConnection();
            zk = conn.connect("localhost:32770");
            //delete the node with the specified path
            delete(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
