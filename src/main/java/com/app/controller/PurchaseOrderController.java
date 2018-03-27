package com.app.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.controller.view.VendorInvoicePdfView;
import com.app.model.PurchaseOrder;
import com.app.model.PurchaseOrderDetails;
import com.app.service.IPurchaseOrderService;
import com.app.util.PurchaseOrderUtil;
import com.app.validator.PurchaseOrderValidator;

@Controller
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderUtil util;
	@Autowired
	private PurchaseOrderValidator validator;
	@Autowired
	private IPurchaseOrderService pService;

	/**
	 * 1. Get The Registration Page
	 * 
	 * @param map
	 * @return
	 */
	@GetMapping("/regPurchaseOrder")
	public String showPurchaseOrder(ModelMap map) {
		map.addAttribute("purchaseOrder", new PurchaseOrder());
		util.getDynamics(map);
		System.out.println(new PurchaseOrder().getShipmentMode());
		return "PurchaseOrderReg";
	}

	/**
	 * 2. Insert Purchase Order into table
	 * 
	 * @param po
	 * @param errors
	 * @param map
	 * @return
	 */
	@PostMapping("insertPurchaseOrder")
	public String save(@ModelAttribute PurchaseOrder po, BindingResult errors, ModelMap map) {
		validator.validate(po, errors);
		if (errors.hasErrors()) {
			map.addAttribute("purchaseOrder", po);
			util.getDynamics(map);
		} else {
			long id = pService.save(po);
			map.addAttribute("purchaseOrder", new PurchaseOrder());
			map.addAttribute("message", "Purchase Order '" + id + "' saved !! :)");
			util.getDynamics(map);
		}
		return "PurchaseOrderReg";
	}

	/**
	 * 3.Get All Purchase Orders
	 * 
	 * @param map
	 * @return
	 */
	@GetMapping("/getAllPurchaseOrders")
	public String getAll(ModelMap map) {
		List<PurchaseOrder> list = pService.getAll();
		map.addAttribute("poList", list);
		return "PurchaseOrderData";
	}

	/**
	 * 4.Delete One Purchase Order By ID
	 * 
	 * @param map
	 * @return
	 */
	@GetMapping("/deleteOrder")
	public String deleteOrder(@RequestParam long orderId, ModelMap map) {
		pService.deleteOneById(orderId);
		return "redirect:getAllPurchaseOrders";
	}

	/**
	 * 5.Edit Purchase Order
	 * 
	 * @param orderId
	 * @param map
	 * @return
	 */
	@GetMapping("/editPurchaseOrder")
	public String editOrder(@RequestParam long poId, ModelMap map) {
		map.addAttribute("purchaseOrder", pService.getOneById(poId));
		util.getDynamics(map);
		return "PurchaseOrderDataEdit";
	}

	/**
	 * 6.Update Purchase Order Details
	 * 
	 * @param map
	 * @return
	 */
	@PostMapping("/updatePurchaseOrder")
	public String updateOrder(@ModelAttribute PurchaseOrder purchaseOrder, BindingResult errors, ModelMap map) {
		validator.validate(purchaseOrder, errors);
		String page = null;
		if(errors.hasErrors()) {
			map.addAttribute("purchaseOrder",purchaseOrder);
			page = "PurchaseOrderDataEdit";
			util.getDynamics(map);
		} else {
			pService.update(purchaseOrder);
			map.addAttribute("purchaseOrder",new PurchaseOrder());
			page = "redirect:getAllPurchaseOrders";
		}
		return page;
	}
	/**
	 * 7. Show add item Page
	 * @param poId
	 * @param map
	 * @return
	 */
	@GetMapping("/addPoItem")
	public String showAddItems(@RequestParam long poId,ModelMap map) {
		PurchaseOrder po=pService.getOneById(poId);
		PurchaseOrderDetails poDtls=new PurchaseOrderDetails();
		poDtls.setPoHdrId(po.getOrderId());
		poDtls.setSlno(po.getDetails().size()+1); // set serial number
		map.addAttribute("poDtls",poDtls);
		map.addAttribute("po",po);
		//map.addAttribute("venItems",util.getVendor)
		map.addAttribute("venItems",util.getVendorItems(po.getVendor().getWhUserTypeId()));
		return "AddPoItems";
	}
	/**
	 * 8. Item Operations
	 * @param poDtl
	 * @param itemOpr
	 * @param map
	 * @return
	 */
	@PostMapping("/poItemAdd")
	public String itemOperation(@ModelAttribute PurchaseOrderDetails poDtl,@RequestParam String itemOpr,ModelMap map) {
		PurchaseOrder po=pService.getOneById(poDtl.getPoHdrId());
		String page=null;
		if("Add Item".equals(itemOpr)) {
			//if we click on add item
			po.getDetails().add(poDtl);
			po.setDefaultStatus("PICKING");//No vendor change
			pService.save(po);
			poDtl=new PurchaseOrderDetails();
			poDtl.setPoHdrId(po.getOrderId());
			poDtl.setSlno(po.getDetails().size()+1);
			map.addAttribute("poDtls",poDtl);
			map.addAttribute("venItems",util.getVendorItems(po.getVendor().getWhUserTypeId()));
			map.addAttribute("po",po);
			page="AddPoItems";
		} else if("Save and Continue".equals(itemOpr)) {
			if(!po.getDetails().isEmpty()) {
				po.setDefaultStatus("ORDERED");
				pService.update(po);	
				page="redirect:getAllPurchaseOrders";
			}
		}
		return page;
	}
	/**
	 * 9. Remove Items
	 * @param slno
	 * @param poId
	 * @param map
	 * @return
	 */
	@GetMapping("/removeItem")
	public String removeItem(@RequestParam int slno, @RequestParam long poId,ModelMap map) {
		PurchaseOrder po=pService.getOneById(poId);
		List<PurchaseOrderDetails> details=po.getDetails();
		if(details!=null) {
			Iterator<PurchaseOrderDetails> podtlitr=details.iterator();
			while(podtlitr.hasNext()) {
				PurchaseOrderDetails podtl=podtlitr.next();
				if(podtl.getSlno()==slno)//get number and check with given input
					podtlitr.remove();
			}
		}
		//Set new Serial Number
		if(details!=null && details.size()>0) {
			int slNo=1;
			Iterator<PurchaseOrderDetails> poDtlItr=details.iterator();
			while(poDtlItr.hasNext()) {
				PurchaseOrderDetails podtl=poDtlItr.next();
				podtl.setSlno(slNo++);
			}
		}
		if(details.size()==0)
			po.setDefaultStatus("OPEN");
		pService.update(po);
		PurchaseOrderDetails poDtls=new PurchaseOrderDetails();
		poDtls.setPoHdrId(po.getOrderId());
		poDtls.setSlno(po.getDetails().size()+1);
		
		map.addAttribute("venItems",util.getVendorItems(po.getVendor().getWhUserTypeId()));
		map.addAttribute("poDtls",poDtls);
		map.addAttribute("po", po);
		return "AddPoItems";
	}
	/**
	 * 10. Confirm Order
	 * @param poId
	 * @return
	 */
		@GetMapping("/poConfirm")
		public String confirmOrder(@RequestParam long poId) {
			PurchaseOrder po=pService.getOneById(poId);
			po.setDefaultStatus("INVOICED");
			pService.update(po);
			return "redirect:getAllPurchaseOrders";
	}
	
	/**
	 * 11. Cancel Order
	 * @param poId
	 * @return
	 */
	@GetMapping("/cancelOrder")
	public String cancelOrder(@RequestParam long poId) {
		PurchaseOrder po=pService.getOneById(poId);
		po.setDefaultStatus("CANCELLED");
		pService.update(po);
		return "redirect:getAllPurchaseOrders";
	}
	/**
	 * 12. Invoice Generator
	 * @param poId
	 * @return
	 */
	@GetMapping("/poInvoceGen")
	public ModelAndView generateInvoice(@RequestParam long poId) {
		PurchaseOrder po=pService.getOneById(poId);
		ModelAndView m=null;
		if(po.getDefaultStatus().equals("INVOICED") ) {
			m=new ModelAndView(new VendorInvoicePdfView(),"po",po);
		}
		else {
			m=new ModelAndView("PurchaseOrderData","posList",pService.getAll());
		}
		return m;
	}
		
}
