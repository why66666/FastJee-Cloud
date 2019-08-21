package cn.chi365.fastjee.cloud.web.admin.controller.sev.view;

import cn.chi365.fastjee.cloud.commons.domain.sev.SevStudent;
import cn.chi365.fastjee.cloud.web.admin.service.sev.SevStudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import cn.chi365.fastjee.cloud.web.admin.controller.view.CrudView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* SevStudentView GEN
*
* @author ：Yusir
* @version ：1.0.0
* @date ：2019/05/134 21:17
*/

@Controller
@RequestMapping(value = "/sevStudent")
public class SevStudentView extends CrudView {

    @Autowired
    SevStudentService service;

    @RequiresPermissions("sev:student:list")
    @GetMapping(value = "/list")
    public String list() {
        return "sev/sevStudentList";
    }

    @RequiresPermissions("sev:student:form")
    @GetMapping(value = "/form/{row}")
    public String form(@PathVariable(value = "row") String row,Model model) {
        model.addAttribute("rowData",row.equals("0")?new SevStudent():service.get(row));
        return "sev/sevStudentForm";
    }
}