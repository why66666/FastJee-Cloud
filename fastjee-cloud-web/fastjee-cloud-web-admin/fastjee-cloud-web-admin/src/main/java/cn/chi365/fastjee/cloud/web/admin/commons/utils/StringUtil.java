package cn.chi365.fastjee.cloud.web.admin.commons.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * StringUtil TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/25 16:49
 */
public class StringUtil extends StringUtils {



    /**
     * list<string>转String,逗号分隔
     * @param stringList
     * @return
     */
    public static String getString(List<String> stringList){
        StringBuffer stringBuffer = new StringBuffer();
        int size = stringList.size();
        for (int i=0;i<size;i++){
            stringBuffer.append(stringList.get(i));
            if(i!=size-1){
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 获得用户远程地址
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request){
        String remoteAddr = request.getHeader("X-Real-IP");
        if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("X-Forwarded-For");
        }else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("Proxy-Client-IP");
        }else if (isNotBlank(remoteAddr)) {
            remoteAddr = request.getHeader("WL-Proxy-Client-IP");
        }
        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
    }
}
