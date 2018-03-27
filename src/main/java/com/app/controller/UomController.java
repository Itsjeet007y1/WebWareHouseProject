package com.app.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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

import com.app.model.Uom;
import com.app.service.IUomService;
import com.app.spec.UomSpecification;
import com.app.util.UomUtil;
import com.app.validator.UomValidator;

@Controller
public class UomController {
	@Autowired
	private IUomService service;
	@Autowired
	private UomUtil uomUtil;
	@Autowired
	private UomValidator validator;
	
	@GetMapping(value= {"/regPage"})
	public String showRegPage(ModelMap map) {
		map.addAttribute("uom",new Uom());
		map.addAttribute("uomTypes",uomUtil.getUomTypes());
		return "RegisterPage";
	}
	
	@PostMapping("/regUom")
	public String regUom(@ModelAttribute @Valid Uom uom,BindingResult errors, ModelMap map) {
		validator.validate(uom, errors);
		if(errors.hasErrors()) {
			map.addAttribute("uom",uom);
			map.addAttribute("uomTypes", uomUtil.getUomTypes());
		} else {
			long uomId=service.saveUom(	uom);
			map.addAttribute("message","Uom "+uomId+" created");
			map.addAttribute("uom",new Uom());
		}
		map.addAttribute("uomTypes", uomUtil.getUomTypes());
		return "RegisterPage";
	}
	
	@GetMapping("/deleteUom")
	public String deletUom(@RequestParam("uomId")long uomId) {// @RequestParam= request.getParameter("uomId");
		service.deleteById(uomId);
		return "redirect:showAllUom";
	}
	
	@GetMapping("/editUom")
	public String showEdit(@RequestParam("uomId")long uomId,ModelMap map) {
		Uom ob=service.getOneById(uomId);
		map.addAttribute("uom",ob);
		Map<String,String> mapList=uomUtil.getUomTypes();
		map.addAttribute("uomTypes",mapList);
		return "UomDataEdit";
	}
	@PostMapping("/updateUom")
	public String updateUom(@ModelAttribute @Valid Uom uom,BindingResult errors, ModelMap map) {
		validator.validate(uom, errors);
		String page=null;
		if(errors.hasErrors()) {
			map.addAttribute("uom"	,uom);
			Map<String,String> mapList=uomUtil.getUomTypes();
			map.addAttribute("uomTypes",mapList);
			page="UomDataEdit";
		} else {
			service.updateUom(uom);
			page="redirect:showAllUom";
		}
		return page;
	}
	
	//for pagination
	@GetMapping("/showAllUom")
	public String getUom(@PageableDefault(size=5,sort="uomId", direction=Direction.ASC) Pageable pageable,@ModelAttribute Uom uom ,ModelMap map)	 {
		Specification<Uom> spec=new UomSpecification(uom);
		Page<Uom> uomPage=service.getAll(spec, pageable);
		List<Uom> uomList=uomPage.getContent();
		uomUtil.replaceWithValues(uomList);
		map.addAttribute("uoms",uomList);
		map.addAttribute("uomPage",uomPage);
		return "ShowData";
	}
}
