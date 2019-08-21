package cn.chi365.fastjee.cloud.web.admin.controller;

import cn.chi365.fastjee.cloud.commons.dto.PageInfo;
import cn.chi365.fastjee.cloud.web.admin.entity.TestSingleTableExample;
import cn.chi365.fastjee.cloud.web.admin.service.TestSingleTableExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(value = "/singleTableExample")
public class TestSingleTableExampleController {
    @Autowired
    private TestSingleTableExampleService testSingleTableExampleService;

    @PostMapping(value = "save")
    public int save(TestSingleTableExample testSingleTableExample) {
        return testSingleTableExampleService.save(testSingleTableExample);
    }
    @PostMapping(value = "delete")
    public int delete(TestSingleTableExample testSingleTableExample) {
        return testSingleTableExampleService.delete(testSingleTableExample);
    }
    @PostMapping(value = "edit")
    public int edit(TestSingleTableExample testSingleTableExample) {
        return testSingleTableExampleService.edit(testSingleTableExample);
    }
    @GetMapping(value = "findById")
    public TestSingleTableExample findById(TestSingleTableExample testSingleTableExample) {
        return testSingleTableExampleService.findById(testSingleTableExample);
    }
    @GetMapping(value = "list")
    public String listAll(Model model) {
        return "singleTableExample";
    }
    @PostMapping(value = "deleteAll")
    public int deleteAll(int[] ids) {
        return testSingleTableExampleService.deleteAll(ids);
    }

/*    @GetMapping(value = "pageList")
    @ResponseBody
    public PageInfo<TestSingleTableExample> pageList(DatatableParams datatableParams){
        System.out.println("获得请求"+datatableParams.toString());
        return testSingleTableExampleService.pageList(datatableParams);
    }*/
}
