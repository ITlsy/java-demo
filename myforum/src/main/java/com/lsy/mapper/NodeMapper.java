package com.lsy.mapper;

import com.lsy.entitiy.Node;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
@CacheNamespace
public interface NodeMapper {
    @Select("select * from t_node")
    List<Node> findAllNodes();

    @Select("select * from t_node where id=?")
    Node findNodeById(Integer nodeid);

    @Update("update t_node set topicnum=#{topicnum},nodename=#{nodenum} where id=#{id}")
    void update(Node node);

    @Select("select * from t_node where nodename=#{nodename}")
    Node findNodeByName(String nodename);

    @Delete("delete from t_node where id=#{id}")
    void delNode(String id);

    @Insert("insert into t_node (nodename) values(#{nodename})")
    void save(String nodename);

}
