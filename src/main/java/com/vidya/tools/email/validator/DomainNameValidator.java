package com.vidya.tools.email.validator;

import java.net.InetAddress;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DomainNameValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DomainNameValidator.class);
	
	public boolean isValid(String domain) {
		try {
			InetAddress inetAddress = InetAddress.getByName(domain);
			return StringUtils.isNotBlank(inetAddress.getHostAddress());
		}catch(Exception e){
			LOGGER.debug("{} is not a valid domain",domain);
		}
		return false;
	}
}