package cn.chi365.fastjee.cloud.web.admin.controller.sev;

import cn.chi365.fastjee.cloud.commons.domain.sev.${cCamelCase};
import cn.chi365.fastjee.cloud.commons.dto.BaseResult;
import cn.chi365.fastjee.cloud.web.admin.controller.CrudController;
import cn.chi365.fastjee.cloud.web.admin.service.sev.${cCamelCase}Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


/**
* ${cCamelCase}Controller GEN
*
* @author ：Yusir
* @version ：1.0.0
* @date ：${nowDate?string("YYYY/MM/DD HH:mm")}
*/
@RestController
@RequestMapping(value = "/${camelCase}")
public class ${cCamelCase}Controller extends CrudController<${cCamelCase}, ${cCamelCase}Service> {

    @PostMapping(value = "/save")
    public BaseResult save(${cCamelCase} ${camelCase}) {
        BaseResult vld = beanValidator((${camelCase}));
        if (vld == null) {
            int rs = service.save(${camelCase});
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
