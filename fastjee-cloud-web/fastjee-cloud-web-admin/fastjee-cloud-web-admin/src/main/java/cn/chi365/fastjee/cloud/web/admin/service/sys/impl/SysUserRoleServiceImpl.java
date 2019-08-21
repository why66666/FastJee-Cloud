package cn.chi365.fastjee.cloud.web.admin.service.sys.impl;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysRole;
import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import cn.chi365.fastjee.cloud.commons.domain.sys.SysUserRole;
import cn.chi365.fastjee.cloud.commons.dto.PageInfo;
import cn.chi365.fastjee.cloud.commons.persistence.service.CrudService;
import cn.chi365.fastjee.cloud.web.admin.dao.sys.SysUserDao;
import cn.chi365.fastjee.cloud.web.admin.dao.sys.SysUserRoleDao;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysUserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
* SysUserRoleServiceImpl TODO
*
* @author ：Yusir
* @version ：1.0.0
* @date ：Created in 2019/4/19 18:05
*/
@Service
@Transactional(readOnly = true)
public class SysUserRoleServiceImpl implements SysUserRoleService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected SysUserRoleDao dao;


    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    public List<SysUserRole> findList(SysUserRole entity) {
        return dao.findList(entity);
    }


    /**
     * 保存数据
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int save(SysUserRole entity) {
        return dao.insert(entity);
    }


    /**
     * 删除数据（物理删除，逻辑删除调用更新方法）
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int delete(SysUserRole entity){
        return dao.delete(entity);
    }


    @Override
    @Transactional(readOnly = false)
    public int saveRoles(String userId, String[] roles) {
        SysUserRole sysUserRole = new SysUserRole();
        SysUser sysUser = new SysUser();
        sysUser.setKeyId(userId);
        sysUserRole.setUser(sysUser);
        dao.delete(sysUserRole);
        if(roles!=null && roles.length>0){
            SysRole sysRole = new SysRole();
            for (String s :
                    roles) {
                sysRole.setKeyId(s);
                sysUserRole.setRole(sysRole);
                dao.insert(sysUserRole);
            }
        }
        return 0;
    }

}
