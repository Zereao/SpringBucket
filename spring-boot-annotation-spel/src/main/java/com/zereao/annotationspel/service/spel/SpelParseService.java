package com.zereao.annotationspel.service.spel;

import java.lang.reflect.Method;

/**
 * @author Zereao
 * @version 2019/10/14 10:37
 */
public interface SpelParseService {
    /**
     * 解析SPEL 表达式，将结果解析为泛型类型
     *
     * @param expression SPEL表达式
     * @param method     切入点方法
     * @param cls        返回类型Class对象
     * @param args       方法参数
     * @param <T>        泛型
     * @return 解析结果
     */
    <T> T parse(String expression, Method method, Class<T> cls, Object... args);
}
