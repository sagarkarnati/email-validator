package com.vidya.tools.email.db.model;

import java.io.Serializable;

import com.vidya.tools.email.builder.BooleanBuilder;

public class Email implements Serializable {

	private static final long serialVersionUID = 5089321232789302463L;

	private long id;
	private String emailAddress;
	private boolean valid;
	private boolean validSyntax;
	private boolean disposable;
	private boolean freeEmail;
	private boolean validDomain;
	private boolean validMxRecords;
	private boolean validSMTP;
	private boolean validCompanyEmailPattern;
	
	public Email(long id) {
		this.id = id;
	}

	public Email() {
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isValidSyntax() {
		return validSyntax;
	}

	public void setValidSyntax(boolean validSyntax) {
		this.validSyntax = validSyntax;
	}

	public boolean isDisposable() {
		return disposable;
	}

	public void setDisposable(boolean disposable) {
		this.disposable = disposable;
	}

	public boolean isFreeEmail() {
		return freeEmail;
	}

	public void setFreeEmail(boolean freeEmail) {
		this.freeEmail = freeEmail;
	}

	public boolean isValidMxRecords() {
		return validMxRecords;
	}

	public void setValidMxRecords(boolean validMxRecords) {
		this.validMxRecords = validMxRecords;
	}

	public boolean isValidSMTP() {
		return validSMTP;
	}

	public void setValidSMTP(boolean validSMTP) {
		this.validSMTP = validSMTP;
	}

	public boolean isValidCompanyEmailPattern() {
		return validCompanyEmailPattern;
	}

	public void setValidCompanyEmailPattern(boolean validCompanyEmailPattern) {
		this.validCompanyEmailPattern = validCompanyEmailPattern;
	}

	public boolean isValidDomain() {
		return validDomain;
	}

	public void setValidDomain(boolean validDomain) {
		this.validDomain = validDomain;
	}
	
	public static class Builder {
		
		private String emailAddress;		
		private boolean validSyntax;
		private boolean disposable;
		private boolean validDomain;
		private boolean freeEmail;
		private boolean validMxRecords;
		private boolean validSMTP;
		private boolean validCompanyEmailPattern;
		
		public Builder(String emailAddress) {
			this.emailAddress = emailAddress;
		}
		
		public Builder validSyntax(boolean validSyntax){
			this.validSyntax = validSyntax;
			return this;
		}
		
		public Builder disposable(boolean disposable){
			this.disposable = disposable;
			return this;
		}

		public Builder freeEmail(boolean freeEmail){
			this.freeEmail = freeEmail;
			return this;
		}

		public Builder validMxRecords(boolean validMxRecords){
			this.validMxRecords = validMxRecords;
			return this;
		}

		public Builder validSMTP(boolean validSMTP){
			this.validSMTP = validSMTP;
			return this;
		}
		
		public Builder validCompanyEmailPattern(boolean validCompanyEmailPattern){
			this.validCompanyEmailPattern = validCompanyEmailPattern;
			return this;
		}
		
		public Builder validDomain(boolean validDomain) {
			this.validDomain = validDomain;
			return this;
		}

		public Email build() {
			Email email = new Email();
			email.setEmailAddress(emailAddress);
			email.setValidSyntax(validSyntax);
			email.setValidDomain(validDomain);
			email.setDisposable(disposable);
			email.setFreeEmail(freeEmail);
			email.setValidMxRecords(validMxRecords);
			email.setValidSMTP(validSMTP);
			email.setValidCompanyEmailPattern(validCompanyEmailPattern);
			
			BooleanBuilder booleanBuilder = new BooleanBuilder();
			booleanBuilder.and(validSyntax);
			booleanBuilder.and(disposable);
			booleanBuilder.and(validDomain);
			booleanBuilder.and(freeEmail);
			booleanBuilder.and(validMxRecords);
			booleanBuilder.and(validSMTP);
			booleanBuilder.and(validCompanyEmailPattern);
			
			email.setValid(booleanBuilder.build());
			
			return email;
		}

    }
}