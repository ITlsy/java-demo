package com.lsy.controller;

import com.lsy.pojo.Device;
import com.lsy.service.DeviceService;
import com.lsy.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
    private Logger logger= LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(@RequestParam(required = false,defaultValue = "1") Integer p, Model model){
       // List<Device> deviceList=deviceService.findAllDevice();
        Page<Device> page=deviceService.findDeviceByPageNo(p);
        model.addAttribute("page",page);
       // model.addAttribute("deviceList",deviceList);
        return "device/device_list";

    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String newDevice(){
        return "device/device_out_add";

    }


}
