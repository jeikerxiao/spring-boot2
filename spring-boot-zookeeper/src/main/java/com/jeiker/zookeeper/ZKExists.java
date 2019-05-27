package com.jeiker.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * Description: 检查节点是否存在
 * User: jeikerxiao
 * Date: 2019/5/27 5:23 PM
 */
public class ZKExists {

    private static ZooKeeper zk;
    private static ZooKeeperConnection conn;

    // Method to check existence of znode and its status, if znode is available.
    public static Stat znode_exists(String path) throws KeeperException, InterruptedException {
        return zk.exists(path, true);
    }

    public static void main(String[] args) {
        // Assign znode to the specified path
        String path = "/MyFirstZnode";

        try {
            conn = new ZooKeeperConnection();
            zk = conn.connect("localhost:32770");
            // Stat checks the path of the znode
            Stat stat = znode_exists(path);

            if (stat != null) {
                System.out.println("Node exists and the node version is " + stat.getVersion());
            } else {
                System.out.println("Node does not exists");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
