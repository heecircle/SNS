package com.heewon.sns.hashtag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.heewon.sns.hashtag.domain.TopHashtag;

public interface HashtagRankRepository extends JpaRepository<TopHashtag, Long> {
	TopHashtag findByRanking(Integer rank);

	@Query(value = "select tophashtag from TopHashtag tophashtag order by tophashtag.ranking")
	List<TopHashtag> findTopHashtag();
}
