<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="cn.chi365.fastjee.cloud.web.admin.dao.sev.${cCamelCase}Dao">

    <!--表名 -->
    <sql id="tableName">
        ${tableName} a
    </sql>

    <!-- 字段 -->
    <sql id="Field">
        a.key_id,
    <#list columnsList as column>
        a.${column.columnName},
    </#list>
        a.create_by as "createBy.keyId",
        a.create_date,
        a.update_by as "updateBy.keyId",
        a.update_date,
        a.remarks,
        a.data_status
    </sql>

    <!-- 字段值 -->
    <sql id="FieldValue">
        ${r"#{"}keyId${r"}"},
    <#list columnsList as column>
        ${r"#{"}${column.coNaCamelCase}${r"}"},
    </#list>
        ${r"#{"}createBy.keyId${r"}"},
        ${r"#{"}createDate${r"}"},
        ${r"#{"}updateBy.keyId${r"}"},
        ${r"#{"}updateDate${r"}"},
        ${r"#{"}remarks${r"}"},
        ${r"#{"}dataStatus${r"}"}
    </sql>

    <!-- 自检条件 -->
    <sql id="WhereTerm">
<#list columnsList as column>
    <#if column.javaType == 'Date'>
        <if test="${column.coNaCamelCase} != null">
    </#if>
    <#if column.javaType != 'Date'>
        <if test="${column.coNaCamelCase} != null and ${column.coNaCamelCase} != ''">
    </#if>
            AND a.${column.columnName} = ${r"#{"}${column.coNaCamelCase}${r"}"}
        </if>
</#list>
        <if test="createBy != null">
            <if test="createBy.keyId != null and createBy.keyId != ''">
                AND a.create_by = ${r"#{"}createBy.keyId${r"}"}
            </if>
        </if>
        <if test="createDate != null">
            AND a.create_date = ${r"#{"}createDate${r"}"}
        </if>
        <if test="updateBy != null">
            <if test="updateBy.keyId != null and updateBy.keyId != ''">
                AND a.update_by = ${r"#{"}updateBy.keyId${r"}"}
            </if>
        </if>
        <if test="updateDate != null">
            AND a.update_date = ${r"#{"}updateDate${r"}"}
        </if>
        <if test="remarks != null and remarks != ''">
            AND a.remarks = ${r"#{"}remarks${r"}"}
        </if>
        <if test="dataStatus != null and dataStatus != ''">
            AND a.data_status = ${r"#{"}dataStatus${r"}"}
        </if>
    </sql>

    <!-- 自检set属性 -->
    <sql id="SetTerm">
<#list columnsList as column>
    <#if column.javaType == 'Date'>
        <if test="${column.coNaCamelCase} != null">
    </#if>
    <#if column.javaType != 'Date'>
        <if test="${column.coNaCamelCase} != null and ${column.coNaCamelCase} != ''">
    </#if>
        a.${column.columnName} = ${r"#{"}${column.coNaCamelCase}${r"}"},
        </if>
</#list>
        <if test="createBy != null">
            <if test="createBy.keyId != null and createBy.keyId != ''">
                a.create_by = ${r"#{"}createBy.keyId${r"}"},
            </if>
        </if>
        <if test="createDate != null">
            a.create_date = ${r"#{"}createDate${r"}"},
        </if>
        <if test="updateBy != null">
            <if test="updateBy.keyId != null and updateBy.keyId != ''">
                a.update_by = ${r"#{"}updateBy.keyId${r"}"},
            </if>
        </if>
        <if test="updateDate != null">
            a.update_date = ${r"#{"}updateDate${r"}"},
        </if>
        <if test="remarks != null and remarks != ''">
            a.remarks = ${r"#{"}remarks${r"}"},
        </if>
        <if test="dataStatus != null and dataStatus != ''">
            a.data_status = ${r"#{"}dataStatus${r"}"}
        </if>
    </sql>

    <!-- 模糊查询条件 -->
    <sql id="Fuzzy">
        <if test="search != null and search != ''">
            (
    <#list columnsList as column>
        <#if column.fuzzySearch?? && column.fuzzySearch == 'on'>
            <#if column.dataType == 'varchar'>a.${column.columnName}<#else>cast(a.${column.columnName} as char )</#if> LIKE concat(concat('%',${r"#{"}search${r"}"}),'%')
        </#if><#sep>or
    </#list>
            )
        </if>
        and
        a.data_status = ${r"#{"}status${r"}"}
    </sql>


    <!-- 高级搜索条件 -->
    <sql id="SearchPlus">
        <if test="searchPlusParams != null">
    <#list columnsList as column>
        <#if column.searchPlus?? && column.searchPlus == 'on'>
            <if test="searchPlusParams.${column.coNaCamelCase} != null and searchPlusParams.${column.coNaCamelCase} != ''">
                AND a.${column.columnName} = ${r"#{"}searchPlusParams.${column.coNaCamelCase}${r"}"}
            </if>
        </#if>
    </#list>
            <if test="searchPlusParams.bwCreateDate != null and searchPlusParams.bwCreateDate != ''">
                AND (
                a.create_date between
                <foreach collection="searchPlusParams.bwCreateDate.split(' - ')" item="item" index="index" separator="and">
                    ${r"#{"}item${r"}"}
                </foreach>
                )
            </if>
            <if test="searchPlusParams.bwUpdateDate != null and searchPlusParams.bwUpdateDate != ''">
                AND (
                a.update_date between
                <foreach collection="searchPlusParams.bwUpdateDate.split(' - ')" item="item" index="index" separator="and">
                    ${r"#{"}item${r"}"}
                </foreach>
                )
            </if>
        </if>
    </sql>


    <!-- 根据keyId查询 -->
    <select id="get" resultType="${cCamelCase}" parameterType="String">
        SELECT
        <include refid="Field"/>
        FROM <include refid="tableName"></include>
        WHERE a.key_id = ${r"#{"}keyId${r"}"}
    </select>

    <!-- 查询数据列表 -->
    <select id="findList" resultType="${cCamelCase}" parameterType="${cCamelCase}">
        SELECT
        <include refid="Field"/>
        FROM <include refid="tableName"></include>
        <where>
            <include refid="WhereTerm"></include>
        </where>
    </select>


    <!-- 查询所有数据列表 -->
    <select id="findAllList" resultType="${cCamelCase}">
        SELECT
        <include refid="Field"/>
        FROM <include refid="tableName"></include>
    </select>

    <!-- 获得数据总条数(用于分页) -->
    <select id="getCount" resultType="int">
        SELECT
        count(a.key_id)
        FROM <include refid="tableName"></include>
        where a.data_status = '0'
    </select>

    <!-- 查询分页数据列表 -->
    <select id="pageList" resultType="${cCamelCase}" parameterType="HashMap">
        SELECT
        <include refid="Field"/>
        FROM <include refid="tableName"></include>
        <where>
            <include refid="Fuzzy"></include>
            <include refid="SearchPlus"></include>
        </where>
        order by a.update_date desc
        limit ${r"${"}start${r"}"},${r"${"}length${r"}"}
    </select>

    <!-- 插入数据 -->
    <insert id="insert" parameterType="${cCamelCase}">
        insert into
        ${tableName}
        (
        key_id,
    <#list columnsList as column>
        ${column.columnName},
    </#list>
        create_by,
        create_date,
        update_by,
        update_date,
        remarks,
        data_status
        ) values (
        <include refid="FieldValue"></include>
        )
    </insert>

    <!-- 删除数据（该方法为物理删除，逻辑删除使用update更新data_status字段） -->
    <delete id="delete" parameterType="${cCamelCase}">
        delete from
        ${tableName}
        where
        key_id = ${r"#{"}keyId${r"}"}
    </delete>

    <!-- 更新数据 -->
    <update id="update" parameterType="${cCamelCase}">
        update
        <include refid="tableName"></include>
        <set>
            <include refid="SetTerm"></include>
        </set>
        where
        a.key_id = ${r"#{"}keyId${r"}"}
    </update>

    <!-- 批量删除，逻辑删除 -->
    <update id="deleteMulti">
        update
        <include refid="tableName"></include>
        set a.data_status = '1'
        where a.key_id in
        <foreach collection="array" open="(" close=")" separator="," item="id">
            ${r"#{"}id${r"}"}
        </foreach>
    </update>

</mapper>