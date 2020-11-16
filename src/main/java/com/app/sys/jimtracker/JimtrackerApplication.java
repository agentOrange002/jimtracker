package com.app.sys.jimtracker;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.sys.jimtracker.security.AppProperties;

/*
 * @OpenAPIDefinition(info = @Info( title="ITS REST API", version = "v.0.1.0",
 * description = "ITS REST API Spring Boot"))
 */
@SpringBootApplication
public class JimtrackerApplication {
	public static void main(String[] args) {
		SpringApplication.run(JimtrackerApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}

	@Bean(name = "newDataSource")
	public DataSource getDataSource() {
		@SuppressWarnings("rawtypes")
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
		dataSourceBuilder.url("jdbc:mysql://localhost:3306/itsystemdb?useSSL=false");
		dataSourceBuilder.username("root");
		dataSourceBuilder.password("!Neob2235orange02");
		return dataSourceBuilder.build();
	}

}
