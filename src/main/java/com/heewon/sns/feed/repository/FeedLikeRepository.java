package com.heewon.sns.feed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heewon.sns.feed.domain.FeedLike;
import com.heewon.sns.feed.domain.FeedLikeId;

public interface FeedLikeRepository extends JpaRepository<FeedLike, FeedLikeId> {
	
}
