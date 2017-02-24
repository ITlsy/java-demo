package com.lsy.controller;

import com.lsy.dto.AjaxResult;
import com.lsy.dto.DeviceWorkDto;
import com.lsy.pojo.Work;
import com.lsy.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/2/19 0019.
 */
@Controller
@RequestMapping("/device/work")
public class DeviceWorkController {

    @Autowired
    private WorkService workService;

    @GetMapping
    public String list(Model model) {
        return "device/work/list";
    }

    @GetMapping("/new")
    public String newWork(Model model){
        List<Work> workList=workService.findAllWork();
        model.addAttribute("workList",workList);
        return "/device/work/new";
    }
    //根据ID查询劳务信息
    @GetMapping("/work.json")
    @ResponseBody
    public AjaxResult workJson(Integer id){
        Work work=workService.findWorkById(id);
        if(work==null){
            return new AjaxResult(AjaxResult.ERROR,"设备不存在");
        }else {
            return new AjaxResult(work);
        }
    }

    @PostMapping("/new")
    @ResponseBody
    public AjaxResult saveWork(@RequestBody DeviceWorkDto deviceWorkDto){
        try {
            String serialNumber=workService.saveWork(deviceWorkDto);
            System.out.println(serialNumber);
            AjaxResult ajaxResult=new AjaxResult();
            ajaxResult.setData(serialNumber);
            ajaxResult.setStatus(AjaxResult.SUCCESS);
            return ajaxResult;
        }catch (Exception e) {
            return new AjaxResult(AjaxResult.SUCCESS,e.getMessage());
        }

    }

    //根据流水号显示合同详情
    @GetMapping("/{serialNumber:\\d+}")
    public String showWork(@PathVariable String serialNumber){
        return "device/work/show";

    }


}