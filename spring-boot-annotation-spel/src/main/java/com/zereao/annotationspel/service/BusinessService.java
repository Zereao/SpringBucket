package com.zereao.annotationspel.service;

import com.zereao.annotationspel.annotation.RedisCache;
import org.springframework.stereotype.Service;

/**
 * @author Zereao
 * @version 2019/10/14 10:26
 */
@Service
public class BusinessService {
    /**
     * 需要缓存的业务逻辑
     *
     * @ ReDisCache注解类似 @Cacheable
     * 调用 @RedisCache 标注的方法时，首先去 Redis中查找；
     * 如果找不到，则执行方法，并将结果集写入Redis
     */
    @RedisCache(expire = "2H")
    public String business() {
        // do something
        return "{THE_RESULT}";
    }
}
