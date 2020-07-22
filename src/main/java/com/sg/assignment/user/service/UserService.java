package com.sg.assignment.user.service;

import com.sg.assignment.user.model.User;

public interface UserService {

	public void join(User user);

	public User getUser(String id);
}
