package com.lsy.controller;

import com.lsy.pojo.Leave;
import com.lsy.pojo.User;
import com.lsy.service.LeaveWorkFlow;
import com.lsy.shiro.ShiroUtil;
import org.activiti.engine.ActivitiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
@Controller
@RequestMapping("/leave")
public class LeaveController {
    Logger logger= LoggerFactory.getLogger(LeaveController.class);

    @Autowired
    private LeaveWorkFlow leaveWorkFlow;

    @GetMapping("/apply")
    public String leaveApply(){
        return "activiti/leave/leaveApply";
    }

    @RequestMapping("/start")
    public String leaveStart(Leave leave, Model model){
        User user= ShiroUtil.getCurrentUser();
        String processDefKey="leaveProcess";
        Map<String,Object> variables=new HashMap<>();
        //TODO 模拟找到了上级
        String upperId="24";
        variables.put("deptLeaderUserId",upperId);
        try{
            leaveWorkFlow.processStart(leave,user,processDefKey,variables);
            model.addAttribute("message","流程启动成功");
        }catch (ActivitiException e){
            logger.error("KEY为{}：流程启动异常",processDefKey);
            model.addAttribute("message","流程启动失败");
        }
        return "activiti/leave/leaveApply";
    }
}
