package cn.chi365.fastjee.cloud.web.admin.service.sys.impl;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysRole;
import cn.chi365.fastjee.cloud.commons.persistence.service.CrudService;
import cn.chi365.fastjee.cloud.web.admin.dao.sys.SysRoleDao;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* SysRoleServiceImpl TODO
*
* @author ：Yusir
* @version ：1.0.0
* @date ：Created in 2019/4/19 18:05
*/
@Service
@Transactional(readOnly = true)
public class SysRoleServiceImpl extends CrudService<SysRole, SysRoleDao> implements SysRoleService {
}
