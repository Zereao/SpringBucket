package com.zereao.es;

import com.zereao.es.config.EsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({EsConfig.class})
@SpringBootApplication(scanBasePackages = {"com.zereao.es"})
public class EsApp {

    public static void main(String[] args) {
        SpringApplication.run(EsApp.class, args);
    }

}
