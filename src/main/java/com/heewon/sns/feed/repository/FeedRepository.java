package com.heewon.sns.feed.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heewon.sns.feed.domain.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
	Feed findByTitle(String title);
}
