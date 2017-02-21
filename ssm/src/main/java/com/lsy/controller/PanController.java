package com.lsy.controller;

import com.lsy.dto.AjaxResult;
import com.lsy.exception.ServiceException;
import com.lsy.pojo.Disk;
import com.lsy.service.DiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
@Controller
@RequestMapping("/pan")
public class PanController {

    @Autowired
    private DiskService diskService;
    @GetMapping
    public String list(@RequestParam(required = false,defaultValue = "0") Integer path, Model model){
        List<Disk> diskList=diskService.findDiskByFid(path);
        model.addAttribute("diskList",diskList);
        model.addAttribute("fid",path);
        return "pan/list";
    }
    //新建文件夹
    @PostMapping("/folder/new")
    @ResponseBody
    public AjaxResult saveFolder(Disk disk){
        diskService.saveFolder(disk);
        return new AjaxResult(AjaxResult.SUCCESS);
    }
//    文件上传(文件到磁盘)
    @RequestMapping("/upload")
    @ResponseBody
    public AjaxResult uploadFile(Integer fid,MultipartFile file){
        try {
            diskService.uploadNewFile(fid,file);
            return new AjaxResult(AjaxResult.SUCCESS);
        }catch (ServiceException e){
            return new AjaxResult(AjaxResult.ERROR,e.getMessage());
        }

    }
//    文件下载
    @GetMapping("/download")
    @ResponseBody
    public AjaxResult downloadFile(Integer id){

    }
}
