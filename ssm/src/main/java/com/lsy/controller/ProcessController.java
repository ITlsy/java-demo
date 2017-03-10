package com.lsy.controller;

import com.lsy.dto.Process;
import com.lsy.shiro.ShiroUtil;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        System.out.println(processList);
        return "activiti/process/taskList";

    }
}
