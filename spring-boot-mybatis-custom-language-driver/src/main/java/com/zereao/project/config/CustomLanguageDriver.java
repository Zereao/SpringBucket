package com.zereao.project.config;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;

/**
 * 在这里，可以自定义解析SQL 脚本的协议
 *
 * @author Zereao
 * @version 2019/12/13 14:12
 */
public class CustomLanguageDriver implements LanguageDriver {
    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object o, BoundSql boundSql) {
        return null;
    }

    @Override
    public SqlSource createSqlSource(Configuration configuration, XNode xNode, Class<?> aClass) {
        return null;
    }

    @Override
    public SqlSource createSqlSource(Configuration configuration, String s, Class<?> aClass) {
        return null;
    }
}
