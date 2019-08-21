package cn.chi365.fastjee.cloud.web.admin.service.security;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;

/**
 * SecurityService TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/24 13:58
 */
public interface SecurityService {
    public SysUser selectURMByAccount(String account);
}
