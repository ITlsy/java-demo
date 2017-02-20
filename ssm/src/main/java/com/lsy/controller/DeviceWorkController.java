package com.lsy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/2/19 0019.
 */
@Controller
@RequestMapping("/device/work")
public class DeviceWorkController {
    @GetMapping
    public String list() {
        return "device/work/list";
    }
}