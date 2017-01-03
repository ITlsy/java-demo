package com.lsy.dao;

import com.lsy.entitiy.Topic;
import com.lsy.entitiy.TopicReplyCount;
import com.lsy.entitiy.User;
import com.lsy.util.Config;
import com.lsy.util.DbHelp;
import com.lsy.util.StringUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.AbstractListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TopicDao {
    public Integer save(Topic topic) {
        String sql="insert into t_topic (title,content,nodeid,userid) values(?,?,?,?)";
        return DbHelp.insert(sql,topic.getTitle(),topic.getContent(),topic.getNodeid(),topic.getUserid());
    }

    public Topic findTopicById(String topicid) {
        String sql="select * from t_topic where id=?";
        return DbHelp.query(sql,new BeanHandler<>(Topic.class),topicid);
    }

    public void update(Topic topic) {
        String sql="update t_topic set title=?,content=?,clicknum=?,favnum=?,thankyounum=?,replynum=?,lastreplytime=?,nodeid=?,userid=? where id=?";
        DbHelp.update(sql,topic.getTitle(),topic.getContent(), topic.getClicknum(),
                topic.getFavnum(),topic.getThankyounum(),topic.getReplynum(),topic.getLastreplytime(),
                topic.getNodeid(),topic.getUserid(),topic.getId());
    }

    public int count() {
        String sql="select count(*) from t_topic";
        return DbHelp.query(sql,new ScalarHandler<Long>()).intValue();
    }

    public int count(String nodeid) {
        String sql="select count(*) from t_topic where nodeid=?";
        return DbHelp.query(sql,new ScalarHandler<Long>(),nodeid).intValue();
    }

    public List<Topic> findAll(HashMap<String,Object> map) {
        String sql="SELECT tu.username,tu.avatar,tt.* FROM t_topic tt,t_user tu WHERE tt.userid = tu.id ";
        String nodeid=map.get("nodeid")==null ?null:String.valueOf(map.get("nodeid"));
        String where="";
       List<Object> array=new ArrayList<>();
       if (StringUtils.isNotEmpty(nodeid)){
           where+="and nodeid=?";
           array.add(nodeid);
       }
       where+=" order by tt.lastreplytime desc limit ?,?";
       array.add(map.get("start"));
       array.add(map.get("pageSize"));
       sql +=where;

       return DbHelp.query(sql, new AbstractListHandler<Topic>() {
           @Override
           protected Topic handleRow(ResultSet rs) throws SQLException {
               Topic topic=new BasicRowProcessor().toBean(rs,Topic.class);
               User user=new User();
               user.setId(rs.getInt("userid"));
               user.setUsername(rs.getString("username"));
               user.setAvatar(Config.get("qiniu.domain")+rs.getString("avatar"));
               topic.setUser(user);
               return topic;
           }
       },array.toArray());
    }

    public void delById(String id) {
        String sql="delete from t_topic where id=?";
        DbHelp.update(sql,id);
    }

    public int findCountTopicByDay() {
      //select count(*) from (select count(*) from t_topic
        // group by DATE_FORMAT(createtime,'%y-%m-%d')) AS topicCount"

        //以创建时间来按组分，七个天数 7
        String sql="select count(*) from (select count(*) from t_topic group by date_format(createtime,'%y-%m-%d')) as topicCount";
        return DbHelp.query(sql,new ScalarHandler<Long>()).intValue();
    }

    public List<TopicReplyCount> findTopicnumAndReplynumList(Integer start, Integer pageSize) {
        String sql="SELECT COUNT(*) topicnum,DATE_FORMAT(createtime,'%y-%m-%d') 'time',(SELECT COUNT(*) FROM t_reply WHERE DATE_FORMAT(createtime,'%y-%m-%d') = DATE_FORMAT(t_topic.createtime,'%y-%m-%d')) 'replynum' FROM t_topic GROUP BY (DATE_FORMAT(createtime,'%y-%m-%d')) ORDER BY (DATE_FORMAT(createtime,'%y-%m-%d')) DESC LIMIT ?,?";
        return DbHelp.query(sql,new BeanListHandler<>(TopicReplyCount.class),start,pageSize);
    }
}
