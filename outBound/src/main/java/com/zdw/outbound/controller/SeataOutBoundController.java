package com.zdw.outbound.controller;

import com.zdw.outbound.dto.SeataOutBoundDto;
import com.zdw.outbound.service.SeataOutBoundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value="seata测试接口",tags={"seataAPi"})
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
    @ApiOperation("seata测试方法-testSeata")
    public Integer testSeata(@RequestParam @ApiParam(name = "入库单号" ,value = "inNo" , required = true) String inNo, @RequestParam @ApiParam(name = "出库字段" ,value = "outBound" , required = true) String outBound) {
        SeataOutBoundDto seataOutBoundDto = new SeataOutBoundDto();
        seataOutBoundDto.setInNo(inNo);
        seataOutBoundDto.setOutBound(outBound);
        return seataOutBoundService.insert(seataOutBoundDto);
    }

}
