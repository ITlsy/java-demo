package com.lsy.mapper;

import com.lsy.pojo.Leave;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public interface LeaveMapper {
    void save(Leave leave);
    void delete (long id);
    void update(Leave leave);
    Leave findLeaveById(long id);
}
