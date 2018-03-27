package com.app.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.app.model.WhUserType;
import com.app.service.IWhUserTypeService;
import com.app.util.WhUserTypeUtil;

@Component
public class WhUserTypeValidator implements Validator {

	@Autowired
	private WhUserTypeUtil util;
	@Autowired
	private IWhUserTypeService service;

	@Override
	public boolean supports(Class<?> clazz) {
		return WhUserType.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "whUserIdNumber", "", "Enter ID Number..");

		WhUserType whu = (WhUserType) target;

		if (whu.getWhUserTyp() == null) {
			errors.rejectValue("whUserTyp", "", "Please choose one User Type");
		} else if (!util.getWhUserId().contains(whu.getWhUserTyp())) {
			errors.rejectValue("whUserTyp", "", "User Type must be from:" + util.getWhUserId());
		}
		if (!Pattern.compile("[A-Z]{4,8}").matcher(whu.getWhUserCode()).matches()) {
			errors.rejectValue("whUserCode", "", "User Code must 4-8 Uppercase caracters");
		} else if(service.isWhTypeAndUserCodeExist(whu)) {
			errors.rejectValue("whUserCode", "","'"+whu.getWhUserCode()+"' is already Exist with '"+whu.getWhUserTyp()+"'");
		}
		if(!Pattern.compile("[A-Z]{4,8}").matcher(whu.getWhUserFor()).matches()) {
			errors.rejectValue("whUserFor", "", "User Code must 4-8 UpperCase letters");
		}
		if (!Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
				.matcher(whu.getWhUserMail()).matches()) {
			errors.rejectValue("whUserMail", "", "Invalid Email Format");
		}
		if (!Pattern.compile("[0-9]{10}").matcher(whu.getWhUserContact()).matches()) {
			errors.rejectValue("whUserContact", "", "It must be 10 Digits");
		}
		if (!util.getWhUserIdTypes().contains(whu.getWhUserIdType())) {
			errors.rejectValue("whUserIdType", "", "Choose one ID type");
		} else if ("OTHER".equalsIgnoreCase(whu.getWhUserIdType())
				&& (whu.getWhUserIdTypeOther() == null || "".equals(whu.getWhUserIdTypeOther().trim()))) {
			errors.rejectValue("whUserIdTypeOther", "", "Provide Other ID Type Details");
		}
	}
}
