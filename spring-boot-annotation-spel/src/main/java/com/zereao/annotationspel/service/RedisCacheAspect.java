package com.zereao.annotationspel.service;

import com.zereao.annotationspel.annotation.Expire;
import com.zereao.annotationspel.service.spel.SpelParseService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author Zereao
 * @version 2019/10/14 10:53
 */
@Slf4j
@Aspect
@Service
public class RedisCacheAspect {
    @Resource
    private CacheToolService cacheToolService;
    @Resource
    private SpelParseService spelParseService;

    /**
     * 切点，所有方法
     */
    @Pointcut("execution(* com.zereao.annotationspel..*.*(..))")
    public void allMethods() {}

    /**
     * 切点，被 @RedisCache 标注的所有方法
     */
    @Pointcut("@annotation(com.zereao.annotationspel.annotation.RedisCache)")
    public void redisCacheAnnotated() {}

    /**
     * 切点，被 @Expire 标注的所有方法
     */
    @Pointcut("@annotation(com.zereao.annotationspel.annotation.Expire)")
    public void expireAnnotated() {}

    /**
     * 用于处理 缓存写入、读取
     */
    @Around("allMethods() && redisCacheAnnotated()")
    public Object redisCache(ProceedingJoinPoint pjp) throws Throwable {
        // get the result from redis, if not exist，execute the target method and return it's result
        return "{THE_RESULT_OBJECT}";
    }

    /**
     * 用于处理 缓存 过期
     */
    @Before("allMethods() && expireAnnotated()")
    public void expireCache(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Expire expire = method.getAnnotation(Expire.class);
        if (expire.allEntries()) {
            // expire all entries
            return;
        } else {
            String keySpelExpression = expire.key();
            String methodName = method.getName();
            if (StringUtils.isEmpty(keySpelExpression)) {
                log.warn("方法 {} 上的@Expire 注解参数全为空！", methodName);
            } else {
                String spel = expire.key();
                /* 这里，解析出的结果不管是 1个字符串，还是一个 List<String> ，都使用 List<String> 接收，没问题的 */
                @SuppressWarnings("unchecked")
                List<String> keyList = spelParseService.parse(spel, method, List.class, joinPoint.getArgs());
                if (CollectionUtils.isEmpty(keyList)) {
                    log.warn("从方法 {} 上的@Expire注解的key属性的SPEL表达式中解析出的数据为空！SPEL = {}", methodName, spel);
                } else {
                    // delete all the keys, cache flushed
                    return;
                }
            }
        }
        log.info("没有任何缓存被清理，请关注 @Expire 注解是否正确设置了参数！");
    }
}
