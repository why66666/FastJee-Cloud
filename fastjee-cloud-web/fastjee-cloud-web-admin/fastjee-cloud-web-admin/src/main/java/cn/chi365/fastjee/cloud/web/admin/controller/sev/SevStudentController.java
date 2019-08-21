package cn.chi365.fastjee.cloud.web.admin.controller.sev;

import cn.chi365.fastjee.cloud.commons.domain.sev.SevStudent;
import cn.chi365.fastjee.cloud.commons.dto.BaseResult;
import cn.chi365.fastjee.cloud.web.admin.controller.CrudController;
import cn.chi365.fastjee.cloud.web.admin.service.sev.SevStudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;


/**
* SevStudentController GEN
*
* @author ：Yusir
* @version ：1.0.0
* @date ：2019/05/134 21:17
*/
@RestController
@RequestMapping(value = "/sevStudent")
public class SevStudentController extends CrudController<SevStudent, SevStudentService> {

    @PostMapping(value = "/save")
    public BaseResult save(SevStudent sevStudent) {
        BaseResult vld = beanValidator((sevStudent));
        if (vld == null) {
            int rs = service.save(sevStudent);
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
