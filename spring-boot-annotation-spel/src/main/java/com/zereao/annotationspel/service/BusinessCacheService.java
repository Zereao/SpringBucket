package com.zereao.annotationspel.service;

import com.zereao.annotationspel.annotation.Expire;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author Zereao
 * @version 2019/10/14 10:12
 */
@Service
public class BusinessCacheService {
    @Resource
    private BusinessService businessService;

    /**
     * SPEL 表达式需要包裹在  #{   }  中
     */
    @Expire(key = "#{@cacheToolService.getKey(#p0)}")
    public void flushCache(Method method) {
        String result = businessService.business();
    }
}
