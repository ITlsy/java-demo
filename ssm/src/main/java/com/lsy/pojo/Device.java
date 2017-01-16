package com.lsy.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/13 0013.
 */
@Data
public class Device implements Serializable {
    private Integer id;
    private String name;
    private String unit;
    private Integer totalNum;
    private Integer currentNum;
    private Float price;


}
