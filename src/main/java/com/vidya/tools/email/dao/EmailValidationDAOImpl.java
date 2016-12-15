package com.vidya.tools.email.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.vidya.tools.email.db.model.Email;

@Repository
public class EmailValidationDAOImpl implements EmailValidationDAO {

	public static final String INSERT_EMAIL = "INSERT INTO EMAIL (EMAIL_ADDRESS, VALID, VALID_SYNTAX, IS_DISPOSABLE,"
			+ " IS_FREE_EMAIL, VALID_MX_RECORD, VALID_SMTP, VALID_COMPANY_EMAIL_PATTERN) VALUES (?,?,?,?,?,?,?,?)";

	public static final String FIND_EMAIL = "SELECT ID, EMAIL_ADDRESS, VALID, VALID_SYNTAX, IS_DISPOSABLE,"
			+ " IS_FREE_EMAIL, VALID_MX_RECORD, VALID_SMTP, VALID_COMPANY_EMAIL_PATTERN FROM EMAIL WHERE EMAIL_ADDRESS = ?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@CachePut(value="emailCache",key="#emailObj.emailAddress")
	public Email save(Email emailObj) {

		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {

			PreparedStatement ps = connection.prepareStatement(INSERT_EMAIL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, emailObj.getEmailAddress());
			ps.setBoolean(2, emailObj.isValid());
			ps.setBoolean(3, emailObj.isValidSyntax());
			ps.setBoolean(4, emailObj.isDisposable());
			ps.setBoolean(5, emailObj.isFreeEmail());
			ps.setBoolean(6, emailObj.isValidMxRecords());
			ps.setBoolean(7, emailObj.isValidSMTP());
			ps.setBoolean(8, emailObj.isValidCompanyEmailPattern());
			
			return ps;
			
		}, holder);

		int emailObjId = holder.getKey().intValue();
		emailObj.setId(emailObjId);
		
		return emailObj;
	}
	
	@Override
	@Cacheable("emailCache")
	public Email find(String emailStr) {
		
		return jdbcTemplate.queryForObject(FIND_EMAIL, (ResultSet rs, int rowNum) -> {
			
				Email email = new Email.Builder(rs.getString("EMAIL_ADDRESS"))
						.validSyntax(rs.getBoolean("VALID_SYNTAX"))
						.disposable(rs.getBoolean("IS_DISPOSABLE"))
						.freeEmail(rs.getBoolean("IS_FREE_EMAIL"))
						.validMxRecords(rs.getBoolean("VALID_MX_RECORD"))
						.validSMTP(rs.getBoolean("VALID_SMTP"))
						.validCompanyEmailPattern(rs.getBoolean("VALID_COMPANY_EMAIL_PATTERN"))
						.build();
				
				email.setId(rs.getLong("ID"));
				return email;

		},emailStr);
	}
}