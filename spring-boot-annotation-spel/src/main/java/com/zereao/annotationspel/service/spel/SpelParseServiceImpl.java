package com.zereao.annotationspel.service.spel;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author Zereao
 * @version 2019/10/14 10:38
 */
@Service("SpelParseService")
public class SpelParseServiceImpl implements SpelParseService, BeanFactoryAware {
    @Resource
    private SpelExpressionParser spelExpressionParser;

    private BeanFactory beanFactory;

    @Override
    public <T> T parse(String expression, Method method, Class<T> cls, Object... args) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(beanFactory));
        context.setVariable("method", method);
        context.setVariable("args", args);
        if (args != null && args.length > 0) {
            for (int i = 0, len = args.length; i < len; i++) {
                context.setVariable("p" + i, args[i]);
            }
        }
        Expression exp = spelExpressionParser.parseExpression(expression, ParserContext.TEMPLATE_EXPRESSION);
        return exp.getValue(context, cls);
    }

    @Override
    public void setBeanFactory(@NonNull BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Bean
    public SpelExpressionParser spelExpressionParser() {
        return new SpelExpressionParser();
    }
}
