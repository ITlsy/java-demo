package com.lsy.service.impl;

import com.google.common.collect.Lists;
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

    //保存文件夹
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
        disk.setType(Disk.FILE_TYPE);
        disk.setSize(FileUtils.byteCountToDisplaySize(size));
       // disk.setCreateTime(DateTime.now().toString("yyyy-mdd HH:mm"));
        diskMapper.saveFolder(disk);

    }

    @Override
    public InputStream downloadFile(Integer id) throws FileNotFoundException {
       Disk disk=diskMapper.findById(id);
       if(disk==null||Disk.DIRECTORY_TYPE.equals(disk.getType())){
           return null;
       }else {
           FileInputStream inputStream=new FileInputStream(new File(new File(savePath),disk.getName()));
           return inputStream;
       }
    }

    @Override
    public Disk findById(Integer id) {
        return diskMapper.findById(id);
    }

    @Override
    @Transactional
    public void delById(Integer id) {
        Disk disk=findById(id);
        if(disk!=null){
            if(Disk.FILE_TYPE.equals(disk.getType())){
                //删除文件
                File file=new File(savePath,disk.getName());
                file.delete();
                //删除数据库中的记录
                diskMapper.del(id);
            }else {
            List<Disk> diskList=diskMapper.findAll();//所有的记录
            List<Integer> delIdList= Lists.newArrayList();//即将删除的id
                findDelId(diskList,delIdList,id);
                delIdList.add(id);
                //批量删除
                diskMapper.batchDel(delIdList);
            }
        }

    }

    private void findDelId(List<Disk> diskList, List<Integer> delIdList, Integer id) {
       for (Disk disk:diskList){
           if (disk.getFid().equals(id)){
               delIdList.add(disk.getId());
               if (disk.getType().equals(Disk.DIRECTORY_TYPE)) {

                   findDelId(diskList,delIdList,disk.getId());

               }else {
                   File file=new File(savePath,disk.getName());
                   file.delete();
               }
           }
       }
    }
}
