package com.zdw.outbound.service;

import com.zdw.outbound.dto.SeataOutBoundDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 卓德文
 * @since 2022-11-22
 */
public interface SeataOutBoundService  {

    /**
     * 新增方法--测试seata分布式事务
     * @param seataOutBoundDto
     * @return
     */
    public int insert(SeataOutBoundDto seataOutBoundDto);


}
