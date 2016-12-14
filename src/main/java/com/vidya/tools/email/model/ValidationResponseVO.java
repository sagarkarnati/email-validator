package com.vidya.tools.email.model;

public class ValidationResponseVO {

	private String email;
	private boolean valid;
	
	public ValidationResponseVO() {
	}
	
	public ValidationResponseVO(String email, boolean valid) {
		super();
		this.email = email;
		this.valid = valid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int valid = 1;
		valid = prime * valid + ((email == null) ? 0 : email.hashCode());
		valid = prime * valid + (this.valid ? 1231 : 1237);
		return valid;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValidationResponseVO other = (ValidationResponseVO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (valid != other.valid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SyntaxValidationResponse [email=" + email + ", valid=" + valid + "]";
	}

}
