package com.lsy.mapper;

import com.lsy.pojo.Device;
import com.lsy.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
public interface DeviceMapper {
    List<Device> findAllDevice();

    Long count();

    List<Device> findByPage(@Param("start") int start,@Param("pageSize") int pageSize);
}
