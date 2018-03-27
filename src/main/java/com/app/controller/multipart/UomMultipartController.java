package com.app.controller.multipart;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.controller.view.UomExcelView;
import com.app.model.Uom;
import com.app.service.IUomService;
import com.app.util.UomUtil;

@Controller
public class UomMultipartController {

	@Autowired
	private IUomService service;
	@Autowired
	private UomUtil uomUtil;
	@Autowired
	UomMultipartValidator validator;

	@GetMapping("/uomMultipart")
	public String showMultipartUom(){
		return "UomMultipart";
	}

	@GetMapping("/uomExport")
	public ModelAndView exportUom(){
		List<Uom> uoms=service.getAll();
		return new ModelAndView(new UomExcelView(), "uomList", uoms);
	}

	@PostMapping("/uomImport")
	public String importUom(@RequestParam MultipartFile uomFile,ModelMap map){
		if("".equals(uomFile.getOriginalFilename()) 
				|| !uomFile.getOriginalFilename().contains(".xlsx") ){
			map.addAttribute("importMessage", "Please Choose one Excel File..");
		}else{
			List<Uom> uomList=uomUtil.getUomsFromSheet(uomFile);
			if(uomList!=null && uomList.size()>0){
				Map<String,List<String>> errors=validator.validateMultipart(uomList);
				if(errors.isEmpty()){
					uomList=service.saveMultiple(uomList);
					map.addAttribute("importMessage", "Data Import done successfully");
				}
				else{
					map.addAttribute("importErrors", errors);
				}
			}else{
				map.addAttribute("importMessage", "No Rows Found in Excel");
			}
		}
		return "UomMultipart";
	}
}
