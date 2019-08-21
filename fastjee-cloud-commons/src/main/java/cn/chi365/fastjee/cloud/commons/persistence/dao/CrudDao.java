package cn.chi365.fastjee.cloud.commons.persistence.dao;

import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * CrudDao TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/18 14:08
 */
public interface CrudDao<T extends BaseEntity> extends BaseDao {
    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T get(String id);

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity);

    /**
     * 查询数据列表
     * @param entity
     * @return
     */
    public List<T> findList(T entity);

    /**
     * 查询所有数据列表
     * @return
     */
    public List<T> findAllList();

    /**
     * 获得数据总条数(用于分页)
     * @return
     */
    public int getCount();

    /**
     * 查询分页数据列表
     * @param datatableParams
     * @return
     */
    public List<T> pageList(Map<String,Object> datatableParams);

    /**
     * 插入数据
     * @param entity
     * @return
     */
    public int insert(T entity);

    /**
     * 删除数据（该方法为物理删除，逻辑删除使用update更新data_status字段）
     * @param entity
     * @return
     */
    public int delete(T entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    public int update(T entity);

    /**
     * 批量删除 逻辑删除
     * @param ids
     * @return
     */
    public int deleteMulti(String[] ids);
}
