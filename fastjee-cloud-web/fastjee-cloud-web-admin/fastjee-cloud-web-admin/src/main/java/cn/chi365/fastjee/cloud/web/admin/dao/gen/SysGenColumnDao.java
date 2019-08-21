package cn.chi365.fastjee.cloud.web.admin.dao.gen;

import cn.chi365.fastjee.cloud.commons.domain.gen.SysGenColumn;
import cn.chi365.fastjee.cloud.commons.persistence.dao.CrudDao;

import java.util.Map;

/**
 * SysGenColumnDao TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/26 16:56
 */
public interface SysGenColumnDao extends CrudDao<SysGenColumn> {
    int createTable(Map<String, Object> root);
    int getCountByTableName(String tableName);
}
