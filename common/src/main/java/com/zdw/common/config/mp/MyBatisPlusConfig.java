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
     * 最新版分页
     */
    @ConditionalOnMissingBean
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.ORACLE));
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
                Date date=new Date();
                //创建时间
                this.setFieldValByName("createTime", date, metaObject);
                //更新时间
                this.setFieldValByName("updateTime", date, metaObject);
            }

            // 更新时的填充
            @Override
            public void updateFill(MetaObject metaObject) {
                //更新时间
                this.setFieldValByName("updateTime", new Date(), metaObject);
            }
        };
    }

}