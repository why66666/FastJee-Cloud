package cn.chi365.fastjee.cloud.web.admin.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysToolsView TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/5/9 17:40
 */
@Controller
@RequestMapping(value = "/sysTools")
public class SysToolsView {

    @RequestMapping(value = "/crawler")
    public String crawler(){
        return "tools/sysToolsCrawler";
    }

    @GetMapping(value = "/baiduMap")
    public String baiduMap(){
        return "tools/baiduMap";
    }
}
