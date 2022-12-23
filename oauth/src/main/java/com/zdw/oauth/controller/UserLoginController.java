package com.zdw.oauth.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.zdw.common.parent.JSONResult;
import com.zdw.oauth.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author 卓德文
 * @title: 登录控制器
 * @date 2022/12/22
 */
@Api(tags = "用户认证相关")
@RestController
@RequestMapping("/user")
public class UserLoginController {

    /**
     * 手机登录
     *
     * @return
     */
    @ApiOperation(value = "手机登录")
    @PostMapping("/phoneLogin")
    public JSONResult phoneLogin(@RequestBody UserEntity userEntity) {
        JSONResult jsonResult = new JSONResult();
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("13422075804".equals(userEntity.getPhone()) && "123456".equals(userEntity.getPwd())) {
            StpUtil.login(10001);
            return jsonResult.markSuccess("手机登录成功", StpUtil.getTokenInfo());
        }
        return jsonResult.markFail("手机登录失败");
    }

    /**
     * 账号登录
     *
     * @return
     */
    @ApiOperation(value = "账号登录")
    @PostMapping("doLogin")
    public JSONResult doLogin(@RequestBody UserEntity userEntity) {
        JSONResult jsonResult = new JSONResult();
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if ("zhuodewen".equals(userEntity.getName()) && "123456".equals(userEntity.getPwd())) {
            StpUtil.login(10002);
            return jsonResult.markSuccess("账号登录成功", StpUtil.getTokenInfo());
        }
        return jsonResult.markFail("账号登录失败");
    }

    /**
     * 查询登录状态
     *
     * @return
     */
    @ApiOperation(value = "查询登录状态")
    @PostMapping("isLogin")
    public SaResult isLogin() {
        return SaResult.ok("是否登录：" + StpUtil.isLogin());
    }

    /**
     * 查询Token信息
     *
     * @return
     */
    @ApiOperation(value = "查询Token信息")
    @PostMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    /**
     * 注销
     *
     * @return
     */
    @ApiOperation(value = "注销")
    @GetMapping("logout")
    public SaResult logout(String loginId) {
        StpUtil.logout(loginId);
        return SaResult.ok("loginId：" + loginId + ",注销成功");
    }

}