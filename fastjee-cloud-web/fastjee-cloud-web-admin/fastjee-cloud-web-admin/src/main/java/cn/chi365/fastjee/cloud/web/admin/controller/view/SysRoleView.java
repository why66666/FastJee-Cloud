package cn.chi365.fastjee.cloud.web.admin.controller.view;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysRole;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* SysRoleView GEN
*
* @author ：Yusir
* @version ：1.0.0
* @date ：2019/05/121 19:54
*/

@Controller
@RequestMapping(value = "/sysRole")
public class SysRoleView extends CrudView {

    @Autowired
    SysRoleService service;

    @RequiresPermissions("sys:role:list")
    @GetMapping(value = "/list")
    public String list() {
        return "sys/sysRoleList";
    }

    @RequiresPermissions("sys:role:form")
    @GetMapping(value = "/form/{row}")
    public String form(@PathVariable(value = "row") String row,Model model) {
        model.addAttribute("rowData",row.equals("0")?new SysRole():service.get(row));
        return "sys/sysRoleForm";
    }
}