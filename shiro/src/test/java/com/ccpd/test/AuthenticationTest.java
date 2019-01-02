package com.ccpd.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;


/**
 * @author forestsun
 * @date 2019/1/2
 */
public class AuthenticationTest {

     SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

     @Before
     public void addUser(){
         simpleAccountRealm.addAccount("Mark","123456","admin","user");
     }

     //shiro认证
    @Test
    public void testAuthentication(){

        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2、主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        //认证校验：校验主体是否通过以下认证
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("Mark","123456");
        subject.login(usernamePasswordToken);
        boolean authenticated = subject.isAuthenticated();
        System.out.print(authenticated);
       /* subject.logout();
        boolean authenticated2 = subject.isAuthenticated();
        System.out.print(authenticated2);*/

       //shiro授权检验：校验主体是否具有以下授权
       subject.checkRoles("admin","user");
    }

}
