package com.lsy.mapper;

import com.lsy.pojo.DeviceRentDocs;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
public interface DeviceRentDocsMapper {
    void batchSave(List<DeviceRentDocs> rentDocList);

    List<DeviceRentDocs> findByRentId(Integer id);
}
