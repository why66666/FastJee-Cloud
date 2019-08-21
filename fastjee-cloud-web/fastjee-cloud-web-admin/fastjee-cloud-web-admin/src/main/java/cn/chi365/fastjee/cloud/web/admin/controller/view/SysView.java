package cn.chi365.fastjee.cloud.web.admin.controller.view;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * LoginView TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/24 18:49
 */
@Controller
public class SysView extends CrudView {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    SessionDAO sessionDAO;

    @GetMapping(value = "/login")
    public String toLogin(){
       return "login" ;
    }

    @GetMapping(value = "/index")
    public String index(Model model){
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("sessionCount",sessionDAO.getActiveSessions().size());
        model.addAttribute("ip", cn.chi365.fastjee.cloud.web.admin.commons.utils.IpUtil.getIp());
        model.addAttribute("usrNum",sysUserService.getCount());
        return "index" ;
    }

    @GetMapping(value = "/sysGen/forward")
    public String codeGen(){
        return "sys/gen/sysGenForward";
    }



    @GetMapping(value = "/logout")
    public String loginOut(Model model){
        Subject subject = SecurityUtils.getSubject();
        model.addAttribute("account",((SysUser)subject.getPrincipal()).getAccount());
        subject.logout();
        return "redirect:/kickout";
    }
    @GetMapping(value = "/sysGen/reverse")
    public String codeGenRe(){
        return "sys/gen/sysGenReverse";
    }

    @GetMapping(value = "/sysGen/mould")
    public String genMould(){
        return "sys/gen/sysGenMould";
    }


    @GetMapping(value = "/kickout")
    public String kickout(Model model){
        return "kickout";
    }
}
