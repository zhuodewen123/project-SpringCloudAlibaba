package com.zdw.gateway.config.satoken;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 卓德文
 * @title: 获取角色和权限
 * @date 2022/12/22
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    /**
     *使用根据loginId动态查询对应权限
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        List<String> permissionList = new ArrayList<>();
        permissionList.add("inbound");
        return permissionList;
    }

    /**
     *使用根据loginId动态查询对应角色
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        List<String> roleList = new ArrayList<>();
        roleList.add("admin");
        roleList.add("super-admin");
        return roleList;
    }

}