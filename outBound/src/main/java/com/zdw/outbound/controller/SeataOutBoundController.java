package com.zdw.outbound.controller;

import com.zdw.outbound.entity.SeataOutBoundEntity;
import com.zdw.outbound.service.SeataOutBoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 卓德文
 * @since 2022-11-22
 */
@RestController
@RequestMapping("/seataOutBound")
public class SeataOutBoundController {

    @Autowired
    private SeataOutBoundService seataOutBoundService;

    /**
     * 测试seata分布式事务
     * @param inNo
     * @param outBound
     * @return
     */
    @PostMapping("/testSeata")
    public Integer testSeata(@RequestParam String inNo,@RequestParam String outBound) {
        SeataOutBoundEntity seataOutBoundEntity = new SeataOutBoundEntity();
        seataOutBoundEntity.setInNo(inNo);
        seataOutBoundEntity.setOutBound(outBound);
        return seataOutBoundService.insert(seataOutBoundEntity);
    }

}
