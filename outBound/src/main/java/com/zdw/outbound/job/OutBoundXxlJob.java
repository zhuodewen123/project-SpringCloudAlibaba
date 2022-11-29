package com.zdw.outbound.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class OutBoundXxlJob {

    @XxlJob("outboundJob")
    public ReturnT<String> outboundJob(Map<String, String> map) throws Exception {
        XxlJobHelper.log("testJob定时器执行成功:outboundJob");
        log.info("testJob定时器执行成功:outboundJob");
        return ReturnT.SUCCESS;
    }

}
