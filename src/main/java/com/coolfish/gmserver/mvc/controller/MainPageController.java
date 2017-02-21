package com.coolfish.gmserver.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coolfish.gmserver.common.UserMananger;
import com.coolfish.gmserver.config.Page;
import com.coolfish.gmserver.mvc.model.User;
import com.coolfish.gmserver.mvc.service.UserService;
/**
 * @author Forrest
 * @date 2017年2月20日
 */
@Controller
public class MainPageController {
 
	@Autowired
    private UserService userService;
    /**
     * 返回结果为视图文件路径。视图相关文件默认放置在路径 resource/templates下
     * @param name
     * @param model
     * @return
     */
    @RequestMapping("/user")
    public String mainPage(HttpSession session, Model model) {
    	int id = UserMananger.getInstance().getUserId(session);
    	User user = userService.selectById(id);
    	if(user == null) {
    		return null;
    	}
    	List<User>users = userService.select();
    	model.addAttribute("users", users);
    	return Page.User.getHtmlPath();
    }
    
}