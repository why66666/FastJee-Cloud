package cn.chi365.fastjee.cloud.web.admin.commons.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUtil TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/5/1 16:08
 */
public class FileUtil {
    /**
     * 获取路劲下的所有文件名
     * @param path
     * @return
     */
    public static List<String> getFileNames(String path) {
        List<String> fileNames = new ArrayList<>();
        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();

        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile()) {
                fileNames.add(array[i].getName());
            } else if (array[i].isDirectory()) {
                /*
                递归遍历文件夹
                 */
                fileNames.addAll(getFileNames(array[i].getPath()));
            }
        }
        return fileNames;
    }

    /**
     * 读取Resources某文件里的全部内容
     * @param fileP  文件路径
     */
    public static String readFileAllContent(String fileP) {
        StringBuffer fileContent = new StringBuffer();
        try {
            String encoding = "utf-8";
            File file = new File(PathUtil.getClasspath() + fileP);//文件路径
            if (file.isFile() && file.exists()) { 		// 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);	// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    fileContent.append(lineTxt);
                    fileContent.append("\n");
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件,查看此路径是否正确:"+fileP);
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
        }
        return fileContent.toString();
    }

    /**
     * 往文件里的内容（Resource下）
     * @param fileP  文件路径
     * @param content  写入的内容
     */
    public static void writeFileCR(String fileP,String content){
        String filePath = PathUtil.getClasspath() + fileP;
        System.out.println(filePath);
        try {
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");
            BufferedWriter writer=new BufferedWriter(write);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
