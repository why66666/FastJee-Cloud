package cn.chi365.fastjee.cloud.web.admin.service.gen;


import cn.chi365.fastjee.cloud.commons.domain.gen.SysGenTable;

import java.util.List;

/**
 * SysGenTableService TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/30 12:53
 */
public interface SysGenTableService {
    List<SysGenTable> selectSevTable();
    void anaTableColumn(String tableName);
}
