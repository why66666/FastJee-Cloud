package cn.chi365.fastjee.cloud.web.admin.feign.controller;

import cn.chi365.fastjee.cloud.web.admin.feign.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 * @author      :YuSir Wang
 * @date        :Created in 13:29 2019/3/28
 * @version     :1.0.0-SNAPSHOT
 */
@RestController
public class NacosConsumerFeignController {
    @Autowired
    EchoService echoService;
    @Autowired
    ConfigurableApplicationContext applicationContext;

    @GetMapping(value = "/echo/{message}")
    public String echo(@PathVariable("message") String message){
        return echoService.echo(message+applicationContext.getEnvironment().getProperty("user.name"));
    }
}
