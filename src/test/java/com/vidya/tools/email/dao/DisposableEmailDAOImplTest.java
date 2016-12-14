package com.vidya.tools.email.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.vidya.tools.email.EmailValidatorApplication;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = EmailValidatorApplication.class)
@TestPropertySource(locations="classpath:application-test.properties")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class DisposableEmailDAOImplTest {
	
	@Autowired
	private DisposableEmailDAOImpl disposableEmailDAOImpl;
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
		disposableEmailDAOImpl = null;
	}
	
	@Test	
	@Sql({"/data/DISPOSABLE_EMAIL_DOMAINS_CONFIG-insert.sql"})
	public void test_happyPath() {
		
		boolean isDisposableEmailDomain = disposableEmailDAOImpl.isDisposableEmailDomain("xyz.com");
		assertTrue(isDisposableEmailDomain);
		
		isDisposableEmailDomain = disposableEmailDAOImpl.isDisposableEmailDomain("abc.com");
		assertTrue(isDisposableEmailDomain);
	}
	
	@Test
	@Sql({"/data/DISPOSABLE_EMAIL_DOMAINS_CONFIG-insert.sql"})
	public void test_fail() {
		
		boolean isDisposableEmailDomain = disposableEmailDAOImpl.isDisposableEmailDomain("hiregrid.io");
		assertFalse(isDisposableEmailDomain);
		
		isDisposableEmailDomain = disposableEmailDAOImpl.isDisposableEmailDomain("wagonsoft.com");
		assertFalse(isDisposableEmailDomain);
	}
}