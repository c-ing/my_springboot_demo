package com.example.my_springboot_demo.designer_model.proxy_factory;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: cdc
 * @Date: 2019/11/8 11:28
 *
 * 而这个代理自身其实并不具备访问互联网的能力，
 * 它只是简单的调用“猫”上网功能，其存在目的只是为了控制对”猫“的互联访问，对其进行代理而已。
 *
 * 其实还有一种叫动态代理，不同之处在于其实例化过程是在运行时完成的，
 * 也就是说我们不需要专门针对某个接口去写这么一个代理类，而是根据接口动态生成。
 */
public class RouterProxy implements Internet { // 路由代理类

    private Internet modem;//持有被代理类引用
    private List<String> blackList = Arrays.asList("电影", "游戏", "音乐", "小说");

    public RouterProxy(Internet modem) {
        this.modem = new Modem();//实例化被代理类
        System.out.println("拨号上网....连接成功");
    }

    @Override
    public void access(String url) { // 同样实现互联网访问接口方法
        for (String keyword : blackList) { // 循环黑名单
            if (url.contains(keyword)) {  //是否包含黑名单字眼
                System.out.println("禁止访问：" + url);
                return;
            }
        }
        modem.access(url); // 正常访问互联网
    }
}
