package com.lsy.mapper;

import com.lsy.pojo.DeviceRentDetail;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public interface DeviceRentDetailMapper {
    void batchSave(List<DeviceRentDetail> detailList);

    List<DeviceRentDetail> findByRentId(Integer rentId);
}
