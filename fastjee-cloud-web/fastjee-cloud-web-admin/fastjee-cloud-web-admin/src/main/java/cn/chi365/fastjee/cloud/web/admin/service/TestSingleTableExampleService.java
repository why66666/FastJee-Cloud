package cn.chi365.fastjee.cloud.web.admin.service;

import cn.chi365.fastjee.cloud.commons.dto.PageInfo;
import cn.chi365.fastjee.cloud.web.admin.entity.TestSingleTableExample;

import java.util.List;
import java.util.Map;

public interface TestSingleTableExampleService {
    int save(TestSingleTableExample testSingleTableExample);
    int delete(TestSingleTableExample testSingleTableExample);
    int edit(TestSingleTableExample testSingleTableExample);
    TestSingleTableExample findById(TestSingleTableExample testSingleTableExample);
    List<TestSingleTableExample> listAll();
    int deleteAll(int[] ids);
    PageInfo<TestSingleTableExample> pageList(Map<String,String> datatableParams);
}
