package com.sg.assignment.common.web;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.sg.assignment.common.service.LoginUserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private LoginUserService loginUserService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String id = authentication.getName();
		LoginUser loginUser = (LoginUser) loginUserService.loadUserByUsername(id);
		Optional.ofNullable(authentication.getCredentials())
				.filter(password -> passwordEncoder.matches(password.toString(), loginUser.getPassword()))
				.orElseThrow(() -> new BadCredentialsException("Bad credentials"));

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginUser.getUsername(),
				loginUser.getPassword(), loginUser.getAuthorities());
		token.setDetails(loginUser.getName());
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
