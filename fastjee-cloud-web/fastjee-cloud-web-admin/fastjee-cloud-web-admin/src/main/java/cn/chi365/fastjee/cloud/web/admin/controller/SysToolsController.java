package cn.chi365.fastjee.cloud.web.admin.controller;

import cn.chi365.fastjee.cloud.commons.dto.BaseResult;
import cn.chi365.fastjee.cloud.web.admin.commons.utils.WebUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * SysToolsController TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/5/9 17:39
 */
@RestController
@RequestMapping(value = "/sysTools")
public class SysToolsController {
    @GetMapping(value = "/crawler/getImgs")
    public BaseResult crawler(String httpUrl){
        httpUrl = "http://" + httpUrl;
        System.out.println(httpUrl);
        try {
            List<String> imgs = WebUtil.getImagePathList(httpUrl);
            return BaseResult.success("1",imgs);
        } catch (IOException e) {
            e.printStackTrace();
            return BaseResult.fail("服务器错误！");
        }
    }
}
