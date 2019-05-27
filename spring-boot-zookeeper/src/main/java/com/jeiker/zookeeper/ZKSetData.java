package com.jeiker.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

/**
 * Description: 修改指定znode中附加的数据
 * User: jeikerxiao
 * Date: 2019/5/27 5:35 PM
 */
public class ZKSetData {

    private static ZooKeeper zk;
    private static ZooKeeperConnection conn;

    // Method to update the data in a znode. Similar to getData but without watcher.
    public static void update(String path, byte[] data) throws KeeperException, InterruptedException {
        zk.setData(path, data, zk.exists(path, true).getVersion());
    }

    public static void main(String[] args) {
        String path = "/MyFirstZnode";
        //Assign data which is to be updated.
        byte[] data = "Success".getBytes();

        try {
            conn = new ZooKeeperConnection();
            zk = conn.connect("localhost:32770");
            // Update znode data to the specified path
            update(path, data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
