package cn.chi365.fastjee.cloud.web.admin.dao.sys;


import cn.chi365.fastjee.cloud.commons.domain.sys.SysUserRole;

import java.util.List;
import java.util.Map;

public interface SysUserRoleDao{

    /**
     * 查询数据列表
     * @param entity
     * @return
     */
    public List<SysUserRole> findList(SysUserRole entity);

    /**
     * 查询所有数据列表
     * @return
     */
    public List<SysUserRole> findAllList();


    /**
     * 插入数据
     * @param entity
     * @return
     */
    public int insert(SysUserRole entity);

    /**
     * 删除数据（该方法为物理删除，逻辑删除使用update更新data_status字段）
     * @param entity
     * @return
     */
    public int delete(SysUserRole entity);
}
