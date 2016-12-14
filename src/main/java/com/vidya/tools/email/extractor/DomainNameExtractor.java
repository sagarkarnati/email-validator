package com.vidya.tools.email.extractor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DomainNameExtractor {

	private Pattern regex = Pattern.compile("(?<=@)\\S+");

	public String extract(String email) {

		Matcher regexMatcher = regex.matcher(email);
		if (regexMatcher.find()) {
			String result = regexMatcher.group();
			return result;
		}
		return StringUtils.EMPTY;
	}
}