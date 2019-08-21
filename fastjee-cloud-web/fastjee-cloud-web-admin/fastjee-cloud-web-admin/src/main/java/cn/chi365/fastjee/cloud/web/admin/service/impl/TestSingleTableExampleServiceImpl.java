package cn.chi365.fastjee.cloud.web.admin.service.impl;

import cn.chi365.fastjee.cloud.commons.dto.PageInfo;
import cn.chi365.fastjee.cloud.web.admin.dao.TestSingleTableExampleDao;
import cn.chi365.fastjee.cloud.web.admin.entity.TestSingleTableExample;
import cn.chi365.fastjee.cloud.web.admin.service.TestSingleTableExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class TestSingleTableExampleServiceImpl implements TestSingleTableExampleService {
    @Autowired
    TestSingleTableExampleDao dao;

    @Override
    @Transactional(readOnly = false)
    public int save(TestSingleTableExample testSingleTableExample) {
        return dao.save(testSingleTableExample);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(TestSingleTableExample testSingleTableExample) {
        return dao.delete(testSingleTableExample);
    }

    @Override
    @Transactional(readOnly = false)
    public int edit(TestSingleTableExample testSingleTableExample) {
        return dao.edit(testSingleTableExample);
    }

    @Override
    public TestSingleTableExample findById(TestSingleTableExample testSingleTableExample) {
        return dao.findById(testSingleTableExample);
    }

    @Override
    public List<TestSingleTableExample> listAll() {
        return dao.listAll();
    }

    @Override
    @Transactional(readOnly = false)
    public int deleteAll(int[] ids) {
        return dao.deleteAll(ids);
    }

    @Override
    public PageInfo<TestSingleTableExample> pageList(Map<String,String> datatableParams) {
        int count = dao.selectCount();
        List<TestSingleTableExample> list = dao.pageList(datatableParams);
        PageInfo<TestSingleTableExample> pageInfo = new PageInfo<>(Integer.parseInt(datatableParams.get("draw")),count,count,list,null);
        return pageInfo;
    }
}
