package com.app.validator;

import java.util.regex.Pattern;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.model.Uom;
import com.app.service.IUomService;
import com.app.util.UomUtil;

@Component
public class UomValidator implements Validator {
	
	@Autowired
	private UomUtil uomUtil;
	@Autowired
	private IUomService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description","", "Enter Uom Description !!!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uomModel", "","Enter Uom Model !!!");
		Uom uom=(Uom)target;
		if(!uomUtil.getUomTypes().keySet().contains(uom.getUomType())) {
			errors.rejectValue("uomType","", "Uom Type Must be one of : "+uomUtil.getUomTypes().keySet()+" !!!");
		}
		if(!Pattern.compile("[A-Z]{4,8}").matcher(uom.getUomModel()).matches()) {
			errors.rejectValue("uomModel","","Uom Model should be 4-8 upper case letters only !!!");
		} else if(service.isUomTypeModelExist(uom)) {
			errors.rejectValue("uomModel","","'"+ uom.getUomType()+"' with ' "+uom.getUomModel()+"'"+" 'already exists !!!");
		}
	}
}
