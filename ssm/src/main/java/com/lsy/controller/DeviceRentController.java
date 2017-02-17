package com.lsy.controller;

import com.lsy.dto.AjaxResult;
import com.lsy.pojo.Device;
import com.lsy.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16 0016.
 */
@Controller
@RequestMapping("/device/rent")
public class DeviceRentController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public String list(){
        return "device/rent/list";
    }

    /*
    * 新建租赁合同
    * @return
     */
    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String newRent(Model model){
        List<Device> deviceList=deviceService.findAllDevice();
        model.addAttribute("deviceList",deviceList);
    return "device/rent/new";
    }

    /**
     * 根据设备ID查找设备信息
     * @param id
     * @return
     */
    @GetMapping("/device.json")
    @ResponseBody
    public AjaxResult deviceJson(Integer id){
        Device device=deviceService.findDeviceById(id);
        if(device==null){
            return new AjaxResult(AjaxResult.ERROR,"设备不存在");
        }else {
            System.out.println(device);
            return new AjaxResult(device);
        }
    }

}
