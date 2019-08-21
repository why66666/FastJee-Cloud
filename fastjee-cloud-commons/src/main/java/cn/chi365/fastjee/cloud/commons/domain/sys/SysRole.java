package cn.chi365.fastjee.cloud.commons.domain.sys;

import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

/**
 * SysRole TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/24 12:19
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity {

    /**
     * 单位Id
     */
    private String teamId;
    /**
     * 角色名
     */
    private String name;
    /**
     * 描述
     */
    private String info;

    /**
     * 对应菜单（权限）
     */
    private Set<SysMenu> menus = new HashSet<>();
}
