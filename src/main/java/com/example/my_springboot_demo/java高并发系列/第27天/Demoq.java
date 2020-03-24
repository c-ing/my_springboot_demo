package com.example.my_springboot_demo.java高并发系列.第27天;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @Author: cdc
 * @Date: 2020/3/16 17:31
 *
 * 整个优化过程：
 *  先列出无依赖的一些操作
 *  将这些操作改为并行的方式
 * 用到的技术有：
 *  线程池相关知识
 *  Executors、Future相关知识
 */
public class Demoq {

    /**
     * 获取商品基本信息
     *
     * @param goodsId 商品id
     * @return 商品基本信息
     * @throws InterruptedException
     */
    public String goodsDetailModel(long goodsId) throws InterruptedException {
        //模拟耗时，休眠200ms
        TimeUnit.MILLISECONDS.sleep(200);
        return "商品id:" + goodsId + ",商品基本信息....";
    }

    /**
     * 获取商品图片列表
     *
     * @param goodsId 商品id
     * @return 商品图片列表
     * @throws InterruptedException
     */
    public List<String> goodsImgsModelList(long goodsId) throws InterruptedException {
        //模拟耗时，休眠200ms
        TimeUnit.MILLISECONDS.sleep(200);
        return Arrays.asList("图1", "图2", "图3");
    }

    /**
     * 获取商品描述信息
     *
     * @param goodsId 商品id
     * @return 商品描述信息
     * @throws InterruptedException
     */
    public String goodsExtModel(long goodsId) throws InterruptedException {
        //模拟耗时，休眠200ms
        TimeUnit.MILLISECONDS.sleep(200);
        return "商品id:" + goodsId + ",商品描述信息......";
    }

    //创建个线程池
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public Map<String,Object> detail(long goodsId){
        //创建一个map
        Map<String, Object> map = new HashMap<>();
        //step1：查询商品基本信息，放入map
        //map.put("goodsModel",(select * from t_goods where id = #gooldsId#));
        //step2：查询商品图片列表，返回一个集合放入map
        //map.put("goodsImgsModelList",(select * from t_goods_imgs where goods_id = #gooldsId#));
        //step3：查询商品描述信息，放入map
        //map.put("goodsExtModel",(select * from t_goods_ext where goods_id = #gooldsId#));
        return map;
    }


    /**
     * 获取商品详情
     *
     * @param goodsId 商品id
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public Map<String, Object> goodsDetail(long goodsId) throws ExecutionException, InterruptedException {
        Map<String, Object> result = new HashMap<>();

        //异步获取商品基本信息
        Future<String> gooldsDetailModelFuture = executorService.submit(() -> goodsDetailModel(goodsId));
        //异步获取商品图片列表
        Future<List<String>> goodsImgsModelListFuture = executorService.submit(() -> goodsImgsModelList(goodsId));
        //异步获取商品描述信息
        Future<String> goodsExtModelFuture = executorService.submit(() -> goodsExtModel(goodsId));

        result.put("gooldsDetailModel", gooldsDetailModelFuture.get());
        result.put("goodsImgsModelList", goodsImgsModelListFuture.get());
        result.put("goodsExtModel", goodsExtModelFuture.get());
        return result;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long starTime = System.currentTimeMillis();
        Map<String, Object> map = new Demoq().goodsDetail(1L);
        System.out.println(map);
        System.out.println("耗时(ms):" + (System.currentTimeMillis() - starTime));
    }
}
