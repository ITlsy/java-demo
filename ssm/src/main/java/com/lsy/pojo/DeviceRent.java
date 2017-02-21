package com.lsy.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
@Data
public class DeviceRent {
    private Integer id;
    private String companyName;
    private String linkMan;
    private String cardNum;
    private String tel;
    private String address;
    private String fax;
    private String rentDate;
    private String backDate;
    private Integer totalDays;
    //总租赁费
    private float totalPrice;
    private  float preCost;
    private float lastCost;
    private Timestamp createTime;
    private String createUser;
    private String serialNumber;
    private String state;
}
