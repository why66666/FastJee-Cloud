package cn.chi365.fastjee.cloud.web.admin.service.sys;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysUserRole;
import cn.chi365.fastjee.cloud.commons.dto.PageInfo;
import cn.chi365.fastjee.cloud.commons.persistence.service.BaseService;

import java.util.List;
import java.util.Map;

/**
* SysUserRoleService GEN
*
* @author ：Yusir
* @version ：1.0.0
* @date ：2019/05/126 16:40
*/
public interface SysUserRoleService{
    public List<SysUserRole> findList(SysUserRole entity);
    public int save(SysUserRole entity);
    public int delete(SysUserRole entity);
    public int saveRoles(String userId,String[] roles);
}
