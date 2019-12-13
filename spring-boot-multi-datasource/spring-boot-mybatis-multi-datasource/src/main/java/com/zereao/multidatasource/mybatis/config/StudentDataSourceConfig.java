package com.zereao.multidatasource.mybatis.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Zereao
 * @version 2019/12/12 21:3
 */
@Configuration
@MapperScan(basePackages = {"com.zereao.multidatasource.mybatis.dao.student"}, sqlSessionTemplateRef = "studentSqlSessionTemplate")
public class StudentDataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "student.datasource")
    public DataSource studentDataSource() {
        return new HikariDataSource();
    }

    @Bean
    public SqlSessionFactory studentSqlSessionFactory(@Qualifier("studentDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean
    public SqlSessionTemplate studentSqlSessionTemplate(@Qualifier("studentSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
