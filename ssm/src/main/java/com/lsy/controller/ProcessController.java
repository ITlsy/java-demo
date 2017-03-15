package com.lsy.controller;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.lsy.dto.Process;
import com.lsy.shiro.ShiroUtil;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
@Controller
@RequestMapping("/process")
public class ProcessController {
    Logger logger= LoggerFactory.getLogger(Process.class);

    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @GetMapping("/apply")
    public String processApply(){
        return "activiti/process/processList";
    }

    @RequestMapping("/task/list")
    public String taskList(Model model){
        String userId= ShiroUtil.getCurrentUserId().toString();
        List<Task> taskList=new ArrayList<>();
        List<Process> processList=new ArrayList<>();
        List<Task> todoList=taskService.createTaskQuery()
                .taskAssignee(userId).orderByTaskCreateTime()
                .asc().list();

        List<Task> unTodoTask=taskService.createTaskQuery()
                .taskCandidateUser(userId).orderByTaskCreateTime()
                .asc().list();

        taskList.addAll(todoList);
        taskList.addAll(unTodoTask);

        for(Task task:taskList){
            String proInstanceId=task.getProcessInstanceId();
            HistoricProcessInstance hisInstance=historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(proInstanceId).singleResult();

            User actUser=identityService.createUserQuery()
                    .userId(hisInstance.getStartUserId()).singleResult();

            Process process=new Process();
            process.setUserName(actUser.getFirstName());
            process.setTask(task);
            process.setHistoricProcessInstance(hisInstance);
            ProcessInstance processInstance=runtimeService.createProcessInstanceQuery()
                    .processInstanceId(proInstanceId)
                    .singleResult();

            ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(processInstance.getProcessDefinitionId()).singleResult();

            process.setProcessDefinitionName(processDefinition.getName());
            processList.add(process);

        }
        model.addAttribute("processList",processList);
        return "activiti/process/taskList";

    }

    //签收任务
    @RequestMapping("/claim/task/{taskId}")
    public String taskClaim(@PathVariable String taskId, RedirectAttributes redirectAttributes){
        String userId=ShiroUtil.getCurrentUserId().toString();
        try{
           taskService.claim(taskId,userId);
           redirectAttributes.addFlashAttribute("message","签收成功");
        }catch (ActivitiException e){
            redirectAttributes.addFlashAttribute("message","签收失败");

        }
        return "redirect:/process/task/list";

    }

    @GetMapping("/myRunning/list")
    public String myRunningProcess(Model model){
        com.lsy.pojo.User user=ShiroUtil.getCurrentUser();
        //1.通过当前登录的userId去查询historyProcessInstance
        List<HistoricProcessInstance> hisProInstanceList=historyService.createHistoricProcessInstanceQuery()
                .startedBy(user.getId().toString()).list();
        List<Process> processList=new ArrayList<>();
        for (HistoricProcessInstance hisInstance:hisProInstanceList){
            //2.根据endtime==null 筛选正在运行的流程
            if (hisInstance.getEndTime()!=null){
                continue;
            }
            Process process=new Process();
            process.setProcessInstanceId(hisInstance.getId());
            ProcessDefinition proDef=repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(hisInstance.getProcessDefinitionId()).singleResult();

            //设置流程名称
            process.setProcessDefinitionName(proDef.getName());
            process.setApplyTime(format.format(hisInstance.getStartTime()));
            process.setUserName(user.getUsername());
            //查询任务信息
            Task task=taskService.createTaskQuery().executionId(hisInstance.getId()).singleResult();
            process.setTask(task);
            processList.add(process);
        }
        model.addAttribute("processList",processList);
        return "activiti/process/myRunningProcess";
    }


    //我的发起历史流程和我参与的历史流程
    @RequestMapping("/history/list")
    public String historyProcess(Model model){
    List<Process> processList=new ArrayList<>();
    List<Process> processTaskList= new ArrayList<>();
    //我的发起历史流程
        com.lsy.pojo.User user=ShiroUtil.getCurrentUser();
        List<HistoricProcessInstance> hisProInstanceList=historyService.createHistoricProcessInstanceQuery()
                .startedBy(user.getId().toString()).finished().list();

        for(HistoricProcessInstance hisProInstance:hisProInstanceList){
            Process process=new Process();
            process.setHistoricProcessInstance(hisProInstance);
            ProcessDefinition proDef=repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(hisProInstance.getProcessDefinitionId()).singleResult();

                process.setProcessDefinitionName(proDef.getName());
                process.setUserName(user.getUsername());
                processList.add(process);
        }

        //2.我参与的历史流程
        List<HistoricTaskInstance> hisTaskList=historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(user.getId().toString()).finished().list();

        for(HistoricTaskInstance hisTask:hisTaskList){
            Process process=new Process();
            //1.获取参与的历史流程，根据hisTaskInstance的processInstanceId;
            HistoricProcessInstance hisProInstance=historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(hisTask.getProcessInstanceId()).singleResult();
            process.setHistoricProcessInstance(hisProInstance);
            //2.根据流程定义id获得流程定义的name
            ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery()
                    .processDefinitionId(hisProInstance.getProcessDefinitionId()).singleResult();
            process.setProcessDefinitionName(processDefinition.getName());
            //3.根据发起者的userId获得userName
            User actUser=identityService.createUserQuery()
                    .userId(hisProInstance.getStartUserId()).singleResult();
            process.setUserName(actUser.getFirstName());
            processTaskList.add(process);

        }
        model.addAttribute("processTaskList",processTaskList);
        model.addAttribute("processList",processList);
        return "activiti/process/finishedProcess";
    }

    @GetMapping("/viewDetail/{processInstanceId}")
    public String instanceDetail(@PathVariable String processInstanceId,Model model){
        Process process=new Process();
        com.lsy.pojo.User user=ShiroUtil.getCurrentUser();
        HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        process.setHistoricProcessInstance(historicProcessInstance);
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(historicProcessInstance.getProcessDefinitionId()).singleResult();
        process.setProcessDefinition(definition);
        List<HistoricActivityInstance> actList=historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list();
        actList= Lists.newArrayList(Collections2.filter(actList, new Predicate<HistoricActivityInstance>() {
            @Override
            public boolean apply(HistoricActivityInstance historicActivityInstance) {
                return StringUtils.isNotEmpty(historicActivityInstance.getAssignee());
            }
        })
        );
        process.setActList(actList);
        model.addAttribute("process",process);
        return "activiti/process/viewProcess";

    }

    @GetMapping("/resource/{depId}")
    public void getResource(@PathVariable("depId") String depId, @RequestParam("resourceName") String resName, HttpServletResponse response){
        try {
            //通过接口读取
            InputStream resourceAsStream=repositoryService.getResourceAsStream(depId,resName);
            // 输出资源内容到相应对象
            byte[] b=new byte[1024];
            int len=-1;
            while ((len=resourceAsStream.read(b,0,1024))!=-1){
                response.getOutputStream().write(b,0,len);
            }
        }catch (IOException e){
        logger.error("png图片读取异常！");
        }

    }
}
