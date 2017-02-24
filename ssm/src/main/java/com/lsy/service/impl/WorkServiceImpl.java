package com.lsy.service.impl;

import com.google.common.collect.Lists;
import com.lsy.dto.DeviceWorkDto;
import com.lsy.exception.ServiceException;
import com.lsy.mapper.*;
import com.lsy.pojo.DeviceWork;
import com.lsy.pojo.DeviceWorkDetail;
import com.lsy.pojo.DeviceWorkDocs;
import com.lsy.pojo.Work;
import com.lsy.service.WorkService;
import com.lsy.shiro.ShiroUtil;
import com.lsy.util.SerialNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */
@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkMapper workMapper;
    @Autowired
    private WorkOutMapper workOutMapper;
    @Autowired
    private WorkDetailMapper workDetailMapper;
    @Autowired
    private WorkDocMapper workDocMapper;
    @Autowired
    private FinanceMapper financeMapper;
    @Override
    public List<Work> findAllWork() {
        return workMapper.findAll();
    }

    @Override
    public Work findWorkById(Integer id) {
        return workMapper.findById(id);
    }

    @Override
    public String saveWork(DeviceWorkDto deviceWorkDto) {
        //保存合同
        DeviceWork deviceWork=new DeviceWork();
        deviceWork.setCompanyName(deviceWorkDto.getCompanyName());
        deviceWork.setAddress(deviceWorkDto.getAddress());
        deviceWork.setCardNum(deviceWorkDto.getCardNum());
        deviceWork.setTel(deviceWorkDto.getTel());
        deviceWork.setLastCost(0F);
        deviceWork.setTelepohone(deviceWorkDto.getTelephone());
        deviceWork.setLinkMan(deviceWorkDto.getLinkMan());
        deviceWork.setPreCost(0F);
        deviceWork.setTotalPrice(0F);
        deviceWork.setLastDate(deviceWorkDto.getLastDate());
        deviceWork.setBeginDate(deviceWorkDto.getBeginDate());
        deviceWork.setTotalDays(deviceWorkDto.getTotalDays());
        deviceWork.setSerialNumber(SerialNumberUtil.getSerialNumber());
        System.out.println(deviceWork);
        workOutMapper.save(deviceWork);

        //保存合同详情
        List<DeviceWorkDto.WorkArrayBean> workArray=deviceWorkDto.getWorkArray();
        List<DeviceWorkDetail> detailList= Lists.newArrayList();
        float total=0f;
        for (DeviceWorkDto.WorkArrayBean bean:workArray){
            Work work=workMapper.findById(bean.getId());
            if (work.getCurrentNum()<bean.getNum()){
                throw new ServiceException(work.getWorkName()+"数量不足");
            }else {
                work.setCurrentNum(work.getCurrentNum() - bean.getNum());
                workMapper.updateCurrentNum(work);
            }

            DeviceWorkDetail  deviceWorkDetail=new DeviceWorkDetail();
            deviceWorkDetail.setWorkName(bean.getName());
            deviceWorkDetail.setWorkPrice(bean.getPrice());
            deviceWorkDetail.setOutNum(bean.getNum());
            deviceWorkDetail.setTotalPrice(bean.getTotal());
            deviceWorkDetail.setWorkId(bean.getId());
            detailList.add(deviceWorkDetail);
            total+=bean.getTotal();
        }
        if(!detailList.isEmpty()){
           workDetailMapper.batchSave(detailList);
        }
        //计算合同总价及预付款、尾款
        total=total*deviceWork.getTotalDays();
        float preCost=total*0.3f;
        float lastCost=total-preCost;
        workOutMapper.updateCost(total,preCost,lastCost,deviceWork.getId());
        //保存文件
        List<DeviceWorkDto.DocBean> docBeanList=deviceWorkDto.getFileArray();
        List<DeviceWorkDocs> workDocList=Lists.newArrayList();
        for (DeviceWorkDto.DocBean doc: docBeanList){
            DeviceWorkDocs workDoc=new DeviceWorkDocs();
            workDoc.setWorkId(deviceWork.getId());
            workDoc.setSourceName(doc.getSourceName());
            workDoc.setNewName(doc.getNewName());
            workDocList.add(workDoc);
        }
        if(!workDocList.isEmpty()){
            workDocMapper.batchSave(workDocList);
        }
        //4. 写入财务流水
        return deviceWork.getSerialNumber();
    }
}
