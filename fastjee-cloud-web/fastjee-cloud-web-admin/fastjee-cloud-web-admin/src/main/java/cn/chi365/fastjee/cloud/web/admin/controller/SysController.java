package cn.chi365.fastjee.cloud.web.admin.controller;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysMenu;
import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import cn.chi365.fastjee.cloud.commons.dto.BaseResult;
import cn.chi365.fastjee.cloud.web.admin.commons.utils.MenuUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * LoginController TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/24 23:31
 */
@RestController
public class SysController {

    @GetMapping(value = "/doLogin")
    public BaseResult doLogin(SysUser sysUser, HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getAccount(),sysUser.getPassword());
        try {
            subject.login(token);
            SysUser user = (SysUser) subject.getPrincipal();
            List<SysMenu> treeMenus = MenuUtil.getTreeMenus(MenuUtil.getMenus(user));
            List<SysMenu> sortMenus = MenuUtil.menusSort(treeMenus);
            session.setAttribute("menus", sortMenus);
            SysUser sessionUser = new SysUser();
            sessionUser.setAccount(user.getAccount());
            sessionUser.setProfilePhoto(user.getProfilePhoto());
            session.setAttribute("user",sessionUser);
            return BaseResult.success();
        } catch (UnknownAccountException e) {
            return BaseResult.fail("用户名不存在");
        } catch (IncorrectCredentialsException e){
            return BaseResult.fail("密码错误");
        }
    }


}
