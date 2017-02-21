package com.coolfish.gmserver.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coolfish.gmserver.config.Page;
/**
 * controller三种接口之视图模板 
 * @author Forrest
 * @date 2017年2月14日
 */
@Controller
public class HomeController {
 
    /*
    *   http://localhost:8080
     */
    /**
     * 返回结果为视图文件路径。视图相关文件默认放置在路径 resource/templates下
     * @param name
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String loginMethod(Model model) {
    	model.addAttribute("error", "");
        return Page.Login.getHtmlPath();//html名字
    }
    
}