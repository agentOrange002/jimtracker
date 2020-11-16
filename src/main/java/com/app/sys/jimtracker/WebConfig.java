package com.app.sys.jimtracker;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
	@Override
	public void addCorsMappings(CorsRegistry registry)
	{
		registry
		.addMapping("/**").allowCredentials(true)               
        .allowedHeaders("Authorization", "Cache-Control", "Content-Type", "Accept", "X-Requested-With", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Origin")
        .exposedHeaders("Authorization","UserID")
				/*
				 * .exposedHeaders("Access-Control-Expose-Headers", "Authorization","UserID",
				 * "Cache-Control", "Content-Type", "Access-Control-Allow-Origin",
				 * "Access-Control-Allow-Headers", "Origin")
				 */
		.allowedMethods("*")
		.allowedOrigins("*");
	}
}
