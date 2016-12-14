package com.vidya.tools.email.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vidya.tools.email.model.ValidationRequest;
import com.vidya.tools.email.model.ValidationRequestVO;
import com.vidya.tools.email.model.ValidationResponse;
import com.vidya.tools.email.model.ValidationResponseVO;
import com.vidya.tools.email.service.EmailValidationService;

@Controller
@RequestMapping(value = "/v1/validate")
public class EmailValidationController {
	
	@Autowired
	private EmailValidationService emailValidationService;
	
	@Autowired
	private Converter<ValidationResponse,ValidationResponseVO> validationResponseVOConverter;

	@Autowired
	private Converter<ValidationRequestVO,ValidationRequest> validationRequestConverter;
	
	@RequestMapping(value = "/{email:.+}", method = RequestMethod.GET)
	@ResponseBody 
	public ValidationResponseVO validate(@PathVariable("email") String email) {

		ValidationResponse validationResponse = emailValidationService.isValid(email);
		return validationResponseVOConverter.convert(validationResponse);
	}

	@RequestMapping(value = "/emails", method = RequestMethod.POST)
	@ResponseBody
	public List<ValidationResponseVO> validate(@Size(min=1) @RequestBody List<ValidationRequestVO> requestVOList) {
		
		return requestVOList.stream().map(validationRequestConverter::convert)
			.map(emailValidationService::isValid)
			.map(validationResponseVOConverter::convert)
			.collect(Collectors.toList());
	}
}