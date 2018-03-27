package com.app.controller.multipart;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.app.model.Uom;
import com.app.service.IUomService;
import com.app.util.UomUtil;

@Component
public class UomMultipartValidator {
	
	@Autowired
	private UomUtil uomUtil;
	
	@Autowired
	private IUomService service;
	
	public Map<String,List<String>> validateMultipart(List<Uom> uomList){
		Map<String,List<String>> errors=new LinkedHashMap<String,List<String>>();
		int rowNum=1;
		for(Uom uom:uomList){
			List<String> errorsList=new ArrayList<String>();
			if(StringUtils.isEmpty(uom.getUomType())){
				errorsList.add("Uom Type Must Not be Empty");
			}else if(!uomUtil.getUomTypes().keySet().contains(uom.getUomType())){
				errorsList.add("Uom Type Must be one of "+uomUtil.getUomTypes().keySet().toString());
			}if(!Pattern.compile("[A-Z]{4,8}").matcher(uom.getUomModel()).matches()){
				errorsList.add("Uom Model should be 4-8 Upper case letter");
			}if(StringUtils.isEmpty(uom.getDescription())){
				errorsList.add("Description must not be Empty");
			}
			if(service.isUomTypeModelExist(uom)){
				errorsList.add("Model : '"+uom.getUomModel()+"' with Type:'"+uom.getUomType()+"' already Exist");
			}
			if(!errorsList.isEmpty())
				errors.put("Errors at Row :"+rowNum, errorsList);
			rowNum++;
		}
		
		return errors;
	}
}
