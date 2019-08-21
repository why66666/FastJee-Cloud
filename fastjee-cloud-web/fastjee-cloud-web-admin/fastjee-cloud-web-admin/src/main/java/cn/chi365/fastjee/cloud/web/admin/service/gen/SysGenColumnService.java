package cn.chi365.fastjee.cloud.web.admin.service.gen;

import cn.chi365.fastjee.cloud.commons.domain.gen.SysGenColumn;
import cn.chi365.fastjee.cloud.commons.persistence.service.BaseService;

import java.util.Map;

/**
 * SysGenColumnService TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/26 16:54
 */
public interface SysGenColumnService extends BaseService<SysGenColumn> {
    int createTable(Map<String, Object> root);
    int getCountByTableName(String tableName);
}
