package com.example.my_springboot_demo.java100.session1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: cdc
 * @Date: 2020/3/24 18:05
 * @Description:
 */

@Controller
@RequestMapping("threadlocal")
public class DemoController {

    private ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);

    @GetMapping("wrong")
    @ResponseBody
    public Map wrong(@RequestParam("userId") Integer userId) {
        //设置用户信息之前查询一次threadLocal中的用户信息
        String before = Thread.currentThread().getName() + ":" + currentUser.get();
        //设置用户信息到ThreadLocal
        currentUser.set(userId);
        //设置用户信息之后再查询一次ThreadLocal中的用户信息
        String after = Thread.currentThread().getName() + ":" + currentUser.get();
        //汇总输出两次查询结果
        Map result = new HashMap();
        result.put("before", before);
        result.put("after", after);
        return result;
    }

    @GetMapping("right")
    @ResponseBody
    public Map right(@RequestParam("userId") Integer userId) {
        try{
            //设置用户信息之前查询一次threadLocal中的用户信息
            String before = Thread.currentThread().getName() + ":" + currentUser.get();
            //设置用户信息到ThreadLocal
            currentUser.set(userId);
            //设置用户信息之后再查询一次ThreadLocal中的用户信息
            String after = Thread.currentThread().getName() + ":" + currentUser.get();
            //汇总输出两次查询结果
            Map result = new HashMap();
            result.put("before", before);
            result.put("after", after);
            return result;
        }finally {
            //在finally代码块中删除ThreadLocal中的数据，确保数据不串
            currentUser.remove();
        }

    }
}
