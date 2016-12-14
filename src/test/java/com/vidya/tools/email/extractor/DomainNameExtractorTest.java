package com.vidya.tools.email.extractor;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DomainNameExtractorTest {

	private DomainNameExtractor domainNameExtractor;
	
	@Before
	public void setup() {
		
		domainNameExtractor = new DomainNameExtractor();
	}
	
	@After
	public void tearDown() {
		
		domainNameExtractor = null;
	}
	
	@Test
	public void test_happyPath() {
		
		String domain = domainNameExtractor.extract("vidya@hiregrid.io");
		assertEquals(domain,"hiregrid.io");
		
		String domain1 = domainNameExtractor.extract("vidya@yahoo.com");
		assertEquals(domain1,"yahoo.com");

		String domain2 = domainNameExtractor.extract("vidya@yahoo.co.in");
		assertEquals(domain2,"yahoo.co.in");
	}
	
	@Test
	public void test_failure() {
		
		String domain = domainNameExtractor.extract("vidya_hiregrid");
		assertEquals(domain,StringUtils.EMPTY);		
	}
}