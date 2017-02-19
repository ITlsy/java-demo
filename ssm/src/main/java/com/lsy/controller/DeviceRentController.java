package com.lsy.controller;

import com.lsy.dto.AjaxResult;
import com.lsy.dto.DeviceRentDto;
import com.lsy.exception.NotFoundException;
import com.lsy.pojo.Device;
import com.lsy.pojo.DeviceRent;
import com.lsy.pojo.DeviceRentDetail;
import com.lsy.pojo.DeviceRentDocs;
import com.lsy.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//保存合同
    @PostMapping("/new")
    @ResponseBody
    public AjaxResult saveRent(@RequestBody DeviceRentDto deviceRentDto){
        String serialNumber=deviceService.saveRent(deviceRentDto);
        AjaxResult ajaxResult=new AjaxResult();
        ajaxResult.setData(serialNumber);
        ajaxResult.setStatus(AjaxResult.SUCCESS);
        return ajaxResult;

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

    /**
     * 根据流水号显示合同详情
     * @param serialNumber
     * @return
     */
    @GetMapping("/{serialNumber:\\d+}")
    public String showDeviceRent(@PathVariable String serialNumber,Model model) {
        //1.查询合同对象
        DeviceRent deviceRent = deviceService.findDeviceRentBySerialNumber(serialNumber);
        if(deviceRent == null) {
            throw new NotFoundException();
        } else {
            //2.查询合同详情列表
            List<DeviceRentDetail> detailList = deviceService.findDeviceRentDetailByRentId(deviceRent.getId());
            //3.查询合同文件列表
            List<DeviceRentDocs> docList = deviceService.findDeviceRentDocsByRentId(deviceRent.getId());

            model.addAttribute("rent",deviceRent);
            model.addAttribute("detailList",detailList);
            model.addAttribute("docList",docList);

            return "device/rent/show";
        }
    }

}
