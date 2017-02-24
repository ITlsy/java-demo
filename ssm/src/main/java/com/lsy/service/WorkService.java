package com.lsy.service;

import com.lsy.dto.DeviceWorkDto;
import com.lsy.pojo.Work;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */
public interface WorkService {
    List<Work> findAllWork();

    Work findWorkById(Integer id);

    String saveWork(DeviceWorkDto deviceWorkDto);
}
