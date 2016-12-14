package com.vidya.tools.email.dao;

@FunctionalInterface
public interface DisposableEmailDAO {
	
	public boolean isDisposableEmailDomain(String domain);
}
