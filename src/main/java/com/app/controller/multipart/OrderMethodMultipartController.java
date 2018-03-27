package com.app.controller.multipart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.controller.view.UomExcelView;
import com.app.model.OrderMethod;
import com.app.service.IOrderMethodService;
import com.app.util.OrderMethodUtil;

public class OrderMethodMultipartController {
	
	@Autowired
	private IOrderMethodService service;
	@Autowired
	private OrderMethodUtil util;
	
	@GetMapping("/orderMethodMultipart")
	public String showMultipartOrderMethod(){
		return "orderMethodMultipart";
	}

	@GetMapping("/orderMethodExport")
	public ModelAndView exportOrderMethod(){
		List<OrderMethod> om=service.getAll();
		return new ModelAndView(new UomExcelView(), "omList", om);
	}
}
