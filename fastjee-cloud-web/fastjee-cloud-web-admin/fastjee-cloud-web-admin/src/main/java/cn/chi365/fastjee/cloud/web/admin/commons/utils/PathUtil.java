package cn.chi365.fastjee.cloud.web.admin.commons.utils;


import java.io.File;

/**
 * PathUtil TODO
 * 路径工具
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/27 12:52
 */
public class PathUtil {


    public static String getClasspath() {
        return System.getProperty("user.dir").replaceAll("\\\\","/");
    }


    /**获取classpath2
     * @return
     */
    public static String getClassResources(){
        String path =  (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
        if(path.indexOf(":") != 1){
            path = File.separator + path;
        }
        return path;
    }

    public static void main(String[] args) {
        System.out.println(getClasspath());
    }
}
