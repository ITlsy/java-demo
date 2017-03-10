package com.lsy.service.impl;

import com.lsy.mapper.LeaveMapper;
import com.lsy.pojo.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/3/10 0010.
 */
@Service
@Transactional
public class LeaveManager {
    @Autowired
    private LeaveMapper leaveMapper;

    /**
     * 保存实体
     */
    public void save(Leave entity){
    leaveMapper.save(entity);
    }

    public void delete(Long id){
        leaveMapper.delete(id);
    }

    public Leave find(Long id){
       return leaveMapper.findLeaveById(id);
    }

    public void update(Leave entity){
        leaveMapper.update(entity);
    }
}
