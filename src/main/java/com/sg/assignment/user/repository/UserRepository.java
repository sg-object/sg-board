package com.sg.assignment.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sg.assignment.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
