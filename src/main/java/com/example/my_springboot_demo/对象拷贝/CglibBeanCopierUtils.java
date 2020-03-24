package com.example.my_springboot_demo.对象拷贝;

import com.example.my_springboot_demo.common.Person;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: cdc
 * @Date: 2020/3/14 11:13
 */
public class CglibBeanCopierUtils {

    private static final Map<String, BeanCopier> beanCopierMap = new ConcurrentHashMap();

    public CglibBeanCopierUtils() {
    }

    public static void copyProperties(Object source, Object target) {
        String beanKey = generateKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!beanCopierMap.containsKey(beanKey)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopierMap.put(beanKey, copier);
        } else {
            copier = (BeanCopier)beanCopierMap.get(beanKey);
        }

        copier.copy(source, target, (Converter)null);
    }

    public static <T> T cloneBean(Object source, Class<T> tClass) {
        if (source == null) {
            return null;
        } else {
            try {
                T target = tClass.newInstance();
                copyProperties(source, target);
                return target;
            } catch (Exception var3) {
                var3.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    public static <T, V> List<V> copyListProperties(List<T> source, Class<V> vClass) {
        List<V> target = new ArrayList();
        if (source != null && source.size() != 0) {
            try {
                Iterator var3 = source.iterator();

                while(var3.hasNext()) {
                    T t = (T)var3.next();
                    V v = vClass.newInstance();
                    copyProperties(t, v);
                    target.add(v);
                }

                return target;
            } catch (Exception var6) {
                var6.printStackTrace();
                throw new RuntimeException();
            }
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    private static String generateKey(Class<?> class1, Class<?> class2) {
        return class1.toString() + class2.toString();
    }

    public static void main(String[] args) {
        Person p = new Person();
        p.setAge(1);
        p.setName("刘德华");
        System.out.println(p.getClass());
        System.out.println(p.getClass().getName());
        Person p2 = new Person();
        CglibBeanCopierUtils.copyProperties(p,p2);
        System.out.println(p2);
    }
}
