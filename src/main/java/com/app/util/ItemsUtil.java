package com.app.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.app.service.IOrderMethodService;
import com.app.service.IUomService;
import com.app.service.IWhUserTypeService;

@Component
public class ItemsUtil {
	@Autowired
	private IUomService service;
	@Autowired
	private IOrderMethodService orderService;
	@Autowired
	private IWhUserTypeService whTypeService;
	
	public List<String> getCurrency() {
		List<String> list=new ArrayList<String>();
		list.add("INR");
		list.add("USD");
		list.add("EUR");
		list.add("YEN");
		list.add("UKD");
		return list;
	}
	public void getDynamics(ModelMap map) {
		map.addAttribute("currs",getCurrency());
		map.addAttribute("uomList",service.getAll());
		map.addAttribute("saleMode",orderService.findByOrderMode("Sale"));
		map.addAttribute("purchaseMode",orderService.findByOrderMode("Purchase"));
		map.addAttribute("vendors",whTypeService.findByWhUserTyp("VENDOR"));
		map.addAttribute("customers",whTypeService.findByWhUserTyp("CUSTOMER"));
	}
}
