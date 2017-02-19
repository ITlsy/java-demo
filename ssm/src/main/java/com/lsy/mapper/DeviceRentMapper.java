package com.lsy.mapper;

import com.lsy.pojo.DeviceRent;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public interface DeviceRentMapper {
    void save(DeviceRent deviceRent);

    void updateCost(@Param("totalPrice") float total,@Param("preCost") float preCost, @Param("lastCost") float lastCost,@Param("id") Integer id);

    DeviceRent findSerialNumber(String serialNumber);
}
