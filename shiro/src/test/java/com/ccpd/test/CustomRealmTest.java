package com.ccpd.test;

import com.ccpd.shiro.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author forestsun
 * @date 2019/1/2
 */
public class CustomRealmTest {

    @Test
    public void testCustomRealm(){

        CustomRealm customRealm = new CustomRealm();

        //shiro加密
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //加密算法
        matcher.setHashAlgorithmName("md5");
        //加密次数
        matcher.setHashIterations(1);
        customRealm.setCredentialsMatcher(matcher);

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);


        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);
        System.out.println(subject.isAuthenticated());
      /*  subject.checkRoles("admin");
        subject.checkPermission("user:delete");*/

    }
}
