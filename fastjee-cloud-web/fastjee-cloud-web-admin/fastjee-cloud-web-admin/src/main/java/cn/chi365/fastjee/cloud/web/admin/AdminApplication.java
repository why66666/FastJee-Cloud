package cn.chi365.fastjee.cloud.web.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/***
 *
 * @author      :YuSir Wang
 * @date        :Created in 0:47 2019/3/29
 * @version     :
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("cn.chi365.fastjee.cloud.web.admin.dao")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }
}
