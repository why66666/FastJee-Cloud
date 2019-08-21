package cn.chi365.fastjee.cloud.web.admin.controller;

import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import cn.chi365.fastjee.cloud.commons.dto.BaseResult;
import cn.chi365.fastjee.cloud.web.admin.commons.utils.MD5Util;
import cn.chi365.fastjee.cloud.web.admin.service.sys.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


/**
 * SysUserController TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/20 22:51
 */
@RestController
@RequestMapping(value = "/sysUser")
public class SysUserController extends CrudController<SysUser, SysUserService> {

    @PostMapping(value = "/save")
    public BaseResult save(SysUser sysUser) {
        BaseResult vld = beanValidator((sysUser));
        if (vld == null) {
            if (sysUser.getPassword() == null) {
                sysUser.setPassword(MD5Util.GetMD5Object(sysUser.getAccount(),SysUser.initPassword).toString());
            } else{
                sysUser.setPassword(MD5Util.GetMD5Object(sysUser.getAccount(),sysUser.getPassword()).toString());
            }
            int rs = service.save(sysUser);
            return rs > 0 ? BaseResult.success("数据提交成功！") : BaseResult.fail("数据提交失败!");
        }
        return vld;
    }

    @PostMapping(value = "/delete")
    public BaseResult delete(String ids){
        logger.info(ids);
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            int rs = service.deleteMulti(idArray);
            return rs > 0 ? BaseResult.success("数据删除成功！！！！") : BaseResult.fail("数据删除失败!");
        } else {
            return BaseResult.fail("删除内容失败！！！");
        }
    }


}
