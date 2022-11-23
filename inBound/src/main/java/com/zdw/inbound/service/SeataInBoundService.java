package com.zdw.inbound.service;

import com.zdw.inbound.dto.SeataInBoundDto;
import com.zdw.inbound.entity.SeataInBoundEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 卓德文
 * @since 2022-11-22
 */
public interface SeataInBoundService  {

    public int insert(SeataInBoundDto seataInBoundDto);
}
