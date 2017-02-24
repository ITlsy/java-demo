package com.lsy.mapper;

import com.lsy.pojo.DeviceWorkDetail;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23 0023.
 */
public interface WorkDetailMapper {
    void batchSave(List<DeviceWorkDetail> detailList);
}
