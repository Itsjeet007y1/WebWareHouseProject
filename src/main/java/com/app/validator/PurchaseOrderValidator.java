package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.model.PurchaseOrder;

@Component
public class PurchaseOrderValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PurchaseOrder.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description", "", "Please Enter Description!!!");
		PurchaseOrder po=(PurchaseOrder)target;
		
		if(!Pattern.compile("[A-Z]{4,8}").matcher(po.getOrderCode().trim()).matches()) {
			errors.rejectValue("orderCode", "", "Order Code must be 4-8 Upper Case Letters!!");
		}
		if(!Pattern.compile("[A-Z]{4,8}").matcher(po.getRefNumber().trim()).matches()) {
			errors.rejectValue("refNumber", "", "Please Enter 4-8 Upper Case Valid Reference Number!!!");
		}
		if(po.getQualityCheck()==null) {
			errors.rejectValue("qualityCheck", "", "Please Select one of the Buttons!!");
		}	
		/*if(po.getShipmentMode()) {
			errors.rejectValue("shipmentMode", "", "Please Select one of the Shipment mode...");
		}*/
	}
}
