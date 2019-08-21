package cn.chi365.fastjee.cloud.commons.persistence.service;

import cn.chi365.fastjee.cloud.commons.dto.PageInfo;
import cn.chi365.fastjee.cloud.commons.persistence.dao.CrudDao;
import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * CrudService TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/18 15:09
 */
@Transactional(readOnly = true)
public abstract class CrudService<T extends BaseEntity,D extends CrudDao<T>> implements BaseService<T>{

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询分页数据
     * @param datatableParams
     * @return
     */
    public PageInfo<T> pageList(Map<String,Object> datatableParams) {
        int count = dao.getCount();
        List<T> list = dao.pageList(datatableParams);
        PageInfo<T> pageInfo = new PageInfo<>(Integer.parseInt(datatableParams.get("draw").toString()),count,count,list,null);
        return pageInfo;
    }

    /**
     * 保存数据
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int save(T entity) {

        /*
        插入数据
         */
        if (entity.getKeyId() == null || entity.getKeyId().equals("")){
            entity.insertInit();
            return dao.insert(entity);
        }
        /*
        更新数据
         */
        else{
            entity.updateInit();
            return dao.update(entity);
        }
    }


    /**
     * 删除数据（物理删除，逻辑删除调用更新方法）
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public int delete(T entity){
        return dao.delete(entity);
    }


    /**
     * 批量删除，逻辑删除
     *
     * @param ids
     */
    @Transactional(readOnly = false)
    public int deleteMulti(String[] ids) {
        return dao.deleteMulti(ids);
    }
}
