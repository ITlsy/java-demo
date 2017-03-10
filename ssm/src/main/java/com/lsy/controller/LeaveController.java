package com.lsy.controller;

import com.lsy.mapper.LeaveMapper;
import com.lsy.pojo.Leave;
import com.lsy.pojo.User;
import com.lsy.service.LeaveWorkFlow;
import com.lsy.shiro.ShiroUtil;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private TaskService taskService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private IdentityService identityService;

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

    //办理任务首先展示任务详情
    @RequestMapping("/view/task/{taskId}")
    public String viewTask(@PathVariable String taskId,Model model){
        //查询t_leave，封装leave对象
        //1.查询leave对象需要instaceId，根据taskid获取task对象，然后根据task对象的processInstanceId查询leave
        Task task=taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId=task.getProcessInstanceId();
        ProcessInstance processInstance=runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        String businessKey=processInstance.getBusinessKey();
        Leave leave=leaveMapper.findLeaveById(Long.parseLong(businessKey));
        //task对象
        leave.setTask(task);

        //开发底层mapper接口
        // leaveMapper.getLeaveByProcessInstanceId(processInstanceId);

        // 根据t_leave表中的userId查询userName(act_id_user)
        org.activiti.engine.identity.User actUser=identityService.createUserQuery().userId(leave.getUserId()).singleResult();
        leave.setUserName(actUser.getFirstName());
        model.addAttribute("leave",leave);
        //如果task_def_key 为 modifyApply，跳转activiti/leave/modify-apply.jsp
        if(task.getTaskDefinitionKey().equals("modifyApply")){
            return "activiti/leave/modifyApply";
        }
        return "activiti/leave/taskVerify";

    }

    //办理任务
    @RequestMapping("/task/complete/{taskId}")
    public String taskComplete(@PathVariable("taskId") String taskId, HttpServletRequest request, RedirectAttributes redirectAttributes){
        Map<String,Object> maps=new HashMap<>();
             String defKey=request.getParameter("defKey");
             try {
                 if(StringUtils.isNotEmpty(defKey)&&("leaveBack".equals(defKey))){
                    String realityStartTime=request.getParameter("realityStartTime");
                    String realityEndTime=request.getParameter("realityEndTime");
                    //真实开始结束时间留给监听器做
                     maps.put("realityStartTime",realityStartTime);
                     maps.put("realityEndTime",realityEndTime);
                 }else {
                     String verify=request.getParameter("verify");
                     //verify必须为boolean值，否则抛出异常
                     Object value= BooleanUtils.toBoolean(verify);
                     maps.put(defKey,value);
                 }
                 //完成任务
                 taskService.complete(taskId,maps);
                 redirectAttributes.addFlashAttribute("message","完成任务");
             }catch (ActivitiException e){
                    redirectAttributes.addFlashAttribute("message","完成任务失败");
             }
             return "redirect:/process/task/list";
    }

    @RequestMapping(value = "/task/modify/{taskId}",method = RequestMethod.POST)
    public String modifyApply(@PathVariable String taskId,Leave leave,HttpServletRequest request,RedirectAttributes redirectAttributes){
        String reApply=request.getParameter("reApply");
        Map<String,Object> maps=new HashMap<>();
        Boolean value =BooleanUtils.toBoolean(reApply);
        maps.put("modifyApply",value);
        try {
            taskService.complete(taskId, maps);
            // //更新请假数据到t_leave表
            if (value) {
                leaveMapper.update(leave);
            }
            redirectAttributes.addFlashAttribute("message","操作成功");
        }catch (ActivitiException e){
            redirectAttributes.addFlashAttribute("message","操作失败");
        }
        return "redirect:/process/task/list";
    }

}
