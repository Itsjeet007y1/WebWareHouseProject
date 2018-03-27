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
import com.app.model.ShipmentType;
import com.app.model.WhUserType;
import com.app.service.IWhUserTypeService;
import com.app.spec.ShipmentTypeSpecification;
import com.app.spec.WhUserTypeSpecification;
import com.app.util.WhUserTypeUtil;
import com.app.validator.WhUserTypeValidator;

@Controller
public class WhUserTypeController {
	@Autowired
	private IWhUserTypeService service;
	@Autowired
	private WhUserTypeUtil util;
	@Autowired
	private WhUserTypeValidator validator;
	
	@GetMapping("/whUserTypeReg")
	public String showWhUserRegPage(ModelMap map) {
		map.addAttribute("whUserType",new WhUserType());
		util.getDynamics(map);
		return "WhUserTypeReg";
	}
	
	@PostMapping("/insertWhUser")
	public String insertWhUser(@ModelAttribute WhUserType whUserType,BindingResult errors,ModelMap map) {
		validator.validate(whUserType, errors);
		if(errors.hasErrors()) {
			map.addAttribute("whUserType",whUserType);
		}
		else {
			long id=service.save(whUserType);
			map.addAttribute("message","WhUserType '"+id+"' saved.. :)");
			map.addAttribute("whUserType",new WhUserType());
		}
		util.getDynamics(map);
		return "WhUserTypeReg";
	}
	
	/*@GetMapping("/getAllWhUserType")
	public String getAllWhUser(ModelMap map) {
		List<WhUserType> whList=service.getAll();
		System.out.println(whList);
		map.addAttribute("whList",whList);
		return "WhUserTypeData";
	}*/
	
	@GetMapping("/getAllWhUserType")
	public String showData(@PageableDefault(size=3,sort="whUserTypeId",direction=Direction.ASC)Pageable pageable, @ModelAttribute WhUserType whUserType,ModelMap map) {
		Specification<WhUserType> spec=new WhUserTypeSpecification(whUserType);
		Page<WhUserType> whUserPage=service.getAll(spec,pageable);
		List<WhUserType> whList=whUserPage.getContent();
		map.addAttribute("whList",whList);
		map.addAttribute("whUserPage",whUserPage);
		util.getDynamics(map);
		return "WhUserTypeData";
	}
	
	
	 @GetMapping("/deleteWhUserType")
	public String deleteWhUser(@RequestParam long whUserTypeId) {
		 service.deleteOneById(whUserTypeId);
		 return "redirect:getAllWhUserType";
	 }
	 
	 @GetMapping("/editWhUserType")
		public String edit(@RequestParam long whUserTypeId, ModelMap map) {
			map.addAttribute("whUserType",service.getOneById(whUserTypeId));
			util.getDynamics(map);
			return "WhUserTypeDataEdit";
		}
	 
	 @PostMapping("/updateWhUser")
		public String update(@ModelAttribute WhUserType whUserType,BindingResult errors,ModelMap map) {
			validator.validate(whUserType, errors);
			String page=null;
			if(errors.hasErrors()) {
				map.addAttribute("whUserType",whUserType);
				page="WhUserTypeDataEdit";
				util.getDynamics(map);
			} else {
				service.update(whUserType);
				map.addAttribute("whUserType",new WhUserType());
				page="redirect:getAllWhUserType";
			}
			return page;
	 }
}
