package com.jeiker.redis.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Description: Redis工具类测试
 * User: jeikerxiao
 * Date: 2019/1/10 5:10 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * String
     */
    @Test
    public void getExpire() {
        String key = "test:name";
        String value = "xiao";
        long time = 20;
        redisUtil.set(key, value);
        redisUtil.expire(key, time);
        long result = redisUtil.getExpire(key);
        Assert.assertTrue(result > 0);
    }

    @Test
    public void del() {
        String key = "test:name";
        String value = "xiao";
        redisUtil.set(key, value);
        redisUtil.del(key);
        Boolean result = redisUtil.hasKey(key);
        Assert.assertFalse(result);
    }

    @Test
    public void set() {
        String key = "test:name";
        String value = "xiao";
        redisUtil.set(key, value);
        String resultValue = redisUtil.get(key).toString();
        Assert.assertEquals(value, resultValue);
    }

    @Test
    public void set1() {
        String key = "test:name2";
        String value = "xiao";
        long time = 20;
        redisUtil.set(key, value, time);
        String resultValue = redisUtil.get(key).toString();
        Assert.assertEquals(value, resultValue);
    }

    @Test
    public void incr() {

    }

    @Test
    public void decr() {

    }

    /**
     * hashmap
     */
    @Test
    public void hget() {

    }

    @Test
    public void hmset() {
        String key = "test:hashmap";
        String value = "xiao";
        Map<String, Object> map = new HashMap<>();
        map.put("name1", "xiao");
        map.put("name2", "jeiker");
        redisUtil.hmset(key, map);
        Assert.assertNotNull(redisUtil.hmget(key));
    }

    @Test
    public void hmset1() {

    }

    @Test
    public void hset() {

    }

    @Test
    public void hset1() {
    }

    @Test
    public void hdel() {
    }

    @Test
    public void hHasKey() {
    }

    @Test
    public void hincr() {
    }

    @Test
    public void hdecr() {
    }

    /**
     * set
     */

    @Test
    public void sHasKey() {
        String key = "test:set";
        String value1 = "xiao1";

        redisUtil.sSet(key, value1);
        Assert.assertTrue(redisUtil.sHasKey(key, value1));
    }

    @Test
    public void sSet() {
        String key = "test:set";
        String value1 = "xiao1";
        String value2 = "xiao2";

        redisUtil.sSet(key, value1, value2);
        Set<Object> result = redisUtil.sGet(key);
        Assert.assertNotNull(result);
    }

    @Test
    public void sSetAndTime() {

    }

    @Test
    public void sGetSetSize() {

    }

    @Test
    public void setRemove() {

    }

    /**
     * list
     */
    @Test
    public void lGetListSize() {

    }

    @Test
    public void lGetIndex() {

    }

    @Test
    public void lSet() {
        String key = "test:list";
        String value = "xiao";
        redisUtil.lSet(key, value);
        redisUtil.lSet(key, value);
        redisUtil.lGet(key, 1, 2);

    }

    @Test
    public void lSet1() {

    }

    @Test
    public void lSet2() {

    }

    @Test
    public void lSet3() {

    }

    @Test
    public void lUpdateIndex() {
        String key = "test:list:update";
        String value = "xiao";
        redisUtil.lSet(key, value);
        redisUtil.lSet(key, value);
        redisUtil.lSet(key, value);
        String changeValue = "jeiker";
        redisUtil.lUpdateIndex(key, 1, changeValue);
    }

    @Test
    public void lRemove() {
        String key = "test:list:remove";
        String value = "xiao";
        redisUtil.lSet(key, value);
        redisUtil.lSet(key, value);
        redisUtil.lSet(key, value);
        long result = redisUtil.lRemove(key, 0, value);
        Assert.assertTrue(result > 0);
    }
}