package com.lsy.mapper;
import com.lsy.entitiy.Topic;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Administrator on 2017/1/6 0006.
 */
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
}
