package com.vidya.tools.email.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vidya.tools.email.model.ValidationResponse;
import com.vidya.tools.email.model.ValidationResponseVO;

@Component("validationResponseToValidationResponseVOConverter")
public class ValidationResponseToValidationResponseVOConverter
		implements Converter<ValidationResponse, ValidationResponseVO> {

	@Override
	public ValidationResponseVO convert(ValidationResponse source) {
		
		ValidationResponseVO responseVO = new ValidationResponseVO();
		
		responseVO.setEmail(source.getEmail());
		responseVO.setValid(source.isValid());
		
		return responseVO;
	}
}