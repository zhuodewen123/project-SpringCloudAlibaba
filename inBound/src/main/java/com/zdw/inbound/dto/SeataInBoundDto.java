package com.zdw.inbound.dto;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("入库字段")
    private String inBound;

    @ApiModelProperty("入库单号")
    private String inNo;


}
