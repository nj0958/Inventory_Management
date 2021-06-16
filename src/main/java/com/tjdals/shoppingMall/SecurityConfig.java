package com.tjdals.shoppingMall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 메소드 체인
		http
				.csrf() 
				.disable()
				.authorizeRequests()
				.antMatchers("/", "/user/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.permitAll()
				.loginPage("/user/login")
				.loginPage("/user/login")
                //.loginProcessingUrl("/user/login")
				//.successForwardUrl("/")
//				.loginProcessingUrl("/login_process")
				.and()
			.logout()
				.permitAll()
				.invalidateHttpSession(true)
//				.deleteCookies("JSESSIONID")
				.logoutUrl("/user/logout");

				
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
//	        .ignoring().antMatchers("/favicon.ico", "/resources/**", "/error");
//	        .antMatchers("/webjars/**", "/css/**", "/images/**");
			.antMatchers("/resources/**", "/css/**", "/images/**", "/webjars/**", "/error");
	}
	

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}
}