package com.lsy.service.impl;

import com.lsy.mapper.DeviceMapper;
import com.lsy.pojo.Device;
import com.lsy.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/13 0013.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public void addDevice(Device device) {
        //让当前库存数量和总数量相同
        device.setCurrentNum(device.getTotalNum());
        deviceMapper.addDevice(device);
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
}