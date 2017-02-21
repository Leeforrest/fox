package com.coolfish.gmserver.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coolfish.gmserver.common.UserMananger;
import com.coolfish.gmserver.config.Page;
import com.coolfish.gmserver.mvc.model.User;
import com.coolfish.gmserver.mvc.service.UserService;
 
/**
 * controller三种接口之Json
 * @author Forrest
 * @date 2017年2月14日
 */
@Controller
public class LoginController {
 
 
    @Autowired
    private UserService userService;
 
    /*
     *  http://localhost:8080/getUserInfo
     */
 
    @RequestMapping("/getUserInfo")
    @ResponseBody //拷贝一条Spring官方的解释： The @ResponseBody annotation tells Spring MVC not to render a model into a view, but rather to write the returned object into the response body
    public User getUserInfo() {
        return null;
    }
    
    
    @RequestMapping(value="/loginRequest")
    public String loginRequest(HttpSession session, @RequestParam("name") String name, @RequestParam("pwd")String pwd, Model model) {
    	User user = userService.findUser(name);
    	if(user == null) {
    		model.addAttribute("error", "user not exist");
    		return Page.Login.getHtmlPath();
    	}
    	if(user.getPassword() == null || !user.getPassword().equals(pwd)) {
    		model.addAttribute("error", "password is incorrect");
        	return Page.Login.getHtmlPath();
    	}
    	
    	//session记录
		session.setAttribute("id", user.getId()); 
		UserMananger.getInstance().onLogin(user.getId());
		
		List<User> users = userService.select();
    	if(users != null) {
    		model.addAttribute("users", users);
    	}
		return Page.Main_Page.getHtmlPath();
    }
    
    
}
