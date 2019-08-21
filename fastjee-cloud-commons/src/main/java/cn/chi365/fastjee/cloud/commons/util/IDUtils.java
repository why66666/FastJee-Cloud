package cn.chi365.fastjee.cloud.commons.util;

import java.util.UUID;

/**
 * IDUtils TODO
 *
 * @author ：Yusir
 * @version ：1.0.0
 * @date ：Created in 2019/4/18 15:43
 */
public class IDUtils {
    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
