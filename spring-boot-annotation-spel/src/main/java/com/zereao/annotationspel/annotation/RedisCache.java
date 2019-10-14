package com.zereao.annotationspel.annotation;

import java.lang.annotation.*;

/**
 * @author Zereao
 * @version 2019/10/14 10:16
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCache {
    /**
     * 过期时间，默认不过期；
     */
    String expire() default "";
}
