package cn.chi365.fastjee.cloud.web.admin.controller;

import cn.chi365.fastjee.cloud.commons.domain.gen.SysGenColumn;
import cn.chi365.fastjee.cloud.commons.domain.gen.SysGenTable;
import cn.chi365.fastjee.cloud.commons.dto.BaseResult;
import cn.chi365.fastjee.cloud.commons.dto.PageInfo;
import cn.chi365.fastjee.cloud.commons.persistence.entity.BaseEntity;
import cn.chi365.fastjee.cloud.web.admin.commons.utils.FileUtil;
import cn.chi365.fastjee.cloud.web.admin.commons.utils.Freemarker;
import cn.chi365.fastjee.cloud.web.admin.commons.utils.FtlTemplateMap;
import cn.chi365.fastjee.cloud.web.admin.commons.utils.PathUtil;
import cn.chi365.fastjee.cloud.web.admin.service.gen.SysGenColumnService;
import cn.chi365.fastjee.cloud.web.admin.service.gen.SysGenTableService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * SysGenController TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/26 16:48
 */
@RestController
@RequestMapping(value = "/sysGen")
public class SysGenController extends CrudController<SysGenColumn, SysGenColumnService> {

    private static final String ENTITYPATH = "/fastjee-cloud-commons/src/main/java/cn/chi365/fastjee/cloud/commons/";

    private static final String FILEPATH = "/fastjee-cloud-web/fastjee-cloud-web-admin/fastjee-cloud-web-admin/src/main/java/cn/chi365/fastjee/cloud/web/admin/";

    private static final String FTLCLASSPATH = "createCode";

    private static final String FTLPATH = "/fastjee-cloud-web/fastjee-cloud-web-admin/fastjee-cloud-web-admin/src/main/resources/ftl/createCode/";

    private static final String FTLBAKPATH = "/fastjee-cloud-web/fastjee-cloud-web-admin/fastjee-cloud-web-admin/src/main/resources/ftl/createCodeBak/";

    private static final String MAPPERPATH = "/fastjee-cloud-web/fastjee-cloud-web-admin/fastjee-cloud-web-admin/src/main/resources/mapper/";

    private static final String HTMLPATH = "/fastjee-cloud-web/fastjee-cloud-web-admin/fastjee-cloud-web-admin/src/main/resources/templates/sev/";

    private static final String FORWARDGENTOKEN = "0";

    private static final String REVERSEGENTOKEN = "1";

    @Autowired
    SysGenTableService sysGenTableService;


    /**
     * 获取字段列表
     *
     * @param request
     * @param tableName
     * @return
     */
    @GetMapping(value = "/getColumns/{token}/{tableName}")
    public PageInfo<SysGenColumn> pageListByTable(HttpServletRequest request, @PathVariable(value = "tableName") String tableName,@PathVariable(value = "token") String token) {
        if(token.equals(REVERSEGENTOKEN) && service.getCountByTableName(tableName)==0){
            sysGenTableService.anaTableColumn(tableName);
        }

        Map<String, Object> map = getDatatableParams(request);
        /*
        设置数据状态为正常0
         */
        map.put("status", BaseEntity.DATA_STATUS_NORMAL);
        /*
        高级搜索
         */
        SysGenColumn sysGenColumn = new SysGenColumn();
        if(token.equals(FORWARDGENTOKEN)){
            sysGenColumn.setTableName("sev_" + tableName);
        }else{
            sysGenColumn.setTableName(tableName);
        }
        map.put("searchPlusParams", sysGenColumn);
        return service.pageList(map);
    }

    /**
     * 保存字段信息
     *
     * @param sysGenColumn
     * @return
     */
    @PostMapping(value = "/saveColumn")
    public BaseResult saveColumn(SysGenColumn sysGenColumn) {
        sysGenColumn.saveColumnInit();
        sysGenColumn.setTableName("sev_" + sysGenColumn.getTableName());
        System.out.println(sysGenColumn.toString());
        BaseResult vld = beanValidator((sysGenColumn));
        if (vld == null) {
            int rs = service.save(sysGenColumn);
            return rs > 0 ? BaseResult.success("数据提交成功！") : BaseResult.fail("数据提交失败!");
        }
        return vld;
    }


    /**
     * 启动代码生成器
     *
     * @return
     */
    @GetMapping(value = "/codeGen/{token}")
    public BaseResult codeGen(String tableName, String titil,@PathVariable(value = "token") String token) {
        if(token.equals(FORWARDGENTOKEN)){
            tableName = "sev_" + tableName;
        }

        List<SysGenColumn> sysGenColumns = service.findList(new SysGenColumn(tableName));
        Map<String, Object> root = FtlTemplateMap.getFtlMap(tableName, titil, sysGenColumns);

        if(token.equals(FORWARDGENTOKEN)){
            service.createTable(root);
        }


        try {
            /**
             * 生成实体类代码
             */
            Freemarker.printFile("entityTemplate.ftl", root, "domain/sev/" + root.get("cCamelCase") + ".java", ENTITYPATH, FTLCLASSPATH);
            /**
             * 生成Controller代码
             */
            Freemarker.printFile("controllerTemplate.ftl", root, "controller/sev/" + root.get("cCamelCase") + "Controller.java", FILEPATH, FTLCLASSPATH);
            /**
             * 生成Dao层代码
             */
            Freemarker.printFile("daoTemplate.ftl", root, "dao/sev/" + root.get("cCamelCase") + "Dao.java", FILEPATH, FTLCLASSPATH);
            /**
             * 生成Mapper文件代码
             */
            Freemarker.printFile("mapperTemplate.ftl", root, root.get("cCamelCase") + "Mapper.xml", MAPPERPATH, FTLCLASSPATH);
            /**
             * 生成Service代码
             */
            Freemarker.printFile("serviceTemplate.ftl", root, "service/sev/" + root.get("cCamelCase") + "Service.java", FILEPATH, FTLCLASSPATH);
            /**
             * 生成View代码
             */
            Freemarker.printFile("viewTemplate.ftl", root, "controller/sev/view/" + root.get("cCamelCase") + "View.java", FILEPATH, FTLCLASSPATH);
            /**
             * 生成ServiceImpl代码
             */
            Freemarker.printFile("serviceImplTemplate.ftl", root, "service/sev/impl/" + root.get("cCamelCase") + "ServiceImpl.java", FILEPATH, FTLCLASSPATH);
            /**
             * 生成前端list代码
             */
            Freemarker.printFile("listTemplate.ftl", root, root.get("camelCase") + "List.html", HTMLPATH, FTLCLASSPATH);
            /**
             * 生成前端form代码
             */
            Freemarker.printFile("formTemplate.ftl", root, root.get("camelCase") + "Form.html", HTMLPATH, FTLCLASSPATH);
        } catch (Exception e) {
            return BaseResult.success("出现异常，请检查格式！");
        }
        return BaseResult.success("生成成功！！");
    }

    /**
     * 获取日志输出
     * @return
     */
    @GetMapping(value = "/sysOut")
    public String sysOut(){
        File file = new File("G:/BiYeSheJi/SY/FastJee-Cloud/FastJee-Cloud/log/fastjee-cloud-web-admin.log");
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            br.skip(1454052);
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append("\n"+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    /**
     * 删除字段
     * @param ids
     * @return
     */
    @PostMapping(value = "/delete")
    public BaseResult delete(String ids){
        logger.info(ids);
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            int rs = service.deleteMulti(idArray);
            return rs > 0 ? BaseResult.success("数据删除成功！！！！") : BaseResult.fail("数据删除失败!");
        } else {
            return BaseResult.fail("删除内容失败！！！");
        }
    }

    /**
     * 查询sevtable
     * @return
     */
    @GetMapping(value = "/queryTable")
    public List<SysGenTable> querySevTables(){
        return sysGenTableService.selectSevTable();
    }

    /**
     * 查询所有模板名
     * @return
     */
    @GetMapping(value = "/queryFtl")
    public List<String> queryFtl(){
        return FileUtil.getFileNames(PathUtil.getClasspath()+FTLPATH);
    }

    /**
     * 读取ftl文件
     * @return
     */
    @GetMapping(value = "/readFtl")
    public String readFtl(String fileName){
        return FileUtil.readFileAllContent(FTLPATH+fileName);
    }


    /**
     * 还原模板
     * @param fileName
     * @return
     */
    @PostMapping(value = "/restoreFtl")
    public BaseResult restoreFtl(String fileName){
        try {
            String code = FileUtil.readFileAllContent(FTLBAKPATH+fileName);
            FileUtil.writeFileCR(FTLPATH+fileName,code);
        }catch (Exception e) {
            return BaseResult.fail("还原文件错误！");
        }
        return BaseResult.success("还原成功！");
    }

    /**
     * 模板写入
     * @param fileName
     * @param code
     * @return
     */
    @PostMapping(value = "/writeFtl")
    public BaseResult writeFtl(String fileName,String code){
        try {
            FileUtil.writeFileCR(FTLPATH+fileName,code);
        } catch (Exception e) {
            return BaseResult.fail("文件写入错误！");
        }
        return BaseResult.success("写入成功！");
    }

}
