package com.sg.assignment.common.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class NaverAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		NaverAuthenticationToken token = new NaverAuthenticationToken(authentication.getPrincipal(),
				authentication.getCredentials(), getAuthorities());
		token.setDetails(authentication.getCredentials());
		return token;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(NaverAuthenticationToken.class);
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		auth.add(new SimpleGrantedAuthority("ROLE_USER"));
		return auth;
	}
}
