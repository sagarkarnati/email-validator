package com.vidya.tools.email.dao;

import com.vidya.tools.email.db.model.Email;

public interface EmailValidationDAO {

	public Email save(Email emailObj);

	public Email find(String emailStr);
}