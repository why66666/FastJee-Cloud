package cn.chi365.fastjee.cloud.web.admin.controller.sev.view;

import cn.chi365.fastjee.cloud.commons.domain.sev.${cCamelCase};
import cn.chi365.fastjee.cloud.web.admin.service.sev.${cCamelCase}Service;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import cn.chi365.fastjee.cloud.web.admin.controller.view.CrudView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* ${cCamelCase}View GEN
*
* @author ：Yusir
* @version ：1.0.0
* @date ：${nowDate?string("YYYY/MM/DD HH:mm")}
*/

@Controller
@RequestMapping(value = "/${camelCase}")
public class ${cCamelCase}View extends CrudView {

    @Autowired
    ${cCamelCase}Service service;

    @RequiresPermissions("${permCase}:list")
    @GetMapping(value = "/list")
    public String list() {
        return "sev/${camelCase}List";
    }

    @RequiresPermissions("${permCase}:form")
    @GetMapping(value = "/form/{row}")
    public String form(@PathVariable(value = "row") String row,Model model) {
        model.addAttribute("rowData",row.equals("0")?new ${cCamelCase}():service.get(row));
        return "sev/${camelCase}Form";
    }
}