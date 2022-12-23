package com.zdw.warehouse.config.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置类(接口文档:localhost:port/swagger-ui.html)
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                // 通过.select()方法，去配置扫描接口
                .select()
                // RequestHandlerSelectors.basePackage配置根据包路径扫描接口
                // .apis(RequestHandlerSelectors.basePackage("com.atguigu.bookcity.controller"))
                // RequestHandlerSelectors.basePackage扫描所有有注解的api,用这种方式更灵活,指定为ApiOperation.class
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build();
    }

    //配置文档信息
    private ApiInfo apiInfo() {
        //用ApiInfoBuilder进行定制，可以设置不同的属性，比较方便
        return new ApiInfoBuilder()
                //设置标题
                .title("标题：集成swagger测试")
                //描述
                .description("描述：用于测试集成swagger接口")
                //作者信息
                .contact(new Contact("zhuodewen","https://github.com/zhuodewen123","13422075804@163.com"))
                //版本
                .version("版本号：1.0")
                //构建
                .build();
    }

}
