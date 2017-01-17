package com.lsy.controller;

import com.google.common.collect.Maps;
import com.lsy.pojo.Device;
import com.lsy.service.DeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/13 0013.
 */
@Controller
@RequestMapping("/setting/device")
public class SettingDeviceController {
    private Logger logger= LoggerFactory.getLogger(SettingDeviceController.class);

    @Autowired
    private DeviceService deviceService;
    @GetMapping
       public String list(Model model){
      /* List<Device> deviceList= deviceService.findAll();
        model.addAttribute("deviceList",deviceList);*/
        return "setting/device/list";
    }

    @PostMapping("/load")
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request){
        String draw=request.getParameter("draw");
        String start=request.getParameter("start");
        String length=request.getParameter("length");
        String orderIndex=request.getParameter("order[0][column]");
        String orderType=request.getParameter("order[0][dir]");
        String orderColumn=request.getParameter("column["+orderIndex+"][name]");
        String deviceName=request.getParameter("deviceName");

        Map<String,Object> searchParam=Maps.newHashMap();
        searchParam.put("start",start);
        searchParam.put("length",length);
        searchParam.put("orderType",orderType);
        searchParam.put("orderColumn",orderColumn);
        searchParam.put("deviceName",deviceName);

        List<Device> deviceList=deviceService.findDeviceBySearchParam(searchParam);
                //deviceService.findDeviceByPage(start,length);//deviceService.findAll();
        Long count=deviceService.count();
        Long filterCount=deviceService.countBySearchParam(searchParam);
        Map<String,Object> resultMap= Maps.newHashMap();
        resultMap.put("draw",draw);
        resultMap.put("recordsTotal",count);
        resultMap.put("recordsFiltered",filterCount);
        resultMap.put("data",deviceList);
        return resultMap;
    }

    @GetMapping("/add")
    public String addDevice(){
           return "/setting/device/add";
    }

    @PostMapping("/add")
    public String addDevice(Device device, RedirectAttributes redirectAttributes){
        deviceService.addDevice(device);
        redirectAttributes.addFlashAttribute("message","添加成功");
        return "redirect:/setting/device";
    }

    @GetMapping("/{id:\\d+}/del")
    @ResponseBody
    public String delDevice(@PathVariable Integer id){
        deviceService.delDevice(id);
        return "success";

    }



}
