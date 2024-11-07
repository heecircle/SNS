package com.heewon.sns.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heewon.sns.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(Long id);
}
