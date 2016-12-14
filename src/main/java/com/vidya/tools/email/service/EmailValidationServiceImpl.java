package com.vidya.tools.email.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.vidya.tools.email.db.model.Company;
import com.vidya.tools.email.db.model.Email;
import com.vidya.tools.email.extractor.DomainNameExtractor;
import com.vidya.tools.email.model.ValidationRequest;
import com.vidya.tools.email.model.ValidationResponse;
import com.vidya.tools.email.mxrecords.MXRecordFetcher;
import com.vidya.tools.email.validator.CompanyEmailPatternValidator;
import com.vidya.tools.email.validator.DisposableEmailValidator;
import com.vidya.tools.email.validator.DomainNameValidator;
import com.vidya.tools.email.validator.FreeEmailValidator;
import com.vidya.tools.email.validator.SMTPValidator;
import com.vidya.tools.email.validator.SyntaxValidator;

@Service
public class EmailValidationServiceImpl implements EmailValidationService {
	
	@Autowired
	private SyntaxValidator syntaxValidator;
	
	@Autowired
	private DomainNameValidator domainNameValidator;
	
	@Autowired
	private DisposableEmailValidator disposableEmailValidator; 
	
	@Autowired
	private SMTPValidator smtpValidator;
	
	@Autowired
	private MXRecordFetcher mxRecordFetcher;
	
	@Autowired
	private DomainNameExtractor domainNameExtractor;

	@Autowired
	private FreeEmailValidator freeEmailValidator; 
	
	@Autowired
	private CompanyEmailPatternValidator companyEmailPatternValidator; 
	
	@Override
	public ValidationResponse isValid(String email) {
		
		String domain = domainNameExtractor.extract(email);
		
		List<String> mxRecords = mxRecordFetcher.fetch(domain);
		boolean validSMTP = false;
		String validMXRecord = null;
		for(String mxRecord : mxRecords){
			
			validSMTP = smtpValidator.isValid(mxRecord, email);
			if(validSMTP) {
				validMXRecord = mxRecord;
			}				
		}		
		
		Company company = new Company.Builder()
					.domain(domain)
					.mxRecord(validMXRecord)
					.build();
		
		Email emailObj = new Email.Builder(email)
					.validSyntax(syntaxValidator.isValid(email))
					.disposable(disposableEmailValidator.isDisposableEmail(email))
					.freeEmail(freeEmailValidator.isFreeEmailDomain(domain))
					.validDomain(domainNameValidator.isValid(email))
					.validMxRecords(!CollectionUtils.isEmpty(mxRecords))
					.validSMTP(validSMTP)
					.validCompanyEmailPattern(companyEmailPatternValidator.isValid(email))					
					.build();
		
		ValidationResponse validationResponse = new ValidationResponse(email,emailObj.isValid());
		return validationResponse;
	}

	@Override
	public ValidationResponse isValid(ValidationRequest emailRequest) {
		return isValid(emailRequest.getEmail());
	}
}