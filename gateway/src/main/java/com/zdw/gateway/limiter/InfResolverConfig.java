package com.zdw.gateway.limiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * 接口限流配置
 */
@Configuration
public class InfResolverConfig {

    @Bean(value="infKeyResolver")
    KeyResolver infKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getPath().value());
    }
}
