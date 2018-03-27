package com.app.controller;

import java.util.List;

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

import com.app.model.ShipmentType;
import com.app.service.IShipmentTypeService;
import com.app.spec.ShipmentTypeSpecification;
import com.app.util.ShipmentTypeUtil;
import com.app.validator.ShipmentTypeValidator;

@Controller
public class ShipmentTypeController {
	@Autowired
	private IShipmentTypeService service;
	@Autowired
	private ShipmentTypeUtil util;
	@Autowired
	private ShipmentTypeValidator validator;

	@GetMapping("/regShipmentType")
	public String showRegisterPage(ModelMap map) {
		map.addAttribute("shipmentType", new ShipmentType());
		map.addAttribute("shipmentModes", util.getShipmentModes());
		map.addAttribute("shipmentGrades", util.getShimpentGrades());
		return "ShipmentTypeRegister";
	}

	@PostMapping("/insertShipmentType")
	public String registerShipment(@ModelAttribute ShipmentType shipmentType, BindingResult errors, ModelMap map) {
		validator.validate(shipmentType, errors);
		if (errors.hasErrors()) {
			map.addAttribute("shipmentType", shipmentType);
		} else {
			long shipmentTypeId = service.saveShipment(shipmentType);
			map.addAttribute("message", "ShipmentType saved with id :" + shipmentTypeId);
			map.addAttribute("shipment", new ShipmentType());
		}
		map.addAttribute("shipmentModes", util.getShipmentModes());
		map.addAttribute("shipmentGrades", util.getShimpentGrades());
		return "ShipmentTypeRegister";
	}
	
	@GetMapping("/getAllShipmentTypes")
	public String showData(@PageableDefault(size=3,sort="shipmentId",direction=Direction.ASC)Pageable pageable, @ModelAttribute ShipmentType shipmentType,ModelMap map) {
		Specification<ShipmentType> spec=new ShipmentTypeSpecification(shipmentType);
		Page<ShipmentType> shipPage=service.getAll(spec,pageable);
		List<ShipmentType> shipList=shipPage.getContent();
		map.addAttribute("shipList",shipList);
		map.addAttribute("shipPage",shipPage);
		map.addAttribute("shipmentModes",util.getShipmentModes());
		map.addAttribute("shipmentGrades",util.getShimpentGrades());
		return "ShipmentTypeData";
	}
	
	@GetMapping("/deleteShipmentType")
	public String deleteShipment(@RequestParam("shipmentTypeId")Long shipmentId) {
		service.deleteOneById(shipmentId);
		return "redirect:getAllShipmentTypes";
	}
	
	@GetMapping("/editShipmentType")
	public String editShipment(@RequestParam long shipmentTypeId,ModelMap map) {
		map.addAttribute("shipmentType",service.getOneById(shipmentTypeId));
		map.addAttribute("shipmentGrade",util.getShimpentGrades());
		map.addAttribute("shipmentMode",util.getShipmentModes());
		return "ShipmentTypeDataEdit";
	}
	
	@PostMapping("/updateShipmentType")
	public String updateShipment(@ModelAttribute @Valid ShipmentType shipmentType,BindingResult errors,ModelMap map) {
		validator.validate(shipmentType, errors);
		String page=null;
		if(errors.hasErrors()) {
			System.out.println(errors.hasErrors());
			map.addAttribute("shipmentType", shipmentType);
			map.addAttribute("shipmentMode",util.getShipmentModes());
			map.addAttribute("shipmentGrade",util.getShimpentGrades());
			page="ShipmentTypeDataEdit";
		}
		else {
			service.updateShipment(shipmentType);
			page="redirect:getAllShipmentTypes";
		}
		return page;
	}
}
