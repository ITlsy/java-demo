package com.lsy.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/1/20 0020.
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String useName;
    private String email;
    private String phone;
    private String password;

}
