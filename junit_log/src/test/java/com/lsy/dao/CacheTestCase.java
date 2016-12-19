package com.lsy.dao;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.junit.Test;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class CacheTestCase {
    @Test
    public void testCache(){
        CacheManager cacheManager=new CacheManager();//读取xml
        Ehcache ehcache=cacheManager.getEhcache("user");
        //添加
        Element element=new Element("user:1","cache");
        ehcache.put(element);

        /*//删除
        ehcache.remove("user:1");
        ehcache.removeAll();*/

        //取值
        Element e=ehcache.get("user:1");
        System.out.println(e.getObjectValue());



    }
}
