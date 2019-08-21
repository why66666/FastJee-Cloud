package cn.chi365.fastjee.cloud.web.admin.commons.utils;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysMenu;
import cn.chi365.fastjee.cloud.commons.domain.sys.SysRole;
import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * MenuUtil TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/25 21:23
 */
public class MenuUtil {


    protected static Logger logger = LoggerFactory.getLogger(MenuUtil.class);

    /**
     * 获取有序菜单 递归实现
     * @param sysMenus
     * @return
     */
    public static List<SysMenu> menusSort(List<SysMenu> sysMenus){
        Comparator<SysMenu> comparator = new Comparator<SysMenu>() {
            @Override
            public int compare(SysMenu o1, SysMenu o2) {
                if (o1.getSort() < o2.getSort()) {
                    return -1;
                } else if (o1.getSort() > o2.getSort()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        sysMenus.sort(comparator);


        return sortChildren(sysMenus,comparator);
    }

    /**
     * 递归方法
     * @param sysMenus
     * @param comparator
     * @return
     */
    public static List<SysMenu> sortChildren(List<SysMenu> sysMenus, Comparator<SysMenu> comparator){
        for (SysMenu sysMenu:sysMenus) {
            if(sysMenu.getChildren()!=null&&sysMenu.getChildren().size()>0){
                sysMenu.getChildren().sort(comparator);
                sortChildren(sysMenu.getChildren(),comparator);
            }
        }
        return sysMenus;
    }


    /**
     * 获取user所有menu
     * @param sysUser
     * @return
     */
    public static List<SysMenu> getMenus(SysUser sysUser){
        List<SysMenu> menus=new ArrayList<>();
        Set<SysRole> roles = sysUser.getRoles();
        if(roles.size()>0) {
            for(SysRole role : roles) {
                logger.info("role---->"+role.getName());
                Set<SysMenu> menuSet = role.getMenus();
                if(menuSet.size()>0) {
                    for (SysMenu menu : menuSet) {
                        logger.info("menu---->"+menu.getName());
                        if (menu.getIsShow().equals("1")){
                            menus.add(menu);
                        }
                    }
                }
            }
        }
        return menus;
    }


    /**
     * menu list转tree
     * 调用findChildren递归实现
     * @param menus
     * @return
     */
    public static List<SysMenu> getTreeMenus(List<SysMenu> menus) {
        List<SysMenu> treeList = new ArrayList<>();
        for (SysMenu parent :
                menus) {
            if (parent.getKeyId().equals(parent.getParent().getKeyId())) {
                treeList.add(findChildren(parent, menus));
            }
        }
        return treeList;
    }

    /**
     * 递归方法
     * @param parent
     * @param treeList
     * @return
     */
    private static SysMenu findChildren(SysMenu parent, List<SysMenu> treeList) {
        for (SysMenu child : treeList) {
            if (parent.getKeyId().equals(child.getParent().getKeyId()) && !child.getKeyId().equals(parent.getKeyId())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(findChildren(child, treeList));
            }
        }
        return parent;
    }
}
