package cn.chi365.fastjee.cloud.web.admin.service.sev.impl;

import cn.chi365.fastjee.cloud.commons.domain.sev.${cCamelCase};
import cn.chi365.fastjee.cloud.commons.persistence.service.CrudService;
import cn.chi365.fastjee.cloud.web.admin.dao.sev.${cCamelCase}Dao;
import cn.chi365.fastjee.cloud.web.admin.service.sev.${cCamelCase}Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* ${cCamelCase}ServiceImpl TODO
*
* @author ：Yusir
* @version ：1.0.0
* @date ：Created in 2019/4/19 18:05
*/
@Service
@Transactional(readOnly = true)
public class ${cCamelCase}ServiceImpl extends CrudService<${cCamelCase}, ${cCamelCase}Dao> implements ${cCamelCase}Service {
}
