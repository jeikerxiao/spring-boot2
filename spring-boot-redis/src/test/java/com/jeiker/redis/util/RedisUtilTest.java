package com.jeiker.redis.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void sGet() {
    }

    @Test
    public void sHasKey() {
    }

    @Test
    public void sSet() {
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

    @Test
    public void lGet() {
    }

    @Test
    public void lGetListSize() {
    }

    @Test
    public void lGetIndex() {
    }

    @Test
    public void lSet() {
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
    }

    @Test
    public void lRemove() {
    }
}