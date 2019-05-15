package com.zereao.es.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zereao
 * @version 2019/04/30 14:47
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class EsConfig {
    private String host;
    private String clusterName;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient client() {
        return new RestHighLevelClient(RestClient.builder(
                new HttpHost(host, 9200, "http")
        ));
    }

}
