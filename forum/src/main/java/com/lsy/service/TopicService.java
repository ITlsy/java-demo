package com.lsy.service;

import com.lsy.dao.NodeDao;
import com.lsy.dao.TopicDao;
import com.lsy.dao.UserDao;
import com.lsy.entitiy.Node;
import com.lsy.entitiy.Topic;
import com.lsy.entitiy.User;
import com.lsy.exception.ServiceException;
import com.lsy.util.Config;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20 0020.
 */
public class TopicService {
    NodeDao nodeDao=new NodeDao();
    TopicDao topicDao=new TopicDao();
    UserDao userDao=new UserDao();
    public List<Node> findAllNode() {
        List<Node> nodeList=nodeDao.findAllNodes();
        return nodeList;
    }

    public Topic addNewTopic(String title, String content, Integer nodeid, Integer userid) {
        //封装topic对象
        Topic topic=new Topic();
        topic.setTitle(title);
        topic.setContent(content);
        topic.setNodeid(nodeid);
        topic.setUserid(userid);
        Integer topicid=topicDao.save(topic);
        topic.setId(topicid);
        return topic;
    }

    public Topic findTopicById(String topicid) {
        if(StringUtils.isNumeric(topicid)){
            Topic topic=topicDao.findTopicById(topicid);
            if(topic!=null){
                //通过topic对象的userid、nodeid 获取user和node对象,并set到topic对象中
                User user=userDao.findById(topic.getUserid());
                Node node=nodeDao.findNodeById(topic.getNodeid());
                user.setAvatar(Config.get("qiniu.domain")+user.getAvatar());
               topic.setUser(user);
               topic.setNode(node);
               return topic;

            }else {
                throw new ServiceException("该帖不存在或已被删除");
            }
        }else {
            throw  new ServiceException("参数错误");
        }
    }
}
