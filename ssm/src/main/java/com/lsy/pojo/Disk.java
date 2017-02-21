package com.lsy.pojo;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
@Data
public class Disk {
    public static final String FILE_TYPE="file";
    public static final String DIRECTORY_TYPE="directory";

    private Integer id;
    private String sourceName;
    private String name;
    private String size;
    private String type;
    private Timestamp createTime;
    private String createUser;
    private Integer fid;
}
