package com.vidya.tools.email.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
public class FreeEmailDAOImpl implements FreeEmailDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	private static final String SELECT_QUERY = "SELECT DOMAIN FROM FREE_EMAIL_DOMAINS_CONFIG WHERE DOMAIN = ?";
	
	@Override
	@Cacheable("freeEmailDomainCache")
	public boolean isFreeEmailDomain(String emailDomain) {
		
		List<String> domains = jdbcTemplate.query(SELECT_QUERY, new RowMapper<String>(){
			
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {

				return rs.getString("DOMAIN");
			}
		},emailDomain);
		
		return !CollectionUtils.isEmpty(domains);
	}
}