package cn.chi365.fastjee.cloud.commons.domain.gen;

import lombok.Data;

@Data
public class ColumnEntiry {
    private String tableCatalog;
    /**
     * 数据库名
     */
    private String tableSchema;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 字段名
     */
    private String columnName;
    /**
     * 顺序
     */
    private String ordinalPosition;
    /**
     * 默认
     */
    private String columnDefault;
    /**
     * 允许为空
     */
    private String isNullable;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 字符串最长长度
     */
    private String characterMaximumLength;
    /**
     * 字符串长度
     */
    private String characterOctetLength;
    /**
     * 数字精度
     */
    private String numericPrecision;
    /**
     * 数字刻度
     */
    private String numericScale;
    /**
     * 日期时间精度
     */
    private String datetimePrecision;
    /**
     * 字符集名称
     */
    private String characterSetName;
    /**
     * 整理名称
     */
    private String collationName;
    /**
     * 字段类型
     */
    private String columnType;
    /**
     * 约束（主键：PRI）
     */
    private String columnKey;
    /**
     * 额外（自增：auto_increment）
     */
    private String extra;
    /**
     * 权限
     */
    private String privileges;
    /**
     * 字段描述
     */
    private String columnComment;
    /**
     *
     */
    private String generationExpression;
}
