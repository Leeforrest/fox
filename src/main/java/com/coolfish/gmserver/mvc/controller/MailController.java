package com.coolfish.gmserver.mvc.controller;

import com.coolfish.gmserver.common.ExcelReader;
import com.coolfish.gmserver.mvc.model.ExcelObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Forrest on 2017/3/16.
 */
@RestController
public class MailController {


    @RequestMapping("/mail")
    @ResponseBody
    public List<ExcelObj> readMail() {
        try {
            ExcelReader.RowConverter<ExcelObj> converter = (row)->new ExcelObj(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10]);
            ExcelReader<ExcelObj>reader = ExcelReader.builder(ExcelObj.class).converter(converter).withHeader().build();
            List<ExcelObj> objs = reader.read("linlin.xlsx");
            return objs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
