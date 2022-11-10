package com.zdw.common.config.mp;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * mybatisplus配置
 * @author 卓德文
 */
@Configuration
@EnableConfigurationProperties({MybatisPlusProperties.class})
public class MyBatisPlusConfig {

    /**
     * 最新版
     */
    @ConditionalOnMissingBean
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    public GlobalConfig globalConfig() {
        return (new GlobalConfig()).setMetaObjectHandler(this.metaObjectHandler());
    }

    /**
     * 自动填充
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            // 插入时的填充
            @Override
            public void insertFill(MetaObject metaObject) {
                //创建时间
                this.setFieldValByName("creationDate", new Date(), metaObject);
                //修改时间
                this.setFieldValByName("lastUpdateDate", new Date(), metaObject);
            }

            // 更新时的填充
            @Override
            public void updateFill(MetaObject metaObject) {
                //修改时间
                this.setFieldValByName("lastUpdateDate", new Date(), metaObject);
            }
        };
    }

}