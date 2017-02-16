package com.lsy.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/1/23 0023.
 */
@Data
public class Device implements Serializable {
    private Integer id;
    private String deviceName;
    private Integer totalNum;
    private Integer currentNum;
    private String unit;
    private float rentMoney;
    private Timestamp updateTime;
    private Timestamp createTime;
}
