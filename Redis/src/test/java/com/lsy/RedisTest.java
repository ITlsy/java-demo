package com.lsy;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2017/3/3 0003.
 */
public class RedisTest {
    @Test
    public void stringSet(){
        Jedis jedis=new Jedis("127.0.0.1");
        jedis.set("name","tom");
        jedis.close();

    }
    @Test
    public void stringGet(){
        Jedis jedis=new Jedis();
        String name=jedis.get("name");
        jedis.close();
        Assert.assertEquals("tom",name);
    }
}
