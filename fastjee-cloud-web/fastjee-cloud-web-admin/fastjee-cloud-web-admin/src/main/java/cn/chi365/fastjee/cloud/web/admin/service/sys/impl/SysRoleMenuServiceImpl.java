package cn.chi365.fastjee.cloud.web.admin.service.sys.impl;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysMenu;
import cn.chi365.fastjee.cloud.commons.domain.sys.SysRole;
import cn.chi365.fastjee.cloud.commons.domain.sys.SysRoleMenu;
import cn.chi365.fastjee.cloud.web.admin.dao.sys.SysRoleMenuDao;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysRoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SysRoleMenuServiceImpl TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/5/7 18:23
 */
@Service
@Transactional(readOnly = true)
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected SysRoleMenuDao dao;


    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    public List<SysRoleMenu> findList(SysRoleMenu entity) {
        return dao.findList(entity);
    }


    /**
     * 保存数据
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int save(SysRoleMenu entity) {
        return dao.insert(entity);
    }


    /**
     * 删除数据（物理删除，逻辑删除调用更新方法）
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int delete(SysRoleMenu entity){
        return dao.delete(entity);
    }


    @Override
    @Transactional(readOnly = false)
    public int saveMenus(String roleId,String[] menus) {
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        SysRole sysRole = new SysRole();
        sysRole.setKeyId(roleId);
        sysRoleMenu.setRole(sysRole);
        dao.delete(sysRoleMenu);
        if(menus!=null && menus.length>0){
            SysMenu sysMenu = new SysMenu();
            for (String s :
                    menus) {
                sysMenu.setKeyId(s);
                sysRoleMenu.setMenu(sysMenu);
                dao.insert(sysRoleMenu);
            }
        }
        return 0;
    }
}
