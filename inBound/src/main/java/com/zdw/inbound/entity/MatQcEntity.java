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
@TableName("MAT_QC")
@ApiModel(value = "MatQcEntity对象", description = "")
public class MatQcEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("入库单号")
    private String deliveryNo;

    @ApiModelProperty("仓库编码")
    private String warehouseCode;

    @ApiModelProperty("质检单号")
    private String qcNo;

    @ApiModelProperty("物料编码")
    private String materialCode;

    @ApiModelProperty("供应商编码")
    private String venderCode;

    @ApiModelProperty("送检数量")
    private BigDecimal qty;

    @ApiModelProperty("处置数量")
    private BigDecimal qcQty;

    @ApiModelProperty("质检状态(0:新建;1:质检通过;2:质检不通过)")
    private String qcState;


}
