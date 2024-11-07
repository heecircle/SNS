package com.heewon.sns.hashtag.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heewon.sns.hashtag.domain.TopHashtag;

public interface HashtagRankRepository extends JpaRepository<TopHashtag, Long> {
	TopHashtag findByRanking(Integer rank);

}
