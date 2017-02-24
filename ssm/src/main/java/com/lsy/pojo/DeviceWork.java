package com.lsy.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/2/22 0022.
 */
@Data
public class DeviceWork {
    private Integer id;
    private String companyName;
    private String tel;
    private String linkMan;
    private String address;
    private String telepohone;
    private String cardNum;
    private float totalPrice;
    private float preCost;
    private float lastCost;
    private String serialNumber;
    private Timestamp createTime;
    private String state;
    private String beginDate;
    private String lastDate;
    private Integer totalDays;
}
