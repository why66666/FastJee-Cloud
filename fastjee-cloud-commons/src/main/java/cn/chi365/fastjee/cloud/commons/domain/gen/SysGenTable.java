package cn.chi365.fastjee.cloud.commons.domain.gen;

import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * SysGenTable TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/30 12:00
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysGenTable extends BaseEntity {
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 	表的注释、备注
     */
    private String tableComment;

    public SysGenTable(String tableName,String tableComment){
        this.tableComment = tableComment;
        this.tableName = tableName;
    }
}
