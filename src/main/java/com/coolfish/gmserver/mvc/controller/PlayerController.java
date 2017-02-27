package com.coolfish.gmserver.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coolfish.gmserver.config.Page;

/**
 * 
 * @author Forrest
 * @date 2017年2月22日
 */
@Controller
public class PlayerController {
	
	@RequestMapping("/player")
    public String player() {
    	return Page.Player.getHtmlPath();
    }
 
    /**
     * 把玩家踢下线
     * @param uid
     * @param model
     * @return
     */
    @RequestMapping("/kick")
    public String kickPlayer(@RequestParam(value="playerUid", defaultValue="0") String uid, Model model) {
//    	MessageNano msg = RPCUtil.getInstance().requestGS(kickRequest);
//    	if(msg == null) {
//    		model.addAttribute("error", ErrorCode.RequestTimeOut.getValue());
//    		return Page.Player.getHtmlPath();
//    	}
    	
    	return Page.Player.getHtmlPath();
    } 
    
   
 }