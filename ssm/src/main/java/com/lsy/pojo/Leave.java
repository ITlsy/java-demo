package com.lsy.pojo;

import com.lsy.dto.Process;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/9 0009.
 */
@Data
public class Leave extends Process implements Serializable {
    private static final long serialVersionUID=1L;
    private String startTime;
    private String endTime;
    private String realityStartTime;
    private String realityEndTime;
    private String leaveType;
    private String reason;
}
