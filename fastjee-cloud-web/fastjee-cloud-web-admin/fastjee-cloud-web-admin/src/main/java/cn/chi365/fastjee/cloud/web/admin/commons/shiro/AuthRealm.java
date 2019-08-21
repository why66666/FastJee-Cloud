package cn.chi365.fastjee.cloud.web.admin.commons.shiro;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysMenu;
import cn.chi365.fastjee.cloud.commons.domain.sys.SysRole;
import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import cn.chi365.fastjee.cloud.web.admin.service.security.SecurityService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.*;

/**
 * Shiro Realm
 *
 * 授权&登录
 * @version ：1.0.0
 * @date ：Created in 2019/4/24 15:20
 */
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    @Lazy
    private SecurityService securityService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        //定义rolesList存放roleName
        List<String> rolesList = new ArrayList<>();
        //定义permissions存放ModuleName
        List<String> permissions=new ArrayList<>();
        Set<SysRole> roles = sysUser.getRoles();
        if(roles.size()>0) {
            for(SysRole role : roles) {
                rolesList.add(role.getName());
                Set<SysMenu> menus = role.getMenus();
                if(menus.size()>0) {
                    for(SysMenu menu : menus) {
                        permissions.add(menu.getPermission());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中
        info.addRoles(rolesList);//将角色放入shiro中
        return info;
    }

    //认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
        UsernamePasswordToken utoken=(UsernamePasswordToken) authenticationToken;//获取用户输入的token
        String account = utoken.getUsername();
        //调service根据account查user
        SysUser user = securityService.selectURMByAccount(account);
        if (user==null){
            return null;
        }
        System.out.println("user==========>>>>"+user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
        return info;
    }
}
