package cn.chi365.fastjee.cloud.web.admin.controller.view;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysMenu;
import cn.chi365.fastjee.cloud.web.admin.commons.utils.MenuUtil;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SysMenuView TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/5/1 23:06
 */
@Controller
@RequestMapping(value = "/sysMenu")
public class SysMenuView extends CrudView {

    @Autowired
    SysMenuService sysMenuService;


    @RequestMapping("/tree")
    public String list(Model model){
        model.addAttribute("menus",MenuUtil.menusSort(sysMenuService.findList(new SysMenu())));
        return "sys/sysMenuTree";
    }

    @RequestMapping("/form")
    public String form(SysMenu sysMenu,Model model){
        if (sysMenu.getParent()!=null&&!sysMenu.getParent().getKeyId().equals("")) {
            model.addAttribute("menu",sysMenu);
        }else if(!sysMenu.getKeyId().equals("")){
            model.addAttribute("menu",sysMenuService.get(sysMenu.getKeyId()));
        }
        return "sys/sysMenuForm";
    }
}
