package com.vidya.tools.email.dao;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class DisposableEmailDAOImpl implements DisposableEmailDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	private static final String SELECT_QUERY = "SELECT DOMAIN FROM DISPOSABLE_EMAIL_DOMAINS_CONFIG WHERE DOMAIN = ?";

	@Override
	@Cacheable("disposableEmailDomainCache")
	public boolean isDisposableEmailDomain(String domain) {

		List<String> domains = jdbcTemplate.query(SELECT_QUERY, (ResultSet rs, int rowNum) -> rs.getString("DOMAIN") ,domain);
		return !CollectionUtils.isEmpty(domains);
	}
}