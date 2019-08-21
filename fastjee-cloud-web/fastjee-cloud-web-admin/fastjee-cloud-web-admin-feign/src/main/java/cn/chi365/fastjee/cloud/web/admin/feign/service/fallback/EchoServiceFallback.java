package cn.chi365.fastjee.cloud.web.admin.feign.service.fallback;

import cn.chi365.fastjee.cloud.web.admin.feign.service.EchoService;
import org.springframework.stereotype.Component;

/***
 * 熔断器类并实现对应的 Feign 接口
 * @author      :YuSir Wang
 * @date        :Created in 13:34 2019/3/28
 * @version     :1.0.0-SNAPSHOT
 */
@Component
public class EchoServiceFallback implements EchoService {

    @Override
    public String echo(String message) {
        return "echo fallback";
    }
}
