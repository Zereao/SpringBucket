package com.zereao.es;

import com.zereao.es.config.EsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.zereao.es"})
@EnableConfigurationProperties({EsConfig.class})
public class EsApp {

    public static void main(String[] args) {
        SpringApplication.run(EsApp.class, args);
    }

}
