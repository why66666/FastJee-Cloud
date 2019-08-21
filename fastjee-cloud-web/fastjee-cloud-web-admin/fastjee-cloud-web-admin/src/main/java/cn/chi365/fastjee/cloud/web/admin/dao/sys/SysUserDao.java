package cn.chi365.fastjee.cloud.web.admin.dao.sys;


import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import cn.chi365.fastjee.cloud.commons.persistence.dao.CrudDao;

public interface SysUserDao extends CrudDao<SysUser> {
    SysUser selectURMByAccount(String account);
}
