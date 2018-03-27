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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Items;
import com.app.service.IItemsService;
import com.app.spec.ItemsSpecification;
import com.app.util.ItemsUtil;
import com.app.validator.ItemsValidator;

@Controller
public class ItemsController {

	@Autowired
	private ItemsUtil util;
	@Autowired
	private IItemsService service;
	@Autowired
	private ItemsValidator validator;

	@RequestMapping("/itemsRegPage")
	public String getRegPage(ModelMap map) {
		map.addAttribute("items", new Items());
		util.getDynamics(map);
		return "ItemsReg";
	}

	@PostMapping("/regItems")
	public String regItems(@ModelAttribute Items items,BindingResult errors, ModelMap map) {
		validator.validate(items, errors);
		if(errors.hasErrors()) {
			map.addAttribute("items",items);
			System.out.println(errors.hasErrors());
		} else {
			long id = service.save(items);
			map.addAttribute("message", "Items '" + id + "' saved.. :)");
		}
		util.getDynamics(map);			
		return "ItemsReg";
	}
	
	@GetMapping("/getAllItems")
	public String showData(@PageableDefault(size=5,sort="itemId",direction=Direction.ASC)Pageable pageable, @ModelAttribute Items items,ModelMap map) {
		Specification<Items> spec=new ItemsSpecification(items);
		Page<Items> itemPage=service.getAll(spec,pageable);
		List<Items> itemsList=itemPage.getContent();
		map.addAttribute("itemList",itemsList);
		map.addAttribute("itemPage",itemPage);
		util.getDynamics(map);
		return "ItemsData";
	}
	
	@GetMapping("/deleteItem")
	public String deletItem(@RequestParam long itemId) {
		service.deleteOneById(itemId);
		return "redirect:getAllItems";
	}
	
	@GetMapping("/editItem")
	public String editPage(@RequestParam long itemId,ModelMap map) {
		map.addAttribute("items",service.getOneById(itemId));
		util.getDynamics(map);
		return "ItemsDataEdit";
	}
	
	@PostMapping("/updateItem")
	public String updatePage(@ModelAttribute Items items,BindingResult errors,ModelMap map) {
		validator.validate(items, errors);
		String page=null;
		if(errors.hasErrors()) {
			util.getDynamics(map);
			page="ItemsDataEdit";
		} else {
			service.update(items);
			map.addAttribute("items",new Items());
			page="redirect:getAllItems";			
		}
		return page;
	}
}
