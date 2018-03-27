package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.model.ShipmentType;
import com.app.service.IShipmentTypeService;
import com.app.util.ShipmentTypeUtil;

@Component
public class ShipmentTypeValidator implements Validator {

	@Autowired
	private ShipmentTypeUtil util;
	
	@Autowired
	private IShipmentTypeService service;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ShipmentType.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "", "Enter description...");
		ShipmentType shipmentType=(ShipmentType)target;
		
		if(!util.getShipmentModes().contains(shipmentType.getShipmentMode()))	 {
			errors.rejectValue("shipmentMode", "", "Shipment value must be one of:"+shipmentType.getShipmentMode());
		}
		if(!Pattern.compile("[A-Z]{4,8}").matcher(shipmentType.getShipmentCode()).matches()) {
			errors.rejectValue("shipmentCode", "","Shipment Code must be 4-8 Upper case letters");
		}else if(service.isShipmentModeAndCodeExist(shipmentType)) {
			errors.rejectValue("shipmentCode", "", "'"+shipmentType.getShipmentCode()+"' is already exist with '"+shipmentType.getShipmentMode()+"'");
		}
		
		if(shipmentType.getEnabled()==null && !"YES".equals(shipmentType.getEnabled())) {
			errors.rejectValue("enabled", "","Enabled must be Yes or No value...");
		}
		if(shipmentType.getShipmentGrade()==null) {
			errors.rejectValue("shipmentGrade", "","Please choose one Grade..");
		} else if(!util.getShimpentGrades().contains(shipmentType.getShipmentGrade())) {
			errors.rejectValue("shipmentGrade", "","ShipmentGrade must be one of :"+shipmentType.getShipmentGrade());
		}
	}
}
