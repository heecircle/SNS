package com.heewon.sns.admin.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heewon.sns.admin.domain.FeedStatisticUser;

@Repository
public interface FeedStatisticUserRepository extends JpaRepository<FeedStatisticUser, Long> {
	List<FeedStatisticUser> findByDate(LocalDate date);
}
