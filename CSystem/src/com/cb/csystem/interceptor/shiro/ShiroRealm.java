package com.cb.csystem.interceptor.shiro;


import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.cb.csystem.domain.UserDomain;
import com.cb.csystem.service.IUserService;
import com.cb.csystem.util.Consts;


/**
 * shiro身份验证
 * @author chen
 *
 */
public class ShiroRealm extends AuthorizingRealm {

	@Resource private IUserService userService;
	
    /** 
     * 为当前登录的Subject授予角色和权限 
     * @see 经测试:本例中该方法的调用时机为需授权资源被访问时 
     * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache 
     */
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){  
    	
    	String currentUsername=(String)super.getAvailablePrincipal(principals);
    	SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		try {
			UserDomain user = userService.doGetUserByUsername(currentUsername);
	    	if(null!=user){
	    		//添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
	    		simpleAuthorInfo.addRole(user.getRole().getValue());
	    		//添加权限  
	    		simpleAuthorInfo.addStringPermission(String.valueOf(user.getRole().getAuthority()));
	    		return simpleAuthorInfo;
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return null;
    }
   
    /** 
     * 验证当前登录的Subject 
     * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时 
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {  
    	
    	//获取基于用户名和密码的令牌
    	//实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
    	UsernamePasswordToken token=(UsernamePasswordToken)authcToken;
    	
    	//此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息
    	//这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证
    	boolean isOk=false;
    	try {
    		//判断用户名密码
			isOk=userService.doCheckUserPassword(token.getUsername(), token.getPassword());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isOk){
			AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), this.getName());
			this.setSession(Consts.CURRENT_USER,token.getUsername());
			return authenticationInfo;
		}
    	
    	return null;
    }  

       
    /** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * @see 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到 
     */
    private void setSession(Object key, Object value){
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            if(null != session){  
                session.setAttribute(key, value);  
            }
        }  
    }  
}
