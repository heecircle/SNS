package com.heewon.sns.hashtag.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.heewon.sns.feed.dto.FeedReadResponseDto;
import com.heewon.sns.hashtag.domain.Hashtag;
import com.heewon.sns.hashtag.dto.HashtagCount;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

	@Query(value =
		"select new com.heewon.sns.feed.dto.FeedReadResponseDto(feed.id, feed.title, feed.author.nickname, feed.content, feed.imgUrl, feed.updatedAt) "
			+ "from Feed feed left join Follow follow on feed.author.id = follow.id.followId "
			+ "left join Hashtag hashtag on hashtag.feed.id = feed.id "
			+ "where follow.id.userId = :userId and hashtag.text = :hashtag")
	Page<FeedReadResponseDto> findByHashtag(@Param(value = "hashtag") String hashtag,
		@Param(value = "userId") Long userId, Pageable pageable);

	@Query(value = "select new com.heewon.sns.hashtag.dto.HashtagCount(count(feed.id), hashtag.text) from "
		+ "Feed feed left join Hashtag hashtag on feed.id = hashtag.feed.id "
		+ "where feed.createdAt > :time group by hashtag.text order by count(feed.id) desc limit 5")
	List<HashtagCount> top5(@Param("time") LocalDateTime time);

}
