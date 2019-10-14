package com.zereao.annotationspel.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

/**
 * 这里，我只提供了接口，未提供实现~
 *
 * @author Zereao
 * @version 2019/10/14 10:17
 */
@Service
public interface CacheToolService {
    /**
     * 根据 方法名，参数，组装出 缓存的 key
     *
     * @param method 方法
     * @param args   方法的参数，本身是一个 可变参数
     * @return 缓存的key
     */
    String getKey(Method method, Object... args);
}
