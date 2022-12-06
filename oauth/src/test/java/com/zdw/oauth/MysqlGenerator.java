package com.zdw.oauth;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.zdw.common.parent.BaseEntity;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Mybatis-Plus自动生成文件(Oracle)
 */
public class MysqlGenerator {

	@Test
	public void generator() {
		//表名 (支持输入多个表，用","隔开，例如"A,B")
		String tableName = "user";

		//作者名
		String author = "卓德文";

		//代码生成配置
		List<String> tableNameList = Arrays.asList(tableName.split(","));
		String url = "jdbc:mysql://47.119.136.175:3306/wms_oauth";
		String dir = System.getProperty("user.dir");

		FastAutoGenerator.create(url, "zdw", "123456")
				.globalConfig(builder -> {
					builder.author(author) 									// 设置作者
							.enableSwagger() 								// 开启 swagger 模式
                            //.fileOverride() 								// 覆盖已生成文件
							.outputDir(dir + "\\src\\main\\java\\"); 		// 指定输出目录
				})
				.packageConfig(builder -> {
					builder.parent("com.zdw")								// 设置父包名
							.moduleName("oauth")						// 设置父包模块名
							.controller("controller")
							.service("service")
							.serviceImpl("service.impl")
							.entity("entity")
							.mapper("mapper")
							.pathInfo(Collections.singletonMap(OutputFile.mapperXml, dir + "\\src\\main\\resources\\mapper"));	// 设置mapper文件生成路径;
				})
				.strategyConfig(builder -> {
					//controller配置
					builder.controllerBuilder().enableRestStyle();			// 开启生成@RestController控制器

					//server配置
					builder.serviceBuilder()
							.formatServiceFileName("%sService")				// 自定义Service名称
							.formatServiceImplFileName("%sServiceImpl");	// 自定义Service实现类名称

					//entity配置
					builder.entityBuilder()
							.formatFileName("%sEntity")
							.enableLombok()    								// 开启lombok
							.superClass(BaseEntity.class)					// 设置父类
							.addSuperEntityColumns("creator", "create_time", "modifier", "update_time");   						// 添加父类公共字段

					builder.addInclude(tableNameList) 						// 设置需要生成的表名
							.addTablePrefix("report"); 						// 设置过滤表前缀
				})
				.templateConfig(builder -> {
					builder
							.controller("/templates/controller.java")											// 自定义controller模板的路径
							.service("/templates/service.java"); 													// 自定义service接口模板的路径
				})
				.execute();
	}

}
