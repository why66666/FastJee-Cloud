package cn.chi365.fastjee.cloud.web.admin.controller;

import cn.chi365.fastjee.cloud.commons.domain.sys.*;
import cn.chi365.fastjee.cloud.commons.dto.BaseResult;
import cn.chi365.fastjee.cloud.web.admin.commons.utils.MenuUtil;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysMenuService;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysRoleMenuService;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysRoleService;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* SysUserRoleController GEN
*
* @author ：Yusir
* @version ：1.0.0
* @date ：2019/05/126 16:40
*/
@RestController
@RequestMapping(value = "/sysPermission")
public class SysPermissionController {

    @Autowired
    SysUserRoleService service;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Autowired
    SysMenuService sysMenuService;

    @GetMapping(value = "/selectRole")
    public List<SysRole> selectRole(){
        SysRole sysRole = new SysRole();
        sysRole.setDataStatus("0");
        return sysRoleService.findList(sysRole);
    }

    @GetMapping(value = "/selectByUser")
    public List<SysUserRole> selectByUser(SysUserRole sysUserRole){
        return service.findList(sysUserRole);
    }

    @PostMapping(value = "/saveRole")
    public BaseResult saveRole(String userId,String[] roles){
        service.saveRoles(userId,roles);
        return BaseResult.success("保存成功！");
    }


    @GetMapping(value = "/selectMenu")
    public List<SysMenu> selectMenu(){
        SysMenu sysMenu = new SysMenu();
        sysMenu.setDataStatus("0");
        return MenuUtil.menusSort(sysMenuService.findList(sysMenu));
    }

    @GetMapping(value = "/selectByRole")
    public List<SysRoleMenu> selectByRole(SysRoleMenu sysRoleMenu){
        return sysRoleMenuService.findList(sysRoleMenu);
    }

    @PostMapping(value = "/saveMenu")
    public BaseResult saveMenu(String roleId,String[] menus){
        sysRoleMenuService.saveMenus(roleId,menus);
        return BaseResult.success("保存成功！");
    }
}
