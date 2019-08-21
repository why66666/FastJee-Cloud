package cn.chi365.fastjee.cloud.commons.persistence.entity;


import cn.chi365.fastjee.cloud.commons.domain.sys.SysUser;
import cn.chi365.fastjee.cloud.commons.util.IDUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * BaseEntity TODO
 * 实体类基类
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/18 10:38
 */
@Getter
@ToString
@Setter
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    protected String keyId;
    /**
     * 创建者
     */
    protected SysUser createBy;
    /**
     * 创建时间
     */
    protected Date createDate;
    /**
     * 更新者
     */
    protected SysUser updateBy;
    /**
     * 更新时间
     */
    protected Date updateDate;
    /**
     * 备注信息
     */
    protected String remarks;
    /**
     * 数据状态（'0'：正常使用 '1'：逻辑删除）
     */
    protected String dataStatus;

    /**
     * 创建时间间隔 接收高级搜索
     */
    protected String bwCreateDate;
    /**
     * 更新时间间隔 接收高级搜索
     */
    protected String bwUpdateDate;
    /**
     * 三个备用属性 dto
     */
    protected String spareOne;
    protected String spareTwo;
    protected String spareThree;

    /**
     * 数据状态标记（0：正常；1：删除；2：待定；）
     */
    public static final String DATA_STATUS_NORMAL = "0";
    public static final String DATA_STATUS_DELETE = "1";
    public static final String DATA_STATUS_AUDIT = "2";

    public BaseEntity(){
        this.dataStatus = DATA_STATUS_NORMAL;
    }

    /**
     * 插入数据初始化
     */
    public void insertInit(){
        this.keyId = IDUtils.uuid();
        SysUser sysUser = new SysUser();
        sysUser.setKeyId("e4c3f19f960c470aa0da1c54ac6f0205");
        this.createBy = sysUser;
        this.createDate = new Date();
        this.updateDate = this.createDate;
        this.updateBy = this.createBy;
        this.dataStatus = DATA_STATUS_NORMAL;
    }

    /**
     * 更新数据初始化
     */
    public void updateInit(){
        SysUser sysUser = new SysUser();
        sysUser.setKeyId("e4c3f19f960c470aa0da1c54ac6f0205");
        this.updateBy = sysUser;
        this.updateDate = new Date();
    }

    /**
     * Cache 获取Id
     * @return
     */
    public String getId(){
        return this.keyId;
    }
}
