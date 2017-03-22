package com.coolfish.gmserver.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.coolfish.gmserver.common.Constant;
import com.coolfish.gmserver.common.ExcelReader;
import com.coolfish.gmserver.mvc.model.ExcelObj;
import com.coolfish.gmserver.mvc.service.MailService;

/**
 * Created by Forrest on 2017/3/16.
 */
@RestController
public class MailController {

	@Autowired
	private MailService mailServcie;
	
	@Value("${excel.path}")
	private String excelPath;
	
    @RequestMapping("/mail")
    @ResponseBody
    public List<ExcelObj> mail() {
        try {
        	
            ExcelReader.RowConverter<ExcelObj> converter = (row)->new ExcelObj(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10]);
            ExcelReader<ExcelObj>reader = ExcelReader.builder(ExcelObj.class).converter(converter).withHeader().build();
            List<ExcelObj> objs = reader.read("d:/linlin.xlsx");
            System.out.println(objs.size());
            Constant.objs = objs;
            for(ExcelObj obj : objs) {
            	if(!Constant.addAndCheck(obj.getPhone())){
            		System.out.println("alread send mail to "+ obj.getName() + "will not send to him again!");
            		continue;
            	}
            	long now = System.currentTimeMillis();
            	System.out.println("sending to " + obj.getName() + "...");
            	mailServcie.sendAttachmentsMail(obj);
            	System.out.println("sending to " + obj.getName() + " done!!! 用时:"+(System.currentTimeMillis() - now));
            }
            return objs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
