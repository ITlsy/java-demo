package com.lsy.service.impl;

import com.google.common.collect.Lists;

import com.lsy.dto.DeviceRentDto;
import com.lsy.exception.ServiceException;
import com.lsy.mapper.DeviceMapper;
import com.lsy.mapper.DeviceRentDetailMapper;
import com.lsy.mapper.DeviceRentDocsMapper;
import com.lsy.mapper.DeviceRentMapper;
import com.lsy.pojo.Device;
import com.lsy.pojo.DeviceRent;
import com.lsy.pojo.DeviceRentDetail;
import com.lsy.pojo.DeviceRentDocs;
import com.lsy.service.DeviceService;
import com.lsy.shiro.ShiroUtil;
import com.lsy.util.SerialNumberUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Administrator on 2017/1/13 0013.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    private Logger logger= LoggerFactory.getLogger(DeviceServiceImpl.class);
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceRentDetailMapper rentDetailMapper;
    @Autowired
    private DeviceRentMapper rentMapper;
    @Autowired
    private DeviceRentDocsMapper rentDocsMapper;

    @Override
    public void addDevice(Device device) {
        //让当前库存数量和总数量相同
        device.setCurrentNum(device.getTotalNum());
        deviceMapper.addDevice(device);
        logger.info("{}添加了新设备{}",ShiroUtil.getCurrentUserName(),device.getName());
    }

    @Override
    public List<Device> findDeviceByPage(String start, String length) {
        return deviceMapper.findDeviceByPage(start,length);
    }

    @Override
    public Long count() {
        return deviceMapper.count();
    }

    @Override
    public List<Device> findAll() {
        return deviceMapper.findAll();
    }

    @Override
    public List<Device> findDeviceBySearchParam(Map<String, Object> searchParam) {
        return deviceMapper.findDeviceBySearchParam(searchParam);
    }

    @Override
    public Long countBySearchParam(Map<String, Object> searchParam) {
        return deviceMapper.countBySearchParam(searchParam);
    }

    @Override
    public void delDevice(Integer id) {
        deviceMapper.delDevice(id);
    }

    @Override
    public List<Device> findAllDevice() {
        return deviceMapper.findAll();
    }

    @Override
    public Device findDeviceById(Integer id) {
        return deviceMapper.findById(id);
    }

    @Override
    @Transactional
    public String saveRent(DeviceRentDto deviceRentDto) {
 // 1.保存合同
        DeviceRent deviceRent=new DeviceRent();
        deviceRent.setCompanyName(deviceRentDto.getCompanyName());
        deviceRent.setLinkMan(deviceRentDto.getLinkMan());
        deviceRent.setCardNum(deviceRentDto.getCardNum());
        deviceRent.setTel(deviceRentDto.getTel());
        deviceRent.setAddress(deviceRentDto.getAddress());
        deviceRent.setFax(deviceRentDto.getFax());
        deviceRent.setRentDate(deviceRentDto.getRentDate());
        deviceRent.setBackDate(deviceRentDto.getBackDate());
        deviceRent.setTotalDays(deviceRentDto.getTotalDays());
        deviceRent.setTotalPrice(0f);
        deviceRent.setPreCost(0f);
        deviceRent.setLastCost(0f);
        deviceRent.setSerialNumber(SerialNumberUtil.getSerialNumber());
        deviceRent.setCreateUser(ShiroUtil.getCurrentUserName());

        rentMapper.save(deviceRent);

        //2. 保存合同详情
        List<DeviceRentDto.DeviceArrayBean> deviceArray=deviceRentDto.getDeviceArray();
        List<DeviceRentDetail> detailList= Lists.newArrayList();
        float total=0f;
        for (DeviceRentDto.DeviceArrayBean bean:deviceArray){
            Device device=deviceMapper.findById(bean.getId());
            if(device.getCurrentNum()<bean.getNum()){
                throw new ServiceException(device.getName()+"库存不足");
            }else {
                device.setCurrentNum(device.getCurrentNum()-bean.getNum());
                deviceMapper.updateCurrentNum(device);
            }
            DeviceRentDetail deviceRentDetail=new DeviceRentDetail();
            deviceRentDetail.setDeviceName(bean.getName());
            deviceRentDetail.setDeviceUnit(bean.getUnit());
            deviceRentDetail.setDevicePrice(bean.getPrice());
            deviceRentDetail.setNum(bean.getNum());
            deviceRentDetail.setTotalPrice(bean.getTotal());
            deviceRentDetail.setRentId(deviceRent.getId());

            detailList.add(deviceRentDetail);
            total +=bean.getTotal();
        }
        if(!detailList.isEmpty()){
            rentDetailMapper.batchSave(detailList);
        }
        //计算合同总价及预付款、尾款
        total=total*deviceRent.getTotalDays();
        float preCost=total*0.3f;
        float lastCost=total-preCost;
        rentMapper.updateCost(total,preCost,lastCost,deviceRent.getId());
        //3. 保存文件
        List<DeviceRentDto.DocBean> docBeanList = deviceRentDto.getFileArray();
        List<DeviceRentDocs> rentDocList = Lists.newArrayList();
        for(DeviceRentDto.DocBean doc : docBeanList) {
            DeviceRentDocs rentDoc = new DeviceRentDocs();
            rentDoc.setRentId(deviceRent.getId());
            rentDoc.setNewName(doc.getNewName());
            rentDoc.setSourceName(doc.getSourceName());

            rentDocList.add(rentDoc);
        }
        if(!rentDocList.isEmpty()) {
            rentDocsMapper.batchSave(rentDocList);
        }

        //4. 写入财务流水
       return deviceRent.getSerialNumber();
    }

    @Override
    public DeviceRent findDeviceRentBySerialNumber(String serialNumber) {
        return rentMapper.findSerialNumber(serialNumber);
    }

    /**
     * 根据设备租赁合同ID查询详情列表
     * @param id
     * @return
     */
    @Override
    public List<DeviceRentDetail> findDeviceRentDetailByRentId(Integer id) {
        return rentDetailMapper.findByRentId(id);
    }

    @Override
    public List<DeviceRentDocs> findDeviceRentDocsByRentId(Integer id) {
        return rentDocsMapper.findByRentId(id);
    }

    @Override
    public InputStream downloadFile(Integer docId) throws IOException {
        DeviceRentDocs doc=rentDocsMapper.findById(docId);
        if (doc==null) {
            return null;
        }else {
            File file=new File(("F:/upload"),doc.getNewName());
            if (file.exists()){
                return new FileInputStream(file);
            }else {
                return null;
            }

        }
    }

    @Override
    public DeviceRentDocs findDeviceRentDocById(Integer id) {
        return rentDocsMapper.findById(id);
    }

    @Override
    public DeviceRent findDeviceRentById(Integer id) {
        return rentMapper.findById(id);
    }

    @Override
    public void downloadZipFile(DeviceRent deviceRent, ZipOutputStream zipOutputStream) throws IOException {
        //查找合同有多少个附件
        List<DeviceRentDocs> rentDocsList=findDeviceRentDocsByRentId(deviceRent.getId());
        for (DeviceRentDocs doc:rentDocsList){
            //entry是每个压缩包里的文件
            ZipEntry entry=new ZipEntry(doc.getSourceName());
            zipOutputStream.putNextEntry(entry);

            InputStream inputStream=downloadFile(doc.getId());
            IOUtils.copy(inputStream,zipOutputStream);
            inputStream.close();

        }
        zipOutputStream.closeEntry();
        zipOutputStream.flush();
        zipOutputStream.close();
    }


}
