package cn.chi365.fastjee.cloud.web.admin.controller;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysRole;
import cn.chi365.fastjee.cloud.commons.dto.BaseResult;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
* SysRoleController GEN
*
* @author ：Yusir
* @version ：1.0.0
* @date ：2019/05/121 19:54
*/
@RestController
@RequestMapping(value = "/sysRole")
public class SysRoleController extends CrudController<SysRole, SysRoleService> {

    @PostMapping(value = "/save")
    public BaseResult save(SysRole sysRole) {
        BaseResult vld = beanValidator((sysRole));
        if (vld == null) {
            int rs = service.save(sysRole);
            return rs > 0 ? BaseResult.success("数据提交成功！") : BaseResult.fail("数据提交失败!");
        }
        return vld;
    }

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
}
