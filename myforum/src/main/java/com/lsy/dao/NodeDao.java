package com.lsy.dao;

import com.lsy.entitiy.Node;
import com.lsy.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20 0020.
 */
public class NodeDao {
    public List<Node> findAllNodes() {
        String sql="select * from t_node";
        return DbHelp.query(sql,new BeanListHandler<>(Node.class));

    }

    public Node findNodeById(Integer nodeid) {
        String sql="select * from t_node where id=?";
        return DbHelp.query(sql,new BeanHandler<>(Node.class),nodeid);
    }

    public void update(Node node) {
        String sql="update t_node set topicnum=?,nodename=? where id=?";
        DbHelp.update(sql,node.getTopicnum(),node.getNodename(),node.getId());
    }

    public Node findNodeByName(String nodename) {
        String sql="select * from t_node where nodename=?";
        return DbHelp.query(sql,new BeanHandler<>(Node.class),nodename);
    }

    public void delNode(String id) {
        String sql="delete from t_node where id=?";
        DbHelp.update(sql,id);
    }

    public void save(String nodename) {
        String sql="insert into t_node (nodename) values(?)";
        DbHelp.update(sql,nodename);
    }
}
