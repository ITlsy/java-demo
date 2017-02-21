package com.lsy.service.impl;

import com.lsy.exception.ServiceException;
import com.lsy.mapper.DiskMapper;
import com.lsy.pojo.Disk;
import com.lsy.service.DiskService;
import com.lsy.shiro.ShiroUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
@Service
public class DiskServiceImpl implements DiskService {
    @Autowired
    private DiskMapper diskMapper;

    @Value("${upload.path}")
    private String savePath;
    @Override
    public List<Disk> findDiskByFid(Integer path) {
        return diskMapper.findByFid(path) ;
    }

    @Override
    public void saveFolder(Disk disk) {
        disk.setCreateUser(ShiroUtil.getCurrentUserName());
        disk.setType(Disk.DIRECTORY_TYPE);
        diskMapper.saveFolder(disk);
    }

//    保存文件
    @Override
    @Transactional
    public void uploadNewFile(Integer fid, MultipartFile file) {
        //存文件到磁盘
        String sourceName=file.getOriginalFilename();
        String newName= UUID.randomUUID().toString();
        Long size=file.getSize();
        if(sourceName.lastIndexOf(".")!=-1){
            newName+=sourceName.substring(sourceName.lastIndexOf("."));
        }
        try {
        File saveFile=new File(new File(savePath),newName);
        OutputStream outputStream=new FileOutputStream(saveFile);
        InputStream inputStream=file.getInputStream();
        IOUtils.copy(inputStream,outputStream);
        outputStream.flush();;
        outputStream.close();
        inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("文件保存至磁盘异常",e);
        }

        //保存数据库记录
        Disk disk=new Disk();
        disk.setFid(fid);
        disk.setSourceName(sourceName);
        disk.setCreateUser(ShiroUtil.getCurrentUserName());
        disk.setName(newName);
        disk.setType(Disk.DIRECTORY_TYPE);
        disk.setSize(FileUtils.byteCountToDisplaySize(size));
       // disk.setCreateTime(DateTime.now().toString("yyyy-mdd HH:mm"));
        diskMapper.saveFolder(disk);

    }
}
