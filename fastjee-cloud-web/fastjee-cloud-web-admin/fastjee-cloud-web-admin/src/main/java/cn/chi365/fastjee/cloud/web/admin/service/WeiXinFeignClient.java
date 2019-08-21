package cn.chi365.fastjee.cloud.web.admin.service;

import com.alibaba.fastjson.JSONObject;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface WeiXinFeignClient {
    @RequestLine("GET /token?grant_type={gt}&appid={appid}&secret={secret}")
    public JSONObject getToken(@Param(value = "gt") String gt, @Param(value = "appid") String appid, @Param(value = "secret") String secret);

    @RequestLine("POST /create?access_token={at}")
    public JSONObject createMenu(@Param(value = "at") String at,String body);
}
