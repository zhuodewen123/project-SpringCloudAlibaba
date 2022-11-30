package com.zdw.outbound.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * SeataOutBoundDto对象
 * </p>
 *
 * @author 卓德文
 * @since 2022-11-22
 */
@Getter
@Setter
public class SeataOutBoundDto {

    @ApiModelProperty("出库字段")
    private String outBound;

    @ApiModelProperty("入库单号")
    private String inNo;


}
