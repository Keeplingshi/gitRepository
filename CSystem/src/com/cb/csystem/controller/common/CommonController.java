package com.cb.csystem.controller.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cb.csystem.domain.UserDomain;
import com.cb.csystem.service.IUserService;
import com.cb.csystem.util.Consts;
import com.cb.system.util.EndecryptUtils;

/**
 * 公共页面控制层
 * @author chen
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController {

	@Resource private IUserService userService;
	
	/**
	 * 修改用户密码界面
	 * @param model
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPasswordView")
	public String domodifyPasswordView(Model model,HttpSession session)throws Exception{
		
		String username=(String) session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		model.addAttribute("userDomain", userDomain);
		
		return "/commonView/modifyPasswordView";
	}
	
	/**
	 * 修改密码
	 * @param session
	 * @param oldpassword
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifuPassword")
	@ResponseBody
	public String domodifuPassword(HttpSession session,String oldpassword,String password)throws Exception{
		
		String username=(String) session.getAttribute(Consts.CURRENT_USER);
		UserDomain userDomain=userService.doGetUserByUsername(username);
		if(userDomain!=null){
			if(EndecryptUtils.md5(oldpassword).equals(userDomain.getPassword())){
				userDomain.setPassword(EndecryptUtils.md5(password));
				if(userService.doSave(userDomain)){
					return Consts.SUCCESS;
				}
			}			
		}
		return Consts.ERROR;
	}
}
