package cn.chi365.fastjee.cloud.web.admin.commons.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IpUtil TODO
 * 本机Ip
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/5/8 14:38
 */
public class IpUtil {
    public static void main(String[] args) {
        System.out.println("本机的ip=" + IpUtil.getIp());
    }

    public static String getPorjectPath(){
        String nowpath = "";
        nowpath=System.getProperty("user.dir")+"/";

        return nowpath;
    }

    /**
     * 获取本机访问地址
     * @return
     */
    public static String getIp(){
        String ip = "";
        try {
            InetAddress inet = InetAddress.getLocalHost();
            ip = inet.getHostAddress();
            //System.out.println("本机的ip=" + ip);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ip;
    }
}
