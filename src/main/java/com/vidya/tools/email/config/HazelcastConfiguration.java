package com.vidya.tools.email.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
@EnableCaching
@ImportResource("classpath:spring-hazelcast.xml")
public class HazelcastConfiguration {

	@Bean
	public ConversionService conversionService() {
		return new DefaultConversionService();
	}
}