package com.lsy.pojo;

/**
 * Created by Administrator on 2017/3/14 0014.
 */
public class Topic {
    private Integer id;
    private String title;
    private TopicContent topicContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TopicContent getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(TopicContent topicContent) {
        this.topicContent = topicContent;
    }
}
