package com.vidya.tools.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vidya.tools.email.dao.DisposableEmailDAO;

@Service
public class DisposableEmailServiceImpl implements DisposableEmailService {

	@Autowired
	private DisposableEmailDAO disposableEmailDAO; 
	
	@Override
	public boolean isDisposableEmailDomain(String domain) {
		
		return disposableEmailDAO.isDisposableEmailDomain(domain);
	}
}