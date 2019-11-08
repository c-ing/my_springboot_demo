package com.example.my_springboot_demo.designer_model.proxy_factory;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: cdc
 * @Date: 2019/11/8 11:27
 */
public class Modem implements Internet {


    @Override
    public void access(String url) {
        System.out.println("正在访问：" + url);
    }
}
