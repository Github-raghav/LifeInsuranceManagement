package com.Lims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService getUserDetailService() {
		return new UserDetailServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public PasswordEncoder passwordEncoder()
//	{
//	    return new BCryptPasswordEncoder();
//	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {		
		DaoAuthenticationProvider doaAuthenticationProvider =new DaoAuthenticationProvider();
		doaAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
		doaAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return doaAuthenticationProvider;
	}
	//config method

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("inside configure method");
      http.authorizeRequests().antMatchers("/admin/**").hasAuthority("ADMIN")
      .antMatchers("/user/****").hasAuthority("user")
      .antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").and().csrf().disable();
      
	}
	
	
	
	
}
 