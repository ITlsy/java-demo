package com.lsy.service.impl;

import com.lsy.mapper.DeviceMapper;
import com.lsy.pojo.Device;
import com.lsy.service.DeviceService;
import com.lsy.shiro.ShiroUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/13 0013.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    private Logger logger= LoggerFactory.getLogger(DeviceServiceImpl.class);
    @Autowired
    private DeviceMapper deviceMapper;

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
}
