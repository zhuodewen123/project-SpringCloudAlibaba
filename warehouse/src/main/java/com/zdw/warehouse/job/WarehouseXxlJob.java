package com.zdw.warehouse.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class WarehouseXxlJob {

    @XxlJob("warehouseJob")
    public ReturnT<String> warehouseJob(Map<String, String> map) throws Exception {
        XxlJobHelper.log("testJob定时器执行成功:warehouseJob");
        log.info("testJob定时器执行成功:warehouseJob");
        return ReturnT.SUCCESS;
    }

}
