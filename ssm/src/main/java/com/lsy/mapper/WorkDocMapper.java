package com.lsy.mapper;

import com.lsy.pojo.DeviceWorkDocs;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23 0023.
 */
public interface WorkDocMapper {
    void batchSave(List<DeviceWorkDocs> workDocList);
}
