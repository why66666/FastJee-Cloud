package cn.chi365.fastjee.cloud.web.admin.dao;

import cn.chi365.fastjee.cloud.web.admin.entity.TestSingleTableExample;

import java.util.List;
import java.util.Map;

public interface TestSingleTableExampleDao {
    int save(TestSingleTableExample testSingleTableExample);
    int delete(TestSingleTableExample testSingleTableExample);
    int edit(TestSingleTableExample testSingleTableExample);
    TestSingleTableExample findById(TestSingleTableExample testSingleTableExample);
    List<TestSingleTableExample> listAll();
    int deleteAll(int[] ids);
    int selectCount();
    List<TestSingleTableExample> pageList(Map<String,String> datatableParams);
}
