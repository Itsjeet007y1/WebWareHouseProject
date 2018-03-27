package com.app.util;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class WhUserTypeUtil {
	public List<String> getWhUserIdTypes() {
		List<String> list=new LinkedList<String>();
		list.add("PAN CARD");
		list.add("PASSPORT");
		list.add("AADHAR");
		list.add("VOTER ID");
		list.add("OTHER");
		return list;
	}
	
	public List<String> getWhUserId() {
		List<String> list=new LinkedList<String>();
		list.add("VENDOR");
		list.add("CUSTOMER");
		return list;
	}
	
	public void getDynamics(ModelMap map) {
		map.addAttribute("whUserTypes",getWhUserIdTypes());
		map.addAttribute("whUserId",getWhUserId());
	}
}
