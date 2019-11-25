package com.example.my_springboot_demo.concurrent_thread_test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockUtil {

    private  static Map<String,Lock> lockMap = new LinkedHashMap<>();
    private static int i;
	public static Lock getLock(String type) {
	    if(lockMap.get(type)==null){
            synchronized (lockMap){
                if(lockMap.get(type)==null){
                    System.out.println("当前线程" + Thread.currentThread());
                    lockMap.put(type,new ReentrantLock());
                }
            }
        }
       return lockMap.get(type);
    }

    public void unLock(Lock lock) {
	    lock.unlock();
    }

}
