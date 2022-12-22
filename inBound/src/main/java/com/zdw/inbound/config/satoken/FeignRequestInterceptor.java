package com.zdw.inbound.config.satoken;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * openfeign调用携带token
 */
@Configuration
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        try {
            template.header("token", StpUtil.getTokenValue());
        }catch (NotLoginException e){

        }
    }
}
