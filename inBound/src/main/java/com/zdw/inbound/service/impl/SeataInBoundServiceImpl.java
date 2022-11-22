package com.zdw.inbound.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.zdw.inbound.entity.SeataInBoundEntity;
import com.zdw.inbound.feign.SeataInBoundFeignClient;
import com.zdw.inbound.mapper.SeataInBoundMapper;
import com.zdw.inbound.service.SeataInBoundService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class SeataInBoundServiceImpl extends ServiceImpl<SeataInBoundMapper, SeataInBoundEntity> implements SeataInBoundService {

    @Autowired
    private SeataInBoundMapper seataInBoundMapper;
    @Autowired
    private SeataInBoundFeignClient seataInBoundFeignClient;

    /**
     * 测试seata分布式事务
     * @param seataInBoundEntity
     * @return
     */
    @Override
    @GlobalTransactional(name = "seataTest",rollbackFor = Exception.class)
    public int insert(SeataInBoundEntity seataInBoundEntity) {
        //1.调用当前入库服务方法A
        seataInBoundMapper.insert(seataInBoundEntity);
        //2.远程调用出库服务方法B
        ResponseEntity<Integer> responseEntity = seataInBoundFeignClient.testSeata(seataInBoundEntity.getInNo(), seataInBoundEntity.getInBound());
        //3.模拟异常,测试分布式事务回滚
        int i = 1/0;
        //4.结果和返回
        if(ObjectUtil.isNull(responseEntity) || ObjectUtil.isNull(responseEntity.getBody())){
            return 0;
        }
        return responseEntity.getBody();
    }
}
