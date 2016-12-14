package com.vidya.tools.email.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vidya.tools.email.model.ValidationRequest;
import com.vidya.tools.email.model.ValidationRequestVO;

@Component("validationRequestVOToValidationRequestConverter")
public class ValidationRequestVOToValidationRequestConverter
		implements Converter<ValidationRequestVO, ValidationRequest> {

	@Override
	public ValidationRequest convert(ValidationRequestVO source) {
		
		ValidationRequest validationRequest = new ValidationRequest();
		
		validationRequest.setEmail(source.getEmail());
		validationRequest.setFirstName(source.getFirstName());
		validationRequest.setLastName(validationRequest.getLastName());
		
		return validationRequest;
	}
}