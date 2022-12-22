package com.zdw.oauth.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class UserEntity {

    private String loginId;

    private String phone;

    private String name;

    private String pwd;

}
