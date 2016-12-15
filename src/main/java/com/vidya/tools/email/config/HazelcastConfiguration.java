package com.vidya.tools.email.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import com.hazelcast.config.Config;

@Configuration
@EnableCaching
public class HazelcastConfiguration {

	@Bean
	public ConversionService conversionService() {
		return new DefaultConversionService();
	}

	@Bean
	public Config getConfig() {
		return new Config();
	}
}