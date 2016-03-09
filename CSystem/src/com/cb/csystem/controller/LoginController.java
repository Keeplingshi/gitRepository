package com.cb.csystem.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cb.csystem.domain.UserDomain;
import com.cb.csystem.service.IUserService;
import com.cb.csystem.util.Consts;
/**
 * 登录控制层
 * @author chen
 *
 */
@Controller
public class LoginController {

	@Resource private IUserService userService;
	
	/**
	 * 登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String getLogin(Model model){
		
		return "/login";
	}
	
    /** 
     * 主页面
     * @throws Exception 
     */
    @RequestMapping("/main")
    public String login(HttpServletRequest request,Model model) throws Exception{  
        String username = request.getParameter("username");  
        String password = request.getParameter("password");
        
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);  
        token.setRememberMe(true);
        //System.out.println("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));  
        //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到ShiroRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            currentUser.login(token);
        	UserDomain userDomain=userService.doGetUserByUsername(username);
        	model.addAttribute("userDomain", userDomain);
        	if(userDomain.getRole()!=null){
        		int authority=userDomain.getRole().getAuthority();
        		
            	if(authority==Consts.AUTHORITY_ADMIN){
            		//管理员界面
            		return "/adminView/main";
            	}else if(authority==Consts.AUTHORITY_INSTRUCTOR){
            		//辅导员界面
            		return "/instructorView/main";
            	}else if(authority==Consts.AUTHORITY_TEACHER){
            		//老师界面
            		return "/teacherView/main";
            	}else if(authority==Consts.AUTHORITY_MONITOR){
            		//班长界面
            		return "/monitorView/main";
            	}else if(authority==Consts.AUTHORITY_STUDENT){
            		//学生界面
            		return "/studentView/main";
            	}else if(authority==Consts.AUTHORITY_DISCIPLINEADMIN){
            		//违纪管理员
            		return "/disciplineAdminView/main";
            	}
        	}
           // 验证通过
        }catch(UnknownAccountException uae){  
            //验证未通过,未知账户
        }catch(IncorrectCredentialsException ice){  
            //验证未通过,错误的凭证
        }catch(LockedAccountException lae){  
            //验证未通过,账户已锁定
        }catch(ExcessiveAttemptsException eae){  
            //验证未通过,错误次数过多
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            //ae.printStackTrace();  
        }
        model.addAttribute("error", Consts.ERROR);
        return "/login";
    }  
    
    /**
     * 登出
     * @param model
     * @return
     */
	@RequestMapping("/logout")
	public String getLogout(){
		
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		
		return "/login";
	}
}
