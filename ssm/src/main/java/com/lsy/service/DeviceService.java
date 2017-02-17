package com.lsy.service;

import com.lsy.pojo.Device;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/13 0013.
 */
public interface DeviceService {
    void addDevice(Device device);

    List<Device> findDeviceByPage(String start, String length);

    Long count();

    List<Device> findAll();

    List<Device> findDeviceBySearchParam(Map<String, Object> searchParam);

    Long countBySearchParam(Map<String, Object> searchParam);

    void delDevice(Integer id);

    List<Device> findAllDevice();

    Device findDeviceById(Integer id);
}
