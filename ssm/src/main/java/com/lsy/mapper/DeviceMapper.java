package com.lsy.mapper;

import com.lsy.pojo.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/13 0013.
 */
public interface DeviceMapper {
    void addDevice(Device device);

    List<Device> findDeviceByPage(@Param("start") String start,@Param("length") String length);

    Long count();

    List<Device> findAll();

    List<Device> findDeviceBySearchParam(Map<String, Object> searchParam);

    Long countBySearchParam(Map<String, Object> searchParam);

    void delDevice(Integer id);
}
