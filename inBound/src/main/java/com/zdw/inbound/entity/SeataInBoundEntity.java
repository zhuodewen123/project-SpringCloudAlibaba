package com.zdw.inbound.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-11-22
 */
@Getter
@Setter
@TableName("seata_in_bound")
@ApiModel(value = "SeataInBoundEntity对象", description = "")
public class SeataInBoundEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private int id;

    private String inBound;

    private String inNo;


}
