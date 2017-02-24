package com.lsy.mapper;

import com.lsy.pojo.DeviceWork;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2017/2/23 0023.
 */
public interface WorkOutMapper {
    void save(DeviceWork deviceWork);

    void updateCost(@Param("totalPrice")float total, @Param("preCost")float preCost, @Param("lastCost")float lastCost, @Param("id")Integer id);
}
