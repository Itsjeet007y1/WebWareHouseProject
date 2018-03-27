package com.app.util;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class OrderMethodUtil {
	
	public List<String> getOrderModes() {
		List<String> list=new LinkedList<String>();
		list.add("Sale");
		list.add("Purchase");
		return list;
	}
	
	public List<String> getOrderMethods() {
		List<String> list=new LinkedList<String>();
		list.add("FIFO");
		list.add("LIFO");
		list.add("FCFO");
		list.add("FEFO");
		return list;
	}
	
	public List<String> getOrderAccepts() {
		List<String> list=new LinkedList<String>();
		list.add("Multi-Model");
		list.add("Accept Return");
		return list;
	}
	
	public void getDynamics(ModelMap map) {
		map.addAttribute("orderModes",getOrderModes());
		map.addAttribute("orderMethods",getOrderMethods());
		map.addAttribute("orderAccepts",getOrderAccepts());
	}
}
