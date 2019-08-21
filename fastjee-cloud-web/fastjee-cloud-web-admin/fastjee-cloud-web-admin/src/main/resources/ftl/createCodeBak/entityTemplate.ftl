package cn.chi365.fastjee.cloud.commons.domain.sev;

import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* ${cCamelCase} TODO
*
* @author ：Yusir
* @version ：1.0.0
* @date ：Created in 2019/4/18 11:00
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ${cCamelCase} extends BaseEntity {

    private static final long serialVersionUID = 1L;
<#list columnsList as column>
    /**
    * ${column.columnComment} ${column.coNaCamelCase}
    */
<#if column.javaType == 'Date'>
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
</#if>
    private ${column.javaType} ${column.coNaCamelCase};
</#list>
    public ${cCamelCase}(){
        super();
    }
}
