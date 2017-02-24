package com.lsy.pojo;

import lombok.Data;

/**
 * Created by Administrator on 2017/2/22 0022.
 */
@Data
public class Work {
    private Integer id;
    private String workName;
    private float workPrice;
    private Integer currentNum;
    private Integer totalNum;
}
