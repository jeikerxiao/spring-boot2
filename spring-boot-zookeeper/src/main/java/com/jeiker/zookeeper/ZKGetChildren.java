package com.jeiker.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * Description: spring-boot2
 * User: jeikerxiao
 * Date: 2019/5/27 5:40 PM
 */
public class ZKGetChildren {

    private static ZooKeeper zk;
    private static ZooKeeperConnection conn;

    // Method to check existence of znode and its status, if znode is available.
    public static Stat znode_exists(String path) throws KeeperException, InterruptedException {
        return zk.exists(path, true);
    }

    public static void main(String[] args) {
        // Assign path to the znode
        String path = "/MyFirstZnode";

        try {
            conn = new ZooKeeperConnection();
            zk = conn.connect("localhost:32770");
            // Stat checks the path
            Stat stat = znode_exists(path);

            if (stat != null) {

                //“getChildren" method- get all the children of znode.It has twoargs, path and watch
                List<String> children = zk.getChildren(path, false);
                for (String aChildren : children) {
                    //Print children's
                    System.out.println(aChildren);
                }
            } else {
                System.out.println("Node does not exists");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
