package com.vidya.tools.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidya.tools.email.dao.FreeEmailDAO;

@Service
public class FreeEmailServiceImpl implements FreeEmailService {
	
	@Autowired
	private FreeEmailDAO freeEmailDAO;
	
	@Override
	public boolean isFreeEmail(String emailDomain) {

		return freeEmailDAO.isFreeEmailDomain(emailDomain);
	}
}