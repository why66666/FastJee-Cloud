package cn.chi365.fastjee.cloud.web.admin.commons.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * Freemarker TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/27 12:52
 */
public class Freemarker {

	private static Logger logger = LoggerFactory.getLogger(Freemarker.class);
	/**
	 * 输出到输出到文件
	 * @param ftlName   ftl文件名
	 * @param root		传入的FtlTemplate
	 * @param outFile	输出后的文件全部路径
	 * @param filePath	输出前的文件上部路径
	 */
	public static void printFile(String ftlName, Map<String,Object> root, String outFile, String filePath, String ftlPath) throws Exception{
		try {
			File file = new File( PathUtil.getClasspath() + filePath + outFile);
			logger.info("代码生成器：：正在获取"+outFile+"文件输出路径");
			//判断有没有父路径，就是判断文件整个路径是否存在
			if(!file.getParentFile().exists()){
				//不存在就全部创建
				file.getParentFile().mkdirs();
				logger.info("代码生成器：：检测到空目录，正在创建"+outFile+"文件输出目录");
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			logger.info("代码生成器：：正在创建文件输入流");
			Template template = getTemplate(ftlName, ftlPath);
			logger.info("代码生成器：：正在获取模板"+ftlName);
			//模版输出
			template.process(root, out);
			logger.info("代码生成器：：正在执行模板输出，生成文件"+outFile+"中...");
			out.flush();
			out.close();
			logger.info("代码生成器：："+outFile+"已生成！");
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过文件名加载模版
	 * @param ftlName
	 */
	public static Template getTemplate(String ftlName, String ftlPath) throws Exception{
		try {
			Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);  												//通过Freemaker的Configuration读取相应的ftl
			cfg.setEncoding(Locale.CHINA, "utf-8");
			cfg.setDirectoryForTemplateLoading(new File(PathUtil.getClassResources()+"/ftl/"+ftlPath));		//设定去哪里读取相应的ftl模板文件
			Template temp = cfg.getTemplate(ftlName);												//在模板文件目录中找到名称为name的文件
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
