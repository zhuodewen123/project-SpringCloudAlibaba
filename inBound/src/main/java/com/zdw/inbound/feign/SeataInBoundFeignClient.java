package com.zdw.inbound.feign;

import com.zdw.inbound.feign.fallback.SeataInBoundFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * description
 *
 * @author 卓德文
 * @date 2022/11/22
 */
@FeignClient(name = "outbound", /*contextId = "com.zdw.inbound.feign.SeataInBoundFeignClient" ,*/ fallback = SeataInBoundFeignClientFallbackFactory.class)
public interface SeataInBoundFeignClient {

	/**
	 * feign远程调用出库模块的测试方法,测试seata分布式事务
	 * @param inNo
	 * @param outBound
	 * @return
	 */
	@RequestMapping(value = "/seataOutBound/testSeata", method = RequestMethod.POST)
	ResponseEntity<Integer> testSeata(@RequestParam("inNo") String inNo, @RequestParam("outBound") String outBound);


}
