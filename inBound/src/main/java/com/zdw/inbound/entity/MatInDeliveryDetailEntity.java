package com.zdw.inbound.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zdw.common.parent.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("MAT_IN_DELIVERY_DETAIL")
@ApiModel(value = "MatInDeliveryDetailEntity对象", description = "")
public class MatInDeliveryDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ASN单号")
    private String asnNo;

    @ApiModelProperty("ASN行号")
    private String asnItem;

    @ApiModelProperty("物料编码")
    private String materialCode;

    @ApiModelProperty("物料名称")
    private String materialName;

    @ApiModelProperty("数量")
    private BigDecimal qty;

    @ApiModelProperty("移动类型")
    private String moveType;


}
