package com.heewon.sns.feed.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heewon.sns.feed.domain.FeedCheck;

public interface FeedCheckRepository extends JpaRepository<FeedCheck, Long> {
	FeedCheck findByUser_Id(Long userId);

	void deleteByCheckTimeBefore(LocalDateTime checkTime);
}
