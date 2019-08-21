package cn.chi365.fastjee.cloud.commons.domain.sev;

import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* SevStudent TODO
*
* @author ：Yusir
* @version ：1.0.0
* @date ：Created in 2019/4/18 11:00
*/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SevStudent extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
    * 姓名 stuName
    */
    private String stuName;
    /**
    * 时间 stuTime
    */
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date stuTime;
    /**
    * 年龄 stuAge
    */
    private Integer stuAge;
    public SevStudent(){
        super();
    }
}
