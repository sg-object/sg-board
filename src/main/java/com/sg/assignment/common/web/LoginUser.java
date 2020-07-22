package com.sg.assignment.common.web;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LoginUser extends User {

	private static final long serialVersionUID = -5806468793675847409L;

	private String name;

	public LoginUser(com.sg.assignment.user.model.User user, Collection<? extends GrantedAuthority> authorities) {
		// TODO Auto-generated constructor stub
		super(user.getId(), user.getPassword(), authorities);
		this.name = user.getName();
	}
}
