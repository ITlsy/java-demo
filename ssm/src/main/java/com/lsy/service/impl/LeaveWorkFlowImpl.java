package com.lsy.service.impl;

import com.lsy.mapper.LeaveMapper;
import com.lsy.pojo.Leave;
import com.lsy.pojo.User;
import com.lsy.service.LeaveWorkFlow;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
@Service
public class LeaveWorkFlowImpl implements LeaveWorkFlow {
    @Autowired
    LeaveMapper leaveMapper;
    @Autowired
    IdentityService identityService;
    @Autowired
    RuntimeService runtimeService;
    @Override
    @Transactional
    public void processStart(Leave leave, User user, String processDefKey, Map<String, Object> variables) {
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //1.封装leave请假对象并入库
        leave.setUserId(user.getId().toString());
        leave.setApplyTime(format.format(new Date()));
        leaveMapper.save(leave);
        //2.保存流程定义中的activiti:initiator的applyUserId，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(user.getId().toString());
        //3.启动流程
        ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(processDefKey,leave.getId().toString(),variables);
        leave.setProcessInstanceId(processInstance.getProcessInstanceId());
        leaveMapper.update(leave);
    }
}
