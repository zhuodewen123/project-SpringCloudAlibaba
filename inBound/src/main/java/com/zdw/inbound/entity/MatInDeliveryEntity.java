package com.zdw.inbound.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zdw.common.parent.BaseEntity;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 卓德文
 * @since 2022-11-21
 */
@Getter
@Setter
@TableName("MAT_IN_DELIVERY")
@ApiModel(value = "MatInDeliveryEntity对象", description = "")
public class MatInDeliveryEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ASN单号")
    private String asnNo;

    @ApiModelProperty("ASN类型(1:JIT;2:其他)")
    private String asnType;

    @ApiModelProperty("状态(0:新建;1:收货;2:已完成;3:取消)")
    private String asnState;

    @ApiModelProperty("供应商编码")
    private String vendorCode;

    @ApiModelProperty("供应商名称")
    private String vendorName;

    @ApiModelProperty("工厂编码")
    private String factoryCode;

    @ApiModelProperty("工厂名称")
    private String factoryName;


}
