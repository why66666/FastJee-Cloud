package cn.chi365.fastjee.cloud.web.admin.service.sys.impl;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import cn.chi365.fastjee.cloud.commons.persistence.service.CrudService;
import cn.chi365.fastjee.cloud.web.admin.dao.sys.SysUserDao;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SysUserServiceImpl TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/19 18:05
 */
@Service
@Transactional(readOnly = true)
public class SysUserServiceImpl extends CrudService<SysUser, SysUserDao> implements SysUserService {
    @Override
    public int getCount() {
        return dao.getCount();
    }
}
