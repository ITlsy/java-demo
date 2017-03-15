package com.lsy.test;

import com.lsy.pojo.Topic;
import com.lsy.pojo.TopicContent;
import com.lsy.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class OneToOneTest2 {
    @Test
    public void save(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Topic topic=new Topic();
        topic.setTitle("hibernate?");
        TopicContent topicContent=new TopicContent();
        topicContent.setContent("xxxx ORM");
        topic.setTopicContent(topicContent);
        topicContent.setTopic(topic);

        session.save(topic);
        session.save(topicContent);
        session.getTransaction().commit();
    }

    @Test
    public void find(){
        Session session= SessionFactoryUtil.getSession();
        session.getTransaction().begin();
        Topic topic= (Topic) session.get(Topic.class,1);
        System.out.println(topic.getTitle());
        System.out.println(topic.getTopicContent().getContent());
        session.getTransaction().commit();
    }
}
