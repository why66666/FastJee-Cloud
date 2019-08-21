package cn.chi365.fastjee.cloud.commons.domain.sys;

import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;
import cn.chi365.fastjee.cloud.commons.util.RegexpUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SysUser TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/18 11:00
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static final String initPassword = "000000";

    /**
     * 所属单位 teamId
     */
    @NotNull(message = "所属单位不能为空")
    private String teamId;
    /**
     * 用户名
     */
    @Length(min = 6,max = 20,message = "用户名长度必须在6-20之间")
    @NotNull(message = "用户名不能为空")
    private String account;
    /**
     * 密码
     */
    @Length(min = 6,max = 20,message = "密码长度必须在6-20之间")
    private String password;
    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    @Length(min = 0,max = 100,message = "邮箱长度必须在1-100之间")
    private String email;
    /**
     * 手机
     */
    @Pattern(regexp = RegexpUtils.PHONE, message = "手机号格式不正确")
    private String mobile;
    /**
     * 头像
     */
    private String profilePhoto;
    /**
     * 最后一次登陆ip
     */
    private String loginIp;
    /**
     * 最后一次登陆时间
     */
    private Date loginDate;

    /**
     * 对应角色
     */
    private Set<SysRole> roles = new HashSet<>();

    public SysUser(){
        super();
    }

}
