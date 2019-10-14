package com.zereao.annotationspel.annotation;

import java.lang.annotation.*;

/**
 * @author Zereao
 * @version 2019/10/14 10:15
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Expire {
    /**
     * 需要过期的Key，支持 SPEL表达式
     */
    String key() default "";

    /**
     * 是否使当前缓存下所有key失效
     */
    boolean allEntries() default false;
}
