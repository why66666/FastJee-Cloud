package cn.chi365.fastjee.cloud.web.admin.controller;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysMenu;
import cn.chi365.fastjee.cloud.commons.dto.BaseResult;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SysMenuController TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/5/2 15:23
 */
@RestController
@RequestMapping(value = "/sysMenu")
public class SysMenuController extends CrudController<SysMenu, SysMenuService> {

    @PostMapping(value = "/delete")
    public BaseResult delete(String ids){
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            int rs = service.deleteMulti(idArray);
            return rs > 0 ? BaseResult.success("数据删除成功！！！！") : BaseResult.fail("数据删除失败!");
        }else {
            return BaseResult.fail("删除内容失败！！！");
        }
    }

    @PostMapping(value = "/saveSort")
    public BaseResult saveSort(String[] ids, Integer[] sorts){
        for (int i = 0; i < ids.length; i++) {
            SysMenu menu = new SysMenu();
            menu.setKeyId(ids[i]);
            menu.setSort(sorts[i]);
            service.save(menu);
        }
        return BaseResult.success("排序已保存！");
    }

    @PostMapping(value = "/save")
    public BaseResult save(SysMenu sysMenu){
        BaseResult vld = beanValidator((sysMenu));
        if (vld == null) {
            int rs = service.save(sysMenu);
            return rs > 0 ? BaseResult.success("数据提交成功！") : BaseResult.fail("数据提交失败!");
        }
        return vld;
    }
}
