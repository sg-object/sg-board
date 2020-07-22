package com.sg.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.sg.assignment.common.web.CustomAuthenticationProvider;
import com.sg.assignment.common.web.NaverAuthenticationProvider;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ROOT_ENTRY_POINT = "/";

	private static final String LOGIN_ENTRY_POINT = "/login";

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/resources/**", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
				"/configuration/**", "/swagger-ui.html", "/webjars/**", "/console/**", "/images/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
				.antMatchers(ROOT_ENTRY_POINT, LOGIN_ENTRY_POINT, "/join", "/naver/callback")
				.permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
				.usernameParameter("id")
				.passwordParameter("password")
				.loginPage(ROOT_ENTRY_POINT)
				.loginProcessingUrl(LOGIN_ENTRY_POINT)
				.defaultSuccessUrl("/boards", true)
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl(ROOT_ENTRY_POINT)
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
			.and()
				.csrf().disable();
		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false).expiredUrl(LOGIN_ENTRY_POINT);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(customAuthenticationProvider())
				.authenticationProvider(new NaverAuthenticationProvider());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
