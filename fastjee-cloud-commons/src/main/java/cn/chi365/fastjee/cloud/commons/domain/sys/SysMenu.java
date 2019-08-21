package cn.chi365.fastjee.cloud.commons.domain.sys;

import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * SysMenu TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/24 12:29
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends BaseEntity {
    /**
     * 父菜单
     */
    private SysMenu parent;
    /**
     * 所有父菜单
     */
    private String parentIds;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * url
     */
    private String url;
    /**
     * 图标
     */
    private String icon;
    /**
     * 是否显示（显示是菜单‘1’，不显示仅是权限‘0’）
     */
    private String isShow;
    /**
     * 权限
     */
    private String permission;
    /**
     * 子菜单
     */
    private List<SysMenu> children;
}
