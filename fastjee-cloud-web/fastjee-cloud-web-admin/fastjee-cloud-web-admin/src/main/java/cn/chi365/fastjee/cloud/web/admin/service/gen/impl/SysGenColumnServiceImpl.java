package cn.chi365.fastjee.cloud.web.admin.service.gen.impl;

import cn.chi365.fastjee.cloud.commons.domain.gen.SysGenColumn;
import cn.chi365.fastjee.cloud.commons.persistence.service.CrudService;
import cn.chi365.fastjee.cloud.web.admin.dao.gen.SysGenColumnDao;
import cn.chi365.fastjee.cloud.web.admin.service.gen.SysGenColumnService;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * SysGenColumnServiceImpl TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/26 16:55
 */
@Service
@Transactional(readOnly = true)
public class SysGenColumnServiceImpl extends CrudService<SysGenColumn, SysGenColumnDao> implements SysGenColumnService {
    @Override
    @Transactional(readOnly = false)
    public int createTable(Map<String, Object> root) {
        return dao.createTable(root);
    }

    @Override
    public int getCountByTableName(String tableName) {
        return dao.getCountByTableName(tableName);
    }
}
