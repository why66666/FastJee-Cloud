package cn.chi365.fastjee.cloud.commons.domain.gen;

/**
 * DataTypeEnum TODO
 * 数据类型枚举类（datatype，javatype）
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/27 18:02
 */
public enum  DataTypeEnum {
    INT("int","Integer"),

    TINYINT("tinyint","Integer"),

    FLOAT("float","Float"),

    DOUBLE("double","Double"),

    CHAR("char","String"),

    VARCHAR("varchar","String"),

    DATETIME("datetime","Date");


    private String dataType;
    private String javaType;

    DataTypeEnum(String dataType,String javaType){
        this.dataType = dataType;
        this.javaType = javaType;
    }


    public static String getJavaType(String dataType) {
        for (DataTypeEnum d :
                DataTypeEnum.values()) {
            if (d.dataType.equals(dataType)){
                return d.javaType;
            }
        }
        return null;
    }
}
