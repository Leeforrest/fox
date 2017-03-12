package com.coolfish.gmserver.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coolfish.gmserver.common.UserMananger;
import com.coolfish.gmserver.mvc.model.User;
import com.coolfish.gmserver.mvc.service.UserService;
/**
 * @author Forrest
 * @date 2017年2月20日
 */
@RestController
public class MainPageController {
 
	@Autowired
    private UserService userService;
    /**
     * 客户端收到json数据
     * @param name
     * @param model
     * @return
     */
    @RequestMapping("/user")
    @ResponseBody
    public List<User> mainPage(HttpSession session, Model model) {
    	int id = UserMananger.getInstance().getUserId(session);
    	User user = userService.selectById(id);
    	if(user == null) {
    		return null;
    	}
    	List<User>users = userService.select();
    	model.addAttribute("users", users);
    	return users;
    }
    
}