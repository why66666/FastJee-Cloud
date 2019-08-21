package cn.chi365.fastjee.cloud.commons.domain.gen;

import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;
import cn.chi365.fastjee.cloud.commons.util.CamelCaseUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * SysGenColumn TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/26 16:50
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysGenColumn extends BaseEntity {

    private String tableName;
    private String columnName;
    private String columnComment;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private String characterMaximumLength;
    private String columnType;
    private String listShow;
    private String formShow;
    private String searchPlus;
    private String fuzzySearch;
    private String coNaCamelCase;
    private String javaType;


    public SysGenColumn(){
        super();
    }

    public SysGenColumn(String tableName){
        this.tableName = tableName;
    }


    /**
     * 保存字段信息初始化
     */
    public void saveColumnInit() {
        initColumnType();
        initCoNaCamelCase();
        initJavaType();
    }

    /**
     * 根据DataType和characterMaximumLength 填入 columnType
     */
    private void initColumnType() {

        if (this.getDataType().equals("datetime")) {
            this.setColumnType(this.getDataType());
        } else {
            if (this.getDataType().equals("varchar")) {
                if (this.getCharacterMaximumLength() == null || this.getCharacterMaximumLength().equals("")) {
                    this.setColumnType("varchar(255)");
                } else {
                    this.setColumnType("varchar(" + this.getCharacterMaximumLength() + ")");
                }
            } else if (this.getDataType().equals("double") || this.getDataType().equals("float")) {
                this.setColumnType(this.getDataType() + "(" + this.getCharacterMaximumLength() + ",0)");
            } else {
                if (this.getCharacterMaximumLength() == null || this.getCharacterMaximumLength().equals("") || this.getCharacterMaximumLength().equals("0")) {
                    this.setColumnType(this.getDataType());
                } else {
                    this.setColumnType(this.getDataType() + "(" + this.getCharacterMaximumLength() + ")");
                }
            }
        }
    }

    /**
     * 填入驼峰字段名
     */
    private void initCoNaCamelCase(){
        this.coNaCamelCase = CamelCaseUtils.toCamelCase(this.columnName);
    }

    /**
     * 填入javaType
     */
    private void initJavaType(){
        this.javaType = DataTypeEnum.getJavaType(this.dataType);
    }


    public void initRESave(){
        initCoNaCamelCase();
        initJavaType();
    }

}
