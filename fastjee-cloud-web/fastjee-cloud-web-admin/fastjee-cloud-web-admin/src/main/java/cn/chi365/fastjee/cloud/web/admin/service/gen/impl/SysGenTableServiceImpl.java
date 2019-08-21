package cn.chi365.fastjee.cloud.web.admin.service.gen.impl;

import cn.chi365.fastjee.cloud.commons.domain.gen.ColumnEntiry;
import cn.chi365.fastjee.cloud.commons.domain.gen.SysGenColumn;
import cn.chi365.fastjee.cloud.commons.domain.gen.SysGenTable;
import cn.chi365.fastjee.cloud.commons.domain.gen.TableEntity;
import cn.chi365.fastjee.cloud.commons.util.IDUtils;
import cn.chi365.fastjee.cloud.web.admin.dao.gen.SysGenColumnDao;
import cn.chi365.fastjee.cloud.web.admin.dao.gen.SysGenTableDao;
import cn.chi365.fastjee.cloud.web.admin.service.gen.SysGenTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * SysGenTableServiceImpl TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/30 12:56
 */
@Service
@Transactional(readOnly = true)
public class SysGenTableServiceImpl implements SysGenTableService {
    @Autowired
    SysGenTableDao dao;
    @Autowired
    SysGenColumnDao sysGenColumnDao;

    @Override
    public List<SysGenTable> selectSevTable() {
        List<TableEntity> tables = dao.querySevTables();
        List<SysGenTable> result = new ArrayList<>();
        for (TableEntity t :
                tables) {
            result.add(new SysGenTable(t.getTableName(), t.getTableComment()));
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false)
    public void anaTableColumn(String tableName) {
        SysGenColumn sysGenColumn = new SysGenColumn();
        List<ColumnEntiry> columnEntiries = dao.selectColumnByTableName("fastjee", tableName);
        for (ColumnEntiry c :
                columnEntiries) {
            if (c.getColumnName().equals("key_id") || c.getColumnName().equals("create_by") || c.getColumnName().equals("create_date") || c.getColumnName().equals("update_by") || c.getColumnName().equals("update_date") || c.getColumnName().equals("remarks") || c.getColumnName().equals("data_status")) {
                continue;
            }
            sysGenColumn.setTableName(tableName);
            sysGenColumn.setColumnName(c.getColumnName());
            sysGenColumn.setColumnComment(c.getColumnComment());
            sysGenColumn.setColumnDefault(c.getColumnDefault());
            sysGenColumn.setIsNullable(c.getIsNullable().equals("YES") ? "on" : "");
            sysGenColumn.setDataType(c.getDataType());
            sysGenColumn.setCharacterMaximumLength(c.getCharacterMaximumLength());
            sysGenColumn.setColumnType(c.getColumnType());
            switch (c.getDataType()) {
                case "datetime":
                    sysGenColumn.setListShow("datetime");
                    sysGenColumn.setFormShow("datetime-local");
                    break;
                case "int":
                    sysGenColumn.setListShow("text");
                    sysGenColumn.setFormShow("number");
                    break;
                case "tinyint":
                    sysGenColumn.setListShow("text");
                    sysGenColumn.setFormShow("number");
                    break;
                default:
                    sysGenColumn.setListShow("text");
                    sysGenColumn.setFormShow("text");
                    break;
            }
            sysGenColumn.setSearchPlus("on");
            sysGenColumn.setFuzzySearch("on");
            sysGenColumn.initRESave();
            sysGenColumn.insertInit();
            sysGenColumnDao.insert(sysGenColumn);
        }
    }


}
