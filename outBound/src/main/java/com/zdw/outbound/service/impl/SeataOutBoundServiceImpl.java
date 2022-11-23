package com.zdw.outbound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zdw.outbound.dto.SeataOutBoundDto;
import com.zdw.outbound.entity.SeataOutBoundEntity;
import com.zdw.outbound.mapper.SeataOutBoundMapper;
import com.zdw.outbound.service.SeataOutBoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 卓德文
 * @since 2022-11-22
 */
@Service
public class SeataOutBoundServiceImpl extends ServiceImpl<SeataOutBoundMapper, SeataOutBoundEntity> implements SeataOutBoundService {

    @Autowired
    private SeataOutBoundMapper seataOutBoundMapper;

    /**
     * 新增方法--测试seata分布式事务
     * @param seataOutBoundDto
     * @return
     */
    @Override
    public int insert(SeataOutBoundDto seataOutBoundDto) {
        SeataOutBoundEntity seataOutBoundEntity = BeanUtil.copyProperties(seataOutBoundDto, SeataOutBoundEntity.class);
        return seataOutBoundMapper.insert(seataOutBoundEntity);
    }
}
