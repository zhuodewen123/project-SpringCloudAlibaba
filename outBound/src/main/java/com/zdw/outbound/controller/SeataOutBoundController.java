package com.zdw.outbound.controller;

import com.zdw.outbound.dto.SeataOutBoundDto;
import com.zdw.outbound.service.SeataOutBoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        SeataOutBoundDto seataOutBoundDto = new SeataOutBoundDto();
        seataOutBoundDto.setInNo(inNo);
        seataOutBoundDto.setOutBound(outBound);
        return seataOutBoundService.insert(seataOutBoundDto);
    }

}
