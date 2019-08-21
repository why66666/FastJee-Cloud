package cn.chi365.fastjee.cloud.web.admin.dao.gen;

import cn.chi365.fastjee.cloud.commons.domain.gen.ColumnEntiry;
import cn.chi365.fastjee.cloud.commons.domain.gen.TableEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysGenTableDao TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/30 12:57
 */
public interface SysGenTableDao {
    List<TableEntity> querySevTables();
    List<ColumnEntiry> selectColumnByTableName(@Param("databaseName") String databaseName, @Param("table_name") String table_name);
}
