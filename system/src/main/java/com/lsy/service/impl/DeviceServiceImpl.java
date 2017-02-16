package com.lsy.service.impl;

import com.lsy.mapper.DeviceMapper;
import com.lsy.pojo.Device;
import com.lsy.service.DeviceService;
import com.lsy.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Override
    public List<Device> findAllDevice() {
        return deviceMapper.findAllDevice();
    }

    @Override
    public Page<Device> findDeviceByPageNo(Integer pageNo) {
        int total=deviceMapper.count().intValue();
        Page<Device> page=new Page<>(total,pageNo);
        List<Device> deviceList=deviceMapper.findByPage(page.getStart(),page.getPageSize());
        page.setItems(deviceList);
        return page;
    }
}
