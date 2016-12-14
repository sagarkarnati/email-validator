package com.vidya.tools.email.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;

@Configuration
public class TogglzConfig {
	
	public enum MyFeatures implements Feature {

	    @EnabledByDefault
	    @Label("Syntax validation")
	    SYNTAX_VALIDATION,

	    @Label("MXRecord validation")
	    MXRECORD_VALIDATION;

	    public boolean isActive() {
	        return FeatureContext.getFeatureManager().isActive(this);
	    }
	}
	
	@Bean
	public FeatureProvider featureProvider() {
	    return new EnumBasedFeatureProvider(MyFeatures.class);
	}
}