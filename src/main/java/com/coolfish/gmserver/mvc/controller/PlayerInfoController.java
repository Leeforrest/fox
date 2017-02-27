package com.coolfish.gmserver.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coolfish.gmserver.config.Page;
import com.coolfish.gmserver.mvc.model.PlayerInfo;

/**
 * 
 * @author Forrest
 * @date 2017年2月22日
 */
@Controller
public class PlayerInfoController {
	
 
    /**
     * 搜索玩家
     * @param name
     * @param model
     * @return
     */
    @RequestMapping("/searchPlayer")
    public String searchPlayer(@RequestBody PlayerInfo info,  Model model) {
//    	MessageNano msg = RPCUtil.getInstance().requestGS(searchRequest);
//    	if(msg == null) {
//    		model.addAttribute("error", ErrorCode.RequestTimeOut.getValue());
//    		return Page.Player.getHtmlPath();
//    	}
    	
    	return Page.PlayerBaseInfo.getHtmlPath();
    }
    
 }