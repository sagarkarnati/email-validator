package com.vidya.tools.email.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vidya.tools.email.service.FreeEmailService;

@Component
public class FreeEmailValidator {

	@Autowired
	private FreeEmailService freeEmailService;

	public boolean isFreeEmailDomain(String domain) {

		return freeEmailService.isFreeEmail(domain);
	}
}