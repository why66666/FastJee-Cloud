package cn.chi365.fastjee.cloud.web.admin.dao.sys;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysRoleMenu;

import java.util.List;

/**
 * SysRoleMenuDao TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/5/7 18:25
 */
public interface SysRoleMenuDao {
    /**
     * 查询数据列表
     * @param entity
     * @return
     */
    public List<SysRoleMenu> findList(SysRoleMenu entity);

    /**
     * 查询所有数据列表
     * @return
     */
    public List<SysRoleMenu> findAllList();


    /**
     * 插入数据
     * @param entity
     * @return
     */
    public int insert(SysRoleMenu entity);

    /**
     * 删除数据（该方法为物理删除，逻辑删除使用update更新data_status字段）
     * @param entity
     * @return
     */
    public int delete(SysRoleMenu entity);
}
