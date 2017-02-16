package com.lsy.service;

import com.lsy.pojo.Device;
import com.lsy.util.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
public interface DeviceService {
    List<Device> findAllDevice();

    Page<Device> findDeviceByPageNo(Integer pageNo);
}
