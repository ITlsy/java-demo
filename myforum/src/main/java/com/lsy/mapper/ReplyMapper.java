package com.lsy.mapper;

import com.lsy.entitiy.Reply;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
@CacheNamespace
public interface ReplyMapper {
    @Insert("insert into t_reply (content,userid,topicid) values(#{content},#{userid},#{topicid})")
    void addReply(Reply reply);

    @Select("select tu.id,tu.avatar,tu.username,tr.* from t_reply tr,t_user tu where tr.userid=tu.id and topicid=#{topicid}")
    List<Reply> findReplyListByTopicid(String topicid);

    @Delete("delete from t_reply where topicid=#{topicid}")
    void delTopicById(String id);
}
