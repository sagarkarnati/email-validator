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
public class FreeEmailDAOImplTest {
	
	@Autowired
	private FreeEmailDAOImpl freeEmailDAOImpl;
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
		freeEmailDAOImpl = null;
	}
	
	@Test	
	@Sql({"/data/FREE_EMAIL_DOMAINS_CONFIG-insert.sql"})
	public void test_happyPath() {
		
		boolean isFreeEmailDomain = freeEmailDAOImpl.isFreeEmailDomain("gmail.com");
		assertTrue(isFreeEmailDomain);
		
		isFreeEmailDomain = freeEmailDAOImpl.isFreeEmailDomain("yahoo.com");
		assertTrue(isFreeEmailDomain);
	}
	
	@Test
	@Sql({"/data/FREE_EMAIL_DOMAINS_CONFIG-insert.sql"})
	public void test_fail() {
		
		boolean isFreeEmailDomain = freeEmailDAOImpl.isFreeEmailDomain("hiregrid.io");
		assertFalse(isFreeEmailDomain);
		
		isFreeEmailDomain = freeEmailDAOImpl.isFreeEmailDomain("wagonsoft.com");
		assertFalse(isFreeEmailDomain);
	}
}