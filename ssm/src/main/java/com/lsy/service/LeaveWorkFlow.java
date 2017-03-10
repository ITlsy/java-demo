package com.lsy.service;

import com.lsy.pojo.Leave;
import com.lsy.pojo.User;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
public interface LeaveWorkFlow {
    void processStart(Leave leave, User user, String processDefKey, Map<String, Object> variables);
}
