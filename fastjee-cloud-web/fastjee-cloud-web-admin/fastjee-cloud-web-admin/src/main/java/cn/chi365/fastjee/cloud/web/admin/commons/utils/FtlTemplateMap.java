package cn.chi365.fastjee.cloud.web.admin.commons.utils;

import cn.chi365.fastjee.cloud.commons.domain.gen.SysGenColumn;
import cn.chi365.fastjee.cloud.commons.util.CamelCaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FtlTemplateMap TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/27 20:44
 */
public class FtlTemplateMap {
    private static Logger logger = LoggerFactory.getLogger(FtlTemplateMap.class);
    public static Map<String,Object> getFtlMap(String tableName, String titil, List<SysGenColumn> columnsList){
        logger.info("代码生成器：：正在获取解析属性");
        Map<String,Object> ftlMap = new HashMap<>();
        ftlMap.put("columnsList",columnsList);
        ftlMap.put("titil",titil);
        ftlMap.put("tableName",tableName);
        ftlMap.put("cCamelCase", CamelCaseUtils.toCapitalizeCamelCase(tableName));
        ftlMap.put("camelCase",CamelCaseUtils.toCamelCase(tableName));
        ftlMap.put("nowDate",new Date());
        ftlMap.put("permCase",tableName.replaceAll("_",":"));
        return ftlMap;
    }
}
