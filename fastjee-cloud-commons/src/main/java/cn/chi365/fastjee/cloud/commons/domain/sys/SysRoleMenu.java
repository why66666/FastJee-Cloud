package cn.chi365.fastjee.cloud.commons.domain.sys;

import lombok.Data;
import lombok.ToString;

/**
 * SysRoleMenu TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/5/7 18:17
 */
@Data
@ToString(callSuper = true)
public class SysRoleMenu {
    private static final long serialVersionUID = 1L;

    /**
     * 角色编号 roleId
     */
    private SysRole role;
    /**
     * 菜单编号 menuId
     */
    private SysMenu menu;
}
