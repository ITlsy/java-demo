package com.lsy.service;

import com.lsy.dao.NodeDao;
import com.lsy.entitiy.Node;
import com.lsy.exception.ServiceException;
import com.lsy.mapper.NodeMapper;
import com.lsy.util.DbHelp;
import com.lsy.util.SqlSessionFactoryUtil;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public class NodeService {
    SqlSession sqlSession= SqlSessionFactoryUtil.getSqlSession(true);
    NodeMapper nodeMapper=sqlSession.getMapper(NodeMapper.class);
    NodeDao nodeDao = new NodeDao();

    public void updateNode(String nodeid, String nodename) {
        System.out.println(nodename);
        if (StringUtils.isNumeric(nodeid) && StringUtils.isNotEmpty(nodename)) {
            Node node = nodeMapper.findNodeById(Integer.valueOf(nodeid));
            node.setNodename(nodename);
            nodeMapper.update(node);
        } else {
            throw new ServiceException("参数异常");
        }

    }

    public String validateNodename(String nodeid, String nodename) {
        // 根据nodeid查询node,并判断nodeName是否等于node的nodename
        Node node = nodeMapper.findNodeById(Integer.valueOf(nodeid));
        if (node.getNodename().equals(nodename)) {
            return "true";
        } else {
            Node nodeIsIn = nodeMapper.findNodeByName(nodename);
            if (nodeIsIn == null) {
                return "true";
            }

        }
        return "false";
    }

    public void delNodeById(String id) {
        Node node = nodeMapper.findNodeById(Integer.valueOf(id));
        if (node.getTopicnum() > 0) {
            throw new ServiceException("该节点下已有主题,不可删除");
        } else {
            nodeMapper.delNode(id);
        }
    }




    public void addNode(String nodename) {
        Node node =nodeMapper.findNodeByName(nodename);
        if (node != null) {

            throw new ServiceException("节点被占用");
        } else{
            nodeMapper.save(nodename);
        }


    }
}