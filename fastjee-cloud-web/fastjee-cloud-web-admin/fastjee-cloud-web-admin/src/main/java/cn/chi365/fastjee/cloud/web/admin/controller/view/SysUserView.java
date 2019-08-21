package cn.chi365.fastjee.cloud.web.admin.controller.view;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysUserView TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/20 21:15
 */
@Controller
@RequestMapping(value = "/sysUser")
public class SysUserView extends CrudView {
    @Autowired
    SysUserService service;
    @RequiresPermissions("sys:user:list")
    @GetMapping(value = "/list")
    public String list() {
        return "sys/sysUserList";
    }

    @RequiresPermissions("sys:user:form")
    @GetMapping(value = "/form/{row}")
    public String form(@PathVariable(value = "row") String row,Model model) {
        model.addAttribute("rowData",row.equals("0")?new SysUser():service.get(row));
        return "sys/sysUserForm";
    }
}
