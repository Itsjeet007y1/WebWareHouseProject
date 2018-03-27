package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.model.Items;

@Component
public class ItemsValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Items.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "", "Please Enter description..!!");
		
		Items items=(Items)target;
		if(!Pattern.compile("[A-Z]{4,8}").matcher(items.getItemCode()).matches()) {
			errors.rejectValue("itemCode", "", "Item code must be 4-8 Upper Case Letters Only!!");
		}
		
		if(items.getCurrency()=="") {
			errors.rejectValue("currency", "", "Please Select one Currency!!");
		}
		
		/*if(items.getUom()==null) {
			System.out.println(items.getUom().getUomModel());
			errors.rejectValue("uom", "", "Please Select one Uom Model Type!!");
		}*/
	}
}
