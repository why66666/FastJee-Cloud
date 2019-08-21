package cn.chi365.fastjee.cloud.commons.domain.sys;

import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* SysUserRole TODO
*
* @author ：Yusir
* @version ：1.0.0
* @date ：Created in 2019/4/18 11:00
*/
@Data
@ToString(callSuper = true)
public class SysUserRole{

    private static final long serialVersionUID = 1L;
    /**
    * 角色编号 roleId
    */
    private SysRole role;
    /**
    * 用户编号 userId
    */
    private SysUser user;
}
