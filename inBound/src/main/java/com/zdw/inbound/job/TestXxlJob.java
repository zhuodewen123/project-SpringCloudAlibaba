package com.zdw.inbound.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class TestXxlJob {

    @XxlJob("testXxjJob")
    public ReturnT<String> testXxlJob(Map<String, String> map) throws Exception {
        XxlJobHelper.log("testJob定时器执行成功:"+new Date());
        return ReturnT.SUCCESS;
    }

}
