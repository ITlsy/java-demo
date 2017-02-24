package com.lsy.service.impl;

import com.lsy.mapper.FinanceMapper;
import com.lsy.pojo.Finance;
import com.lsy.service.FinanceService;
import com.lsy.shiro.ShiroUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/23 0023.
 */
@Service
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    private FinanceMapper financeMapper;
    @Override
    public List<Finance> findByQueryParam(Map<String,Object> queryParam) {
      return financeMapper.findByQueryParam(queryParam);
    }

    @Override
    public Long count() {
        return financeMapper.count();
    }

    @Override
    public Long filterCount(Map<String,Object> queryParam) {
        return financeMapper.filterCount(queryParam);
    }

    //确认财务流水
    @Override
    public void findConfirmById(Integer id) {
        Finance finance=financeMapper.findById(id);
        if(finance!=null){
            finance.setState(Finance.STATE_OK);
            finance.setConfirmDate(DateTime.now().toString("yyyy-MM-dd"));
            finance.setConfirmUser(ShiroUtil.getCurrentUserName());
            financeMapper.updateState(finance);

        }
    }

    //日报表中显示饼图数据
    @Override
    public List<Map<String, Object>> findPieDataByDay(String today, String type) {
        return financeMapper.findPiaData(today,type);

    }

    @Override
    public List<Finance> findByCreateDate(String today) {
        return financeMapper.findByCreateDate(today);
    }
}
