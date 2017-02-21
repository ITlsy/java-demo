package com.lsy.mapper;

import com.lsy.pojo.DeviceRent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public interface DeviceRentMapper {
    void save(DeviceRent deviceRent);

    void updateCost(@Param("totalPrice") float total,@Param("preCost") float preCost, @Param("lastCost") float lastCost,@Param("id") Integer id);

    DeviceRent findSerialNumber(String serialNumber);

    DeviceRent findById(Integer id);

    List<DeviceRent> findByQueryParam(Map<String, Object> queryParam);

    Long count();

    void updateState(DeviceRent deviceRent);
}
