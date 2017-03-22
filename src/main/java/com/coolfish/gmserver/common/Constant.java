package com.coolfish.gmserver.common;

import java.util.ArrayList;
import java.util.List;

import com.coolfish.gmserver.mvc.model.ExcelObj;

public class Constant {
	public static List<ExcelObj> objs = new ArrayList<>();
	public static List<String> phoneNums = new ArrayList<>();
	
	public static boolean addAndCheck(String phone) {
		if(phoneNums.contains(phone)) {
			return false;
		}
		phoneNums.add(phone);
		return true;
	}
}
