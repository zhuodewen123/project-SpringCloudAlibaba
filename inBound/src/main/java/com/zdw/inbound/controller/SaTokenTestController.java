package com.zdw.inbound.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.zdw.common.parent.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 卓德文
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/sa-token")
@Api(value = "saToken测试接口", tags = {"saTokenApi"})
public class SaTokenTestController {


    @ApiOperation(value = "请求汇总", consumes = "application/json;charset=UTF-8")
    @PostMapping(value = "/apiTestOne")
    @SaCheckLogin
    @SaCheckRole("admin")                   //必须拥有该角色可访问
    @SaCheckPermission("inbound")           //必须拥有该权限可访问
    public JSONResult apiTestOne() {
        return new JSONResult().markSuccess("请求成功", "111");
    }


    @ApiOperation(value = "请求汇总", consumes = "application/json;charset=UTF-8")
    @PostMapping(value = "/apiTestTwo")
    @SaCheckLogin
    @SaCheckRole("super-admin")             //必须拥有该角色可访问
    @SaCheckPermission("inbound2")          //必须拥有该权限可访问
    public JSONResult apiTestTwo() {
        return new JSONResult().markSuccess("请求成功", "222");
    }

}
