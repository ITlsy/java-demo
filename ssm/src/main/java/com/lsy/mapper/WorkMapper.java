package com.lsy.mapper;

import com.lsy.pojo.DeviceWork;
import com.lsy.pojo.Work;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22 0022.
 */
public interface WorkMapper {
    List<Work> findAll();

    Work findById(Integer id);



    void updateCurrentNum(Work work);


}
