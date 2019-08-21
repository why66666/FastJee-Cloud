package cn.chi365.fastjee.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/***
 * gateway
 * @author      :YuSir Wang
 * @date        :Created in 15:56 2019/3/28
 * @version     :1.0.0-SNAPSHOT
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }
}
