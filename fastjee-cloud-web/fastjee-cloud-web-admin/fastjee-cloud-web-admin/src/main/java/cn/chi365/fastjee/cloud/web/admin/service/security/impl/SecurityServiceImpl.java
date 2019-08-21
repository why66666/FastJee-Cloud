package cn.chi365.fastjee.cloud.web.admin.service.security.impl;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import cn.chi365.fastjee.cloud.web.admin.dao.sys.SysUserDao;
import cn.chi365.fastjee.cloud.web.admin.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SecurityServiceImpl TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/24 13:59
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    SysUserDao sysUserDao;

    @Override
    public SysUser selectURMByAccount(String account) {
        return sysUserDao.selectURMByAccount(account);
    }
}
