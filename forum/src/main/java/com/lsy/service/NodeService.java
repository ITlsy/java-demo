package com.lsy.service;

import com.lsy.dao.NodeDao;
import com.lsy.entitiy.Node;
import com.lsy.exception.ServiceException;
import com.lsy.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2016/12/28 0028.
 */
public class NodeService {
    NodeDao nodeDao=new NodeDao();
    public void updateNode(String nodeid, String nodename) {
        System.out.println(nodename);
        if (StringUtils.isNumeric(nodeid) && StringUtils.isNotEmpty(nodename)) {
            Node node = nodeDao.findNodeById(Integer.valueOf(nodeid));
            node.setNodename(nodename);
            nodeDao.update(node);
        } else {
            throw new ServiceException("参数异常");
        }

    }

    public String validateNodename(String nodeid, String nodename) {
        // 根据nodeid查询node,并判断nodeName是否等于node的nodename
        Node node=nodeDao.findNodeById(Integer.valueOf(nodeid));
        if(node.getNodename().equals(nodename)){
            return "true";
        }else{
          Node nodeIsIn=nodeDao.findNodeByName(nodename);
          if(nodeIsIn==null){
              return "true";
          }

        }
        return "false";
    }

    public void delNodeById(String id) {
    Node node=nodeDao.findNodeById(Integer.valueOf(id));
    if(node.getTopicnum()>0){
        throw new ServiceException("该节点下已有主题,不可删除");
      }else {
        nodeDao.delNode(id);
    }
    }
}
