package com.sg.assignment.user.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sg.assignment.common.exception.DuplicateUserException;
import com.sg.assignment.user.model.User;
import com.sg.assignment.user.repository.UserRepository;
import com.sg.assignment.user.service.UserService;

@Service
public class UserJpaService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void join(User user) {
		// TODO Auto-generated method stub
		// User 정보 검사
		user.checkValue();
		if (getUser(user.getId()) != null) {
			throw new DuplicateUserException();
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	@Override
	public User getUser(String id) {
		// TODO Auto-generated method stub
		Optional<User> optional = userRepository.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}
}
