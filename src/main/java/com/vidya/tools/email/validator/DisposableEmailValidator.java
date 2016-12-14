package com.vidya.tools.email.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vidya.tools.email.service.DisposableEmailService;

@Component
public class DisposableEmailValidator {
	
	@Autowired
	private DisposableEmailService disposableEmailService; 
	
	public boolean isDisposableEmail(String domain) {
		return disposableEmailService.isDisposableEmailDomain(domain);
	}
}