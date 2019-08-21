package cn.chi365.fastjee.cloud.web.admin.controller;


import cn.chi365.fastjee.cloud.commons.beanValidators.BeanValidators;
import cn.chi365.fastjee.cloud.commons.dto.BaseResult;
import cn.chi365.fastjee.cloud.commons.dto.PageInfo;
import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;
import cn.chi365.fastjee.cloud.commons.persistence.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CrudController TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/20 22:19
 */
public abstract class CrudController<T extends BaseEntity,S extends BaseService<T>> extends BaseController{
    /**
     * 注入Service层对象
     */
    @Autowired
    protected S service;

    /**
     * 验证Bean实例对象
     */
    @Autowired
    protected Validator validator;

    /**
     * 分页数据（带模糊搜索）
     * @param request
     * @return
     */
    @GetMapping(value = "/pageList")
    public PageInfo<T> pageList(HttpServletRequest request,T entity){
        Map<String,Object> map = getDatatableParams(request);
        /*
        设置数据状态为正常0
         */
        map.put("status",BaseEntity.DATA_STATUS_NORMAL);
        /*
        高级搜索
         */
        map.put("searchPlusParams",entity);
        logger.info("searchPlusParams----->"+entity.toString());
        return service.pageList(map);
    }

    /**
     * 服务端参数有效性验证
     * @param object 验证的实体对象
     * @param groups 验证组
     * @return 验证成功：返回null； 失败：BaseResult
     */
    protected BaseResult beanValidator(Object object, Class<?>... groups) {
        try{
            BeanValidators.validateWithException(validator, object, groups);
        }catch(ConstraintViolationException ex){
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            list.add(0, "数据验证失败：");
            for (String msg:
                 list) {
                logger.info(msg);
            }
            // 封装错误消息为字符串
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                String exMsg = list.get(i);
                if (i != 0) {
                    sb.append(String.format("%s. %s", i, exMsg)).append(list.size() > 1 ? "<br/>" : "");
                } else {
                    sb.append(exMsg).append(list.size() > 1 ? "<br/>" : "");
                }
            }
            return BaseResult.fail(sb.toString());
        }
        return null;
    }

    /**
     * 封装DataTables数据
     * @param request
     * @return
     */
    protected Map<String,Object> getDatatableParams(HttpServletRequest request){
        Map<String,Object> dataTableParams = new HashMap<>();
        dataTableParams.put("draw",request.getParameter("draw"));
        dataTableParams.put("start",request.getParameter("start"));
        dataTableParams.put("length",request.getParameter("length"));
        dataTableParams.put("search",request.getParameter("search[value]"));
        return dataTableParams;
    }
}
