package com.app.util;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.app.model.Items;
import com.app.service.IItemsService;
import com.app.service.IShipmentTypeService;
import com.app.service.IWhUserTypeService;

@Component
public class PurchaseOrderUtil {
	@Autowired
	private IWhUserTypeService whService;
	@Autowired
	private IShipmentTypeService shipService;
	@Autowired
	private IItemsService itemService;
	
	
	public List<String> getQualityStatus() {
		List<String> qltList=new LinkedList<String>();
		qltList.add("Required");
		qltList.add("Not Required");
		return qltList;
	}
	public void getDynamics(ModelMap map) {
		map.addAttribute("qltList",getQualityStatus());
		map.addAttribute("enabled",shipService.findByEnabled("YES"));
		map.addAttribute("vendorList",whService.findByWhUserTyp("Vendor"));
	}
	public List<Items> getVendorItems(Long vendorId) {
		return itemService.findItemsByVendor(vendorId);
	}
}
