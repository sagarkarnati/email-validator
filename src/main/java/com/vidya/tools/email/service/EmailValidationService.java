package com.vidya.tools.email.service;

import com.vidya.tools.email.model.ValidationRequest;
import com.vidya.tools.email.model.ValidationResponse;

public interface EmailValidationService {
	
	ValidationResponse isValid(String email);
	
	ValidationResponse isValid(ValidationRequest emailRequest);
}
