package com.junqiang.www.realm;

import com.junqiang.www.entity.User;
import com.junqiang.www.system.service.UserBiz;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 这里这个拦截器完成了用户名和密码的验证，验证成功后又给用赋角色和权限

public class UserRealm extends AuthorizingRealm {

    @Resource(name="userBizImpl")
    private UserBiz userBiz;

    private static Logger logger = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 登陆成功之后，进行角色和权限验证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username = (String)principals.getPrimaryPrincipal();
        System.out.println("角色和权限验证" + username);
        logger.debug("角色和权限验证" + username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(userBiz.findRoles(username));
        authorizationInfo.setStringPermissions(userBiz.findPermissions(username));
        return authorizationInfo;
    }

    /**
     * 验证登陆
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();
        //从数据库中获取用户信息
        User user = userBiz.findById(username);

//        System.out.println("验证登陆" + username);
//        logger.warn("验证登陆" + username);

//        if(user == null) {
//            throw new UnknownAccountException();//没找到帐号
//        }
//
//        if(Boolean.TRUE.equals(user.getLocked())) {
//            throw new LockedAccountException(); //帐号锁定
//        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUserId(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }


}
