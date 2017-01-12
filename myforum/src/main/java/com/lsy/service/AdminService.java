package com.lsy.service;

import com.lsy.dao.AdminDao;
import com.lsy.dao.NodeDao;
import com.lsy.dao.ReplyDao;
import com.lsy.dao.TopicDao;
import com.lsy.entitiy.Admin;
import com.lsy.entitiy.Node;
import com.lsy.entitiy.Topic;
import com.lsy.exception.ServiceException;
import com.lsy.mapper.NodeMapper;
import com.lsy.mapper.ReplyMapper;
import com.lsy.mapper.TopicMapper;
import com.lsy.util.Config;
import com.lsy.util.SqlSessionFactoryUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public class AdminService {
     SqlSession sqlSession= SqlSessionFactoryUtil.getSqlSession();
    TopicMapper topicMapper=sqlSession.getMapper(TopicMapper.class);
    ReplyMapper replyMapper=sqlSession.getMapper(ReplyMapper.class);
    NodeMapper nodeMapper=sqlSession.getMapper(NodeMapper.class);

        Logger logger= LoggerFactory.getLogger(AdminService.class);
        ReplyDao replyDao=new ReplyDao();
        TopicDao topicDao=new TopicDao();
        NodeDao nodeDao=new NodeDao();
        AdminDao adminDao=new AdminDao();

    public Admin login(String adminName, String password, String ip) {
        Admin admin=adminDao.findAdminByName(adminName);
        if(admin!= null && admin.getPassword().equals(DigestUtils.md5Hex(Config.get("user.password.salt") + password))){
            logger.debug("管理员{}登录了后台管理系统,IP为:{}",adminName,ip);
            return admin;
        }else{
            throw new ServiceException("帐号密码错误");
        }
    }

    public void delTopicById(String id) {
        //删除主题的回复
        replyMapper.delTopicById(id);
        //更新节点下的主题数量
        //1.根据topicId 获取 nodeId
       Topic topic=topicMapper.findTopicById(id);
       if(topic!=null){
           //2.根据nodeid 获取node
           Node node=nodeMapper.findNodeById(topic.getNodeid());//null;
           //3.更新node节点
           node.setTopicnum(node.getTopicnum()-1);
           nodeMapper.update(node);
           //删除主题
           topicMapper.delById(id);

       }else {
           throw new ServiceException("该主题不存在或已删除");
       }
    }
}
