package com.zdw.gateway.config.satoken;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author 卓德文
 * @title: Sa-Token全局过滤器
 * @date 2022/12/22
 */
@Configuration
public class SaTokenConfigure {

    public SaTokenConfigure() {
        super();
    }

    /**
     * 注册Sa-Token全局过滤器
     *
     * @return
     */
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 开放地址
                .addExclude("/favicon.ico")
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
                    SaRouter.match("/**", "/oauth/user/**", r -> StpUtil.checkLogin());
                    // 角色认证 -- 拦截以 admin 开头的路由，必须具备 admin 角色或者 super-admin 角色才可以通过认证
                    SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin", "super-admin"));
                    // 权限认证 -- 不同模块, 校验不同权限
                    SaRouter.match("/inbound/**", r -> StpUtil.checkPermission("inbound"));
                    SaRouter.match("/warehouse/**", r -> StpUtil.checkPermission("warehouse"));
                    SaRouter.match("/outbound/**", r -> StpUtil.checkPermission("outbound"));
                    //SaRouter.match("/oauth/**", r -> StpUtil.checkPermission("oauth"));
                })
                // 异常处理方法：每次setAuth函数出现异常时进入
                .setError(e -> {
                    // 设置错误返回格式为JSON
                    ServerWebExchange exchange = SaReactorSyncHolder.getContext();
                    exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
                    //return new ResultJsonUtil().fail(e.getMessage());
                    return SaResult.error(e.getMessage());
                })
                .setBeforeAuth(obj -> {
                    // 设置跨域响应头
                    SaHolder.getResponse()
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", "*")
                            // 允许所有请求方式
                            .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                            .setHeader("Access-Control-allow-credentials", "true")
                            // 允许的header参数
                            .setHeader("Access-Control-expose-headers", "*")
                            .setHeader("Access-Control-Allow-Headers", "*");

                    // 如果是预检请求，则立即返回到前端
                    SaRouter.match(SaHttpMethod.OPTIONS)
                            .free(r -> System.out.println("--------OPTIONS预检请求，不做处理"))
                            .back();
                });
    }

}