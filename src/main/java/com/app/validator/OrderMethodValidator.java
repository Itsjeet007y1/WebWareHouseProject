package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.model.OrderMethod;
import com.app.service.IOrderMethodService;
import com.app.util.OrderMethodUtil;

@Component
public class OrderMethodValidator implements Validator {

	@Autowired
	private OrderMethodUtil util;
	@Autowired
	private IOrderMethodService service;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return OrderMethod.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description","","Please Enter Description..");
		OrderMethod om=(OrderMethod)target;
		
		if(!Pattern.compile("[A-Z]{4,8}").matcher(om.getOrderCode()).matches()) {
			errors.rejectValue("orderCode","","Enter 4-8 upper case letters... ");
		} else if(service.isOrderModeAndOrderCodeExist(om)) {
			errors.rejectValue("orderCode", "", "'"+om.getOrderCode()+"'"+" with Order Mode '"+om.getOrderMode()+"' is already Exist.");
		}
		if(!util.getOrderMethods().contains(om.getOrderMetd())) {
			errors.rejectValue("orderMetd","","Order Methods must be one of: "+util.getOrderMethods());
		}
		
		/*if(!util.getOrderAccepts().contains(om.getOrderAccept())) {
			errors.rejectValue("orderAccept", "", "Please select at least one checkbox value.."+util.getOrderAccepts());
		}*/
		
		if(om.getOrderMode()==null || !util.getOrderModes().contains(om.getOrderMode())) {
			errors.rejectValue("orderMode", "","Please select one order mode from: "+util.getOrderModes());
		}
	}
}
