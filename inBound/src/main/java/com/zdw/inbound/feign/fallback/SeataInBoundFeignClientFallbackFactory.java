package com.zdw.inbound.feign.fallback;

import com.zdw.inbound.feign.SeataInBoundFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SeataInBoundFeignClientFallbackFactory implements SeataInBoundFeignClient {

    /**
     * feign远程调用出库模块的测试方法,测试seata分布式事务
     * @param inNo
     * @param outBound
     * @return
     */
    @Override
    public ResponseEntity<Integer> testSeata(String inNo, String outBound) {
        return null;
    }
}
