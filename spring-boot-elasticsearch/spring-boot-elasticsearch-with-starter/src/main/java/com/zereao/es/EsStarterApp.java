package com.zereao.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.zereao.es"})
//@EnableElasticsearchRepositories(basePackages = "com.zereao.es.dao")
public class EsStarterApp {

    public static void main(String[] args) {
        SpringApplication.run(EsStarterApp.class, args);
    }

}
