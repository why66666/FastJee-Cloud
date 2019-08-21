package cn.chi365.fastjee.cloud.web.admin.feign.service;

import cn.chi365.fastjee.cloud.web.admin.feign.service.fallback.EchoServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/***
 * 通过 @FeignClient("服务名") 注解来指定调用哪个服务。
 * @author      :YuSir Wang
 * @date        :Created in 13:26 2019/3/28
 * @version     :1.0.0-SNAPSHOT
 */
@FeignClient(value = "fastjee-cloud-web-admin-provider",fallback = EchoServiceFallback.class)
public interface EchoService {
    @GetMapping(value = "/echo/{message}")
    String echo(@PathVariable(value = "message") String message);
}
