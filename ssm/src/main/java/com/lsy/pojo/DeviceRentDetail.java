package com.lsy.pojo;

import lombok.Data;

/**
 * Created by Administrator on 2017/2/18 0018.
 */
@Data
public class DeviceRentDetail {
    private Integer id;
    private String deviceName;
    private String deviceUnit;
    private float devicePrice;
    private Integer num;
    private float totalPrice;
    private Integer rentId;

}
