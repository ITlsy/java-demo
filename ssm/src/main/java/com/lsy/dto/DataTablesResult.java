package com.lsy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Administrator on 2017/2/20 0020.
 */
@Data
@AllArgsConstructor
public class DataTablesResult {
    private String draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private Object data;
}
