package com.zdw.gateway.filter;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.net.URI;
import static org.springframework.http.server.PathContainer.parsePath;

/**
 * 接口日志过滤器(打印所有接口路径和入参)
 * @author 卓德文
 * @since 2022/11/11
 */
@Slf4j
@Component
public class InfLogFilter implements GlobalFilter, Ordered {

    /**
     * 过滤接口,打印路径和参数
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取路径
        PathContainer path = parsePath(exchange.getRequest().getURI().getRawPath());
        String pathInf = String.valueOf(path);
        log.info("接口日志打印,接口路径:{}",pathInf);
        //获取请求
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        HttpMethod method = serverHttpRequest.getMethod();
        URI uri = exchange.getRequest().getURI();
        String contentType = serverHttpRequest.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        boolean postFlag = (method == HttpMethod.POST || method == HttpMethod.PUT) && (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(contentType) || MediaType.APPLICATION_JSON_VALUE.equals(contentType));
        String data = StrUtil.EMPTY;
        //根据请求类型,获取请求参数
        if(postFlag){
            data = exchange.getAttribute("CACHED_REQUEST_BODY");
        }else if(method == HttpMethod.GET){
            data = uri.getRawQuery();
        }
        log.info("data:{}",data);
        //放行
        return chain.filter(exchange);
    }

    /**
     * 优先级
     * @return
     */
    @Override
    public int getOrder() {
        return -999;
    }
}
