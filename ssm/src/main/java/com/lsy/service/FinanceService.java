package com.lsy.service;

import com.lsy.pojo.Finance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/23 0023.
 */
public interface FinanceService {
    List<Finance> findByQueryParam(Map<String,Object> queryParam);

    Long count();

    Long filterCount(Map<String,Object> queryParam);

    void findConfirmById(Integer id);

    List<Map<String,Object>> findPieDataByDay(String today, String type);

    List<Finance> findByCreateDate(String today);
}
