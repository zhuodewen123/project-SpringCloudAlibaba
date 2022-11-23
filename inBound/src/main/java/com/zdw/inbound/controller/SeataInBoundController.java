package com.zdw.inbound.controller;

import com.zdw.inbound.dto.SeataInBoundDto;
import com.zdw.inbound.entity.SeataInBoundEntity;
import com.zdw.inbound.service.SeataInBoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 卓德文
 * @since 2022-11-21
 */
@RestController
@RequestMapping("/seataInBound")
public class SeataInBoundController {

    @Autowired
    private SeataInBoundService seataInBoundService;

    /**
     * 测试seata分布式事务
     * @param seataInBoundDto
     * @return
     */
    @PostMapping("/testSeata")
    public Integer testSeata(@RequestBody SeataInBoundDto seataInBoundDto) {
        return seataInBoundService.insert(seataInBoundDto);
    }

}
