package com.lsy.controller;

import com.lsy.dto.AjaxResult;
import com.lsy.dto.DeviceRentDto;
import com.lsy.exception.NotFoundException;
import com.lsy.pojo.Device;
import com.lsy.pojo.DeviceRent;
import com.lsy.pojo.DeviceRentDetail;
import com.lsy.pojo.DeviceRentDocs;
import com.lsy.service.DeviceService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipOutputStream;

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
        try {
            String serialNumber = deviceService.saveRent(deviceRentDto);
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.setData(serialNumber);
            ajaxResult.setStatus(AjaxResult.SUCCESS);
            return ajaxResult;
        }catch (Exception e){
            return new AjaxResult(AjaxResult.ERROR,e.getMessage());
        }

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

    /*
    * jsp-servlet的文件下载
    */
    /*@GetMapping("/doc")
    public void downloadFile(Integer id, HttpServletResponse response) throws IOException{
        InputStream inputStream=deviceService.downloadFile(id);
        if(inputStream==null){
            throw new NotFoundException();
        }else {
            DeviceRentDocs rentDoc=deviceService.findDeviceRentDocById(id);
            //将文件下载标记为二进制
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
           *//* //设置文件的总大小
            response.setContentLengthLong(file.length());*//*
            //    更改文件下载的名称
            String fileName=rentDoc.getSourceName();
            fileName=new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            //设置弹出对话框的文件名称
            response.setHeader("Content-Disposition","attachment;fileName=\""+fileName+"\"");

            OutputStream outputStream=response.getOutputStream();
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
    }*/

    /*
    *springMvc框架的文件下载
    */
    @GetMapping("/doc")
    @ResponseBody
    public ResponseEntity<InputStreamResource> downloadFile(Integer id) throws IOException {
        InputStream inputStream=deviceService.downloadFile(id);
        if(inputStream==null){
            throw  new NotFoundException();
        }else {
            DeviceRentDocs doc=deviceService.findDeviceRentDocById(id);
            String fileName=doc.getSourceName();
            HttpHeaders headers=new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",fileName, Charset.forName("UTF-8"));
            return new ResponseEntity<InputStreamResource>(new InputStreamResource(inputStream),headers, HttpStatus.OK);
        }
    }

    /*
    *合同文件的打包下载
    */
    @GetMapping("/doc/zip")
    public void downloadZipFile(Integer id,HttpServletResponse response) throws IOException {
        DeviceRent deviceRent=deviceService.findDeviceRentById(id);
        if (deviceRent==null){
            throw new NotFoundException();
        }else {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
            String fileName=deviceRent.getCompanyName()+".zip";
            fileName=new String(fileName.getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition","attachment;fileName=\""+fileName+"\"");
            OutputStream outputStream=response.getOutputStream();
            ZipOutputStream zipOutputStream=new ZipOutputStream(outputStream);
            deviceService.downloadZipFile(deviceRent,zipOutputStream);
        }
    }

    @GetMapping("/load")
    public AjaxResult load(){

    }
}
