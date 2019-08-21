package cn.chi365.fastjee.cloud.web.admin.service.sys;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysRoleMenu;

import java.util.List;

/**
 * SysRoleMenuService TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/5/7 18:20
 */
public interface SysRoleMenuService {
    public List<SysRoleMenu> findList(SysRoleMenu entity);
    public int save(SysRoleMenu entity);
    public int delete(SysRoleMenu entity);
    public int saveMenus(String roleId,String[] menus);
}
