package cn.chi365.fastjee.cloud.web.admin.service.sys;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import cn.chi365.fastjee.cloud.commons.persistence.service.BaseService;

import java.util.List;

public interface SysUserService extends BaseService<SysUser> {
    int getCount();
}
