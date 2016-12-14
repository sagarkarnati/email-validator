package com.vidya.tools.email.validator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.vidya.tools.email.validator.SyntaxValidator;

/**
 * Email validator Testing
 *
 */
@RunWith(DataProviderRunner.class)
public class SyntaxValidatorTest {

	private SyntaxValidator emailValidator;

	@Before
	public void setup() {
		emailValidator = new SyntaxValidator();
	}
	
	@After
	public void tearDown() {
		emailValidator = null;
	}
	
	@Test
	@DataProvider({"vidya@hiregrid.io",
		"vidya-100@hiregrid.io", "vidya.100@hiregrid.io",
		"vidya111@vidya.io", "vidya-100@vidya.net",
		"vidya.100@vidya.io.au", "vidya@1.io",
		"vidya@gmail.io.io", "vidya+100@gmail.io",
		"vidya-100@hiregrid-test.io"})
	public void validEmailTest(String email) {

		boolean valid = emailValidator.isValid(email);
		Assert.assertEquals(valid, true);
	}

	@Test
	@DataProvider({"vidya", "vidya@.com.my",
		"vidya123@gmail.a", "vidya123@.com", "vidya123@.com.com",
		".vidya@vidya.com", "vidya()*@gmail.com", "vidya@%*.com",
		"vidya..2002@gmail.com", "vidya.@gmail.com",
		"vidya@vidya@gmail.com", "vidya@gmail.com.1a","vidya@gmail"})
	public void inValidEmailTest(String email) {

		boolean valid = emailValidator.isValid(email);
		Assert.assertEquals(valid, false);
	}
}