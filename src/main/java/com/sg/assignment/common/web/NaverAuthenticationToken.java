package com.sg.assignment.common.web;

import java.util.Collection;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class NaverAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = -597700897610926946L;

	private final Object principal;

	private Object credentials;

	public NaverAuthenticationToken(Object credentials) {
		// TODO Auto-generated constructor stub
		super(null);
		this.principal = null;
		this.credentials = credentials;
		setAuthenticated(false);
	}

	public NaverAuthenticationToken(Object principal, Object credentials) {
		// TODO Auto-generated constructor stub
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		setAuthenticated(false);
	}

	public NaverAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return this.credentials;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return this.principal;
	}
}
