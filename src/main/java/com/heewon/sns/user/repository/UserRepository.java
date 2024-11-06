package com.heewon.sns.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heewon.sns.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findById(long id);
}
