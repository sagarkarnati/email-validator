package com.vidya.tools.email;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmailValidatorApplication.class);
	}
	
//	@Bean
//	public ThymeleafViewResolver viewResolver() {
//	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//	    viewResolver.setViewClass(ThymeleafTilesView.class);
//	    viewResolver.setTemplateEngine(templateEngine());
//	    viewResolver.setCharacterEncoding("UTF-8");
//	    viewResolver.setOrder(0);//ADDED LINE
//	    return viewResolver;
//	}
}
