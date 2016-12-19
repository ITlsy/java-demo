package com.lsy.service;

import com.lsy.dao.MessageDao;
import com.lsy.entity.Message;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9 0009.
 */
public class MessageService {
    private MessageDao messageDao=new MessageDao();

    public List<Message> findAll(){

        return messageDao.findAll();
    }
    public List<Message> findByMaxId(int maxId) {
        return messageDao.findByMaxId(maxId);

    }
}
