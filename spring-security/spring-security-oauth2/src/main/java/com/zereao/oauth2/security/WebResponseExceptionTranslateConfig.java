package com.zereao.oauth2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * @author Zereao
 * @version 2019/05/28 13:52
 */
@Configuration
public class WebResponseExceptionTranslateConfig {
    /**
     * 自定义登录或者鉴权失败时的返回信息
     */
    @Bean(name = "webResponseExceptionTranslator")
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
                ResponseEntity responseEntity = super.translate(e);
                OAuth2Exception body = (OAuth2Exception) responseEntity.getBody();
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                // do something with header or response
                if (HttpStatus.BAD_REQUEST.equals(responseEntity.getStatusCode())) {
                    assert body != null;
                    if ("Bad credentials".equals(body.getMessage())) {
                        OAuth2Exception exception = new OAuth2Exception("您输入的用户名或密码错误");
                        return new ResponseEntity<>(exception, headers, HttpStatus.OK);
                    }
                }
                return new ResponseEntity<>(body, headers, responseEntity.getStatusCode());
            }
        };
    }
}
