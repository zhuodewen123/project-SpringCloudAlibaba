package com.zdw.inbound.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * SeataInBoundDto对象
 * </p>
 *
 * @author 卓德文
 * @since 2022-11-22
 */
@Getter
@Setter
public class SeataInBoundDto {

    private String inBound;

    private String inNo;


}
