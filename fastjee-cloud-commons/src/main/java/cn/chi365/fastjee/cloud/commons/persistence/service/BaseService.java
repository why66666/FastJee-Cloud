package cn.chi365.fastjee.cloud.commons.persistence.service;

import cn.chi365.fastjee.cloud.commons.dto.PageInfo;
import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * BaseService TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/18 15:09
 */
public interface BaseService<T extends BaseEntity> {
    public T get(String id);
    public T get(T entity);
    public List<T> findList(T entity);
    /**
     * map参数(draw,start,length,search,status)
     * @param datatableParams
     * @return
     */
    public PageInfo<T> pageList(Map<String,Object> datatableParams);
    public int save(T entity);
    public int delete(T entity);
    public int deleteMulti(String[] ids);
}
