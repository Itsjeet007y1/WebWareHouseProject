package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.OrderMethod;
import com.app.service.IOrderMethodService;
import com.app.spec.OrderMethodSpecification;
import com.app.util.OrderMethodUtil;
import com.app.validator.OrderMethodValidator;

@Controller
public class OrderMethodController {
	@Autowired
	private IOrderMethodService service;
	@Autowired
	private OrderMethodUtil util;
	@Autowired
	private OrderMethodValidator validator;
	
	@GetMapping(value= {"/regOrderMethod"})
	public String showPage(ModelMap map) {
		map.addAttribute("orderMethod",new OrderMethod());
		util.getDynamics(map);
		return "OrderMethodRegister";
	}
	
	@PostMapping("/insertOrderMethod")
	public String save(@ModelAttribute OrderMethod orderMethod,BindingResult errors, ModelMap map) {
		validator.validate(orderMethod, errors);
		if(errors.hasErrors()) {
			map.addAttribute("orderMethod",orderMethod);
		} else {
			long id=service.save(orderMethod);
			map.addAttribute("message","<br><h4 style='color:green'>Order Method '"+id+"' saved.. ;)<h4>");
			map.addAttribute("orderMethod",new OrderMethod());
		}
		util.getDynamics(map);
		return "OrderMethodRegister";
	}
	
	@GetMapping("/getAllOrderMethods")
	public String getAll(@PageableDefault(size=5,sort="orderMethodId",direction=Direction.ASC)Pageable pageable, @ModelAttribute OrderMethod orderMethod,ModelMap map) {
		Specification<OrderMethod> spec=new OrderMethodSpecification(orderMethod);
		Page<OrderMethod> orderMethodPage=service.getAll(spec,pageable);
		List<OrderMethod> list=orderMethodPage.getContent();
		map.addAttribute("orderMethodsList",list);
		map.addAttribute("orderMethodsPage",orderMethodPage);
		util.getDynamics(map);
		return "OrderMethodData";
	}
	
	@GetMapping("/editOrderMethod")
	public String edit(@RequestParam long orderMethodId, ModelMap map) {
		map.addAttribute("orderMethod",service.getOneById(orderMethodId));
		util.getDynamics(map);
		return "OrderMethodDataEdit";
	}
	
	@PostMapping("/updateOrderMethod")
	public String update(@ModelAttribute OrderMethod orderMethod,BindingResult errors,ModelMap map) {
		validator.validate(orderMethod, errors);
		String page=null;
		if(errors.hasErrors()) {
			map.addAttribute("orderMethod",orderMethod);
			page="OrderMethodDataEdit";
			util.getDynamics(map);
		} else {
			service.update(orderMethod);
			map.addAttribute("orderMethod",new OrderMethod());
			page="redirect:getAllOrderMethods";
		}
		return page;
	}
	
	@GetMapping("/deleteOrderMethod")
	public String delete(@RequestParam long orderMethodId) {
		service.deleteById(orderMethodId);
		return "redirect:getAllOrderMethods";
	}
}
