package com.lsy.mapper;

import com.lsy.pojo.Finance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/23 0023.
 */
public interface FinanceMapper {
    void save(Finance finance);

    List<Finance> findByQueryParam(Map<String,Object> queryParam);

    Long count();

    Long filterCount(Map<String,Object> queryParam);

    Finance findById(Integer id);

    void updateState(Finance finance);

    List<Map<String,Object>> findPiaData(@Param("today") String today, @Param("type") String type);

    List<Finance> findByCreateDate(String today);
}
