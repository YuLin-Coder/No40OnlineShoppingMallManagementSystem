package cn.huicheng.shop.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.huicheng.shop.entities.User;
import cn.huicheng.shop.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * 跳转到登录页面
	 * @author ghy
	 */
	@RequestMapping("/user_loginPage")
	public String loginPage() {
		return "login";
	}

	/**
	 * 登录功能实现
	 * @param username
	 * @param password
	 * @param session
	 * @param request
	 * @author ghy
	 */
	@RequestMapping("/user_login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, 
			HttpSession session, HttpServletRequest request) {
		User existUser = userService.login(username, password);
		// 判断
		if (existUser == null) {
			// 登录失败
			request.setAttribute("loginFail","登录失败:用户名或密码错误或用户未激活!");
			return "login";
		} else {
			// 登录成功
			// 将用户的信息存入到session中
			session.setAttribute("existUser", existUser);
			// 页面跳转
			return "redirect:index";
		}
	}

	/**
	 * 跳转到注册页面
	 * @author ghy
	 */
	@RequestMapping("/user_registPage")
	public String registPage(){
		return "regist";
	}
	
	/**
	 * Ajax 校验用户名是否可用
	 * @author ghy
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/user_findByName")
	public String checkByName(@RequestParam("username") String username) {
		User existUser = userService.checkByUsername(username);
		if(existUser != null){
			//response.getWriter().println("<font color='red'>用户名已经存在</font>");
			return "yes";
		}
		return "no";
	}
	
	/**
	 * 注册功能
	 * @param request
	 * @param session
	 * @param user
	 * @param checkcode
	 * @author ghy
	 */
	@RequestMapping("/user_regist")
	public ModelAndView regist(HttpServletRequest request, HttpSession session,
			@Valid User user, BindingResult result, @RequestParam String checkcode){
		ModelAndView mav = new ModelAndView();
		System.out.println("user==>"+user);
		
		String sessionCheckCode = (String) session.getAttribute("checkcode");
//		System.out.println("="+checkcode+"=");
//		System.out.println("="+sessionCheckCode+"=");
//		System.out.println(checkcode.trim().equals(sessionCheckCode));
		if(checkcode == null || !checkcode.equals(sessionCheckCode)){
			request.setAttribute("checkCodeError", "验证码输入错误!");
			mav.setViewName("regist");
			return mav;
		}
		mav.setViewName("msg");
		System.out.println("checkcode == "+checkcode);
		mav.addObject("registMsg", "注册成功,请去邮箱激活！！");
		
		userService.save(user);
		
		return mav;
	}
	
	@InitBinder(value={"repassword","checkcode"})
	public void initBinder(WebDataBinder binder){
	     //System.out.println("initBinder...");
	     //不自动绑定对象中的 empName 属性,另行处理
	     binder.setDisallowedFields("repassword");
	     binder.setDisallowedFields("checkcode");
	}

	/**
	 * 激活帐户
	 * 
	 */
	@RequestMapping("/user_active")
	public String userActive(@RequestParam String code,Map<String,Object> map){
		
		User existUser = userService.findByCode(code);
		// 判断
		if (existUser == null) {
			// 激活码错误的
			map.put("activeMsg", "激活失败:激活码错误!");
		} else {
			// 激活成功
			// 修改用户的状态
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			map.put("activeMsg", "激活成功:请去登录!");
		}
		return "msg";
		
	}
	
	/**
	 * 退出
	 * @param session
	 * @author ghy
	 */
	@RequestMapping("/user_exit")
	public String userExit(HttpSession session){
		session.invalidate();
		return "redirect:/index";
	}
	
	
	
}
