package cn.chi365.fastjee.cloud.web.admin.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysPermissionView TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/5/6 15:44
 */
@Controller
@RequestMapping(value = "/sysPermission")
public class SysPermissionView {
    @RequestMapping(value = "/edit")
    public String edit(){
        return "sys/sysPermissionEdit";
    }
}
