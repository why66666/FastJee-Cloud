package cn.chi365.fastjee.cloud.web.admin.commons.shiro;

import cn.chi365.fastjee.cloud.web.admin.commons.utils.MD5Util;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * shiro自定密码校验方法 TODO
 *
 * @version ：1.0.0
 * @date ：Created in 2019/4/25 20:04
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
        public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;

        //获得用户输入的密码:(采用MD5算法加密加盐)
        String inPassword = new String(utoken.getPassword());
        String inUsername = utoken.getUsername();
        String haPassword = MD5Util.GetMD5Object(inUsername,inPassword).toString();
        //获得数据库中的密码
        String dbPassword=(String) info.getCredentials();
        //进行密码的比对
        return equals(haPassword,dbPassword);
    }
}
