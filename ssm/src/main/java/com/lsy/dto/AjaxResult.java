package com.lsy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Administrator on 2017/2/16 0016.
 */
@Data
@AllArgsConstructor
public class AjaxResult {
    public static final String SUCCESS="success";
    public static final String ERROR="error";

    private String status;
    private String message;
    private Object data;
    public AjaxResult(){}
    public AjaxResult(String status,String message){
        this.message=message;
        this.status=status;

    }
    public AjaxResult(Object data){
        this.status=SUCCESS;
        this.data=data;
    }
}
