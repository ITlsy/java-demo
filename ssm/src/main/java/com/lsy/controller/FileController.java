package com.lsy.controller;

import com.google.common.collect.Maps;
import com.lsy.dto.AjaxResult;
import com.lsy.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
@Controller
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult ajaxFileUpload(MultipartFile file){
        try {
            String fileName=fileService.uploadFile(file.getOriginalFilename(),file.getContentType(),file.getInputStream());
            Map<String,String> map= Maps.newHashMap();
            map.put("newFileName",fileName);
            map.put("sourceFileName",file.getOriginalFilename());
            return new AjaxResult(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(AjaxResult.ERROR,e.getMessage());
        }
    }

}
