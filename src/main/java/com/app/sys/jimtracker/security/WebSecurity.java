
package com.app.sys.jimtracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.app.sys.jimtracker.repository.UserRepository;
import com.app.sys.jimtracker.service.UserService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomLogoutSuccessHandler myCustomLogout;

	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final UserRepository userRepository;

	public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder,
			UserRepository userRepository) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, SecurityConstants.PUBLIC_REPORT_ISSUE).permitAll()
				.antMatchers(HttpMethod.GET, "/api/reports/**").permitAll()	
				.antMatchers(HttpMethod.DELETE, SecurityConstants.DELETE_USER).hasRole("ADMIN")	
				.antMatchers("/api/**").authenticated()				
				.antMatchers("/**","/systeminfo/**", "/configuration/**", "/swagger*/**", "/webjars/**", "/swagger-ui/**")
				.permitAll().anyRequest().authenticated().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessHandler(myCustomLogout).invalidateHttpSession(true).and()
				.addFilter(getAuthenticationFilter())
				.addFilter(new AuthorizationFilter(authenticationManager(), userRepository)).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		;
	}

	/*
	 * .and() .logout().logoutRequestMatcher(new
	 * AntPathRequestMatcher("/api/users/logout"))
	 * .logoutSuccessUrl("/api/users/logout").deleteCookies("JSESSIONID")
	 * .invalidateHttpSession(true)
	 */

	/*
	 * logout().logoutUrl("/logout").logoutSuccessUrl("/login").deleteCookies(
	 * "auth_code", "JSESSIONID").invalidateHttpSession(true)
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/login");
		return filter;
	}

	
	/*
	 * @Bean public CorsConfigurationSource corsConfigurationSource() { final
	 * CorsConfiguration configuration = new CorsConfiguration();
	 * 
	 * configuration.setAllowedOrigins(Arrays.asList("*"));
	 * configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT",
	 * "DELETE","OPTIONS")); configuration.setAllowCredentials(true);
	 * configuration.setAllowedHeaders(Arrays.asList("*"));
	 * 
	 * final UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
	 * configuration);
	 * 
	 * return source; }
	 */
	 

}
