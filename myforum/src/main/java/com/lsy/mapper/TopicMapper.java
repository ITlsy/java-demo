package com.lsy.mapper;
import com.lsy.entitiy.Topic;
import com.lsy.entitiy.TopicReplyCount;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/6 0006.
 */
@CacheNamespace
public interface TopicMapper {
    @Insert("insert into t_topic (title,content,nodeid,userid) values(#{title},#{content},#{nodeid},#{userid})")
    Integer save(Topic topic);

    @Select("select * from t_topic where id=#{id}")
    Topic findTopicById(String id);

    @Update("update t_topic set title=#{title},content=#{content},clicknum=#{clicknum},favnum=#{favnum},thankyounum=#{thankyounum},replynum=#{replynum},lastreplytime=#{lastreplytime},nodeid=#{nodeid},userid=#{userid} where id=#{id}")
    void update(Topic topic);

    @Select("select count(*) from t_topic")
    Integer count();

    @Select("select count(*) from t_topic where nodeid=#{nodeid}")
    Integer count(String nodeid);

    @Delete("delete from t_topic where id=#{id}")
    void delById(String id);

    @Select("select count(*) from (select count(*) from t_topic group by date_format(createtime,'%y-%m-%d')) as topicCount")
    Integer findCountTopicByDay();

    List<Topic> findAll(Map<String,Object> map);

    @Select("SELECT COUNT(*) topicnum,DATE_FORMAT(createtime,'%y-%m-%d') 'time',(SELECT COUNT(*) FROM t_reply WHERE DATE_FORMAT(createtime,'%y-%m-%d') = DATE_FORMAT(t_topic.createtime,'%y-%m-%d')) 'replynum' FROM t_topic GROUP BY (DATE_FORMAT(createtime,'%y-%m-%d')) ORDER BY (DATE_FORMAT(createtime,'%y-%m-%d')) DESC LIMIT #{start},#{pageSize}")
    List<TopicReplyCount> findTopicnumAndReplynumList(@Param("start") int start,@Param("pageSize") int pageSize);
}
