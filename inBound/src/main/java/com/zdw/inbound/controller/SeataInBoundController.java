package com.zdw.inbound.controller;

import com.zdw.inbound.dto.SeataInBoundDto;
import com.zdw.inbound.service.SeataInBoundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
@Api(value="seata测试接口",tags={"seataAPi"})
public class SeataInBoundController {

    @Autowired
    private SeataInBoundService seataInBoundService;

    /**
     * 测试seata分布式事务
     * @param seataInBoundDto
     * @return
     */
    @PostMapping("/testSeata")
    @ApiOperation("seata测试方法-testSeata")
    public Integer testSeata(@RequestBody @Valid SeataInBoundDto seataInBoundDto) {
        return seataInBoundService.insert(seataInBoundDto);
    }

}
