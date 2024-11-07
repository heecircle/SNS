package com.heewon.sns.feed.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.heewon.sns.feed.domain.Feed;
import com.heewon.sns.feed.dto.FeedReadResponseDto;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {
	Feed findByTitle(String title);

	@Query(value = "SELECT " +
		"new com.heewon.sns.feed.dto.FeedReadResponseDto(feed.id, feed.title, user.nickname, feed.content, feed.imgUrl, "
		+ "feed.updatedAt) "
		+ "from Feed feed "
		+ "inner join User user on user.id = feed.author.id where feed.author.id in (select follow.id.followId from Follow follow where follow.id.userId = :id)")
	Page<FeedReadResponseDto> findFeedById(@Param("id") Long id, Pageable pageable);

	Feed findFeedById(Long id);

	@Query(value = "select "
		+ "new com.heewon.sns.feed.dto.FeedReadResponseDto(feed.id, feed.title, feed.author.nickname, feed.content, feed.imgUrl, feed.updatedAt) from Feed feed "
		+ "inner join Follow follow on follow.id.followId = feed.author.id "
		+ "where feed.author.id = follow.id.followId "
		+ "and follow.id.userId = :userId and (feed.content like :content or feed.title like :title)")
	Page<FeedReadResponseDto> findFeedsByContentContainingIgnoreCaseAndTitleContainingIgnoreCaseAndAuthor_UserId(
		@Param("userId") Long userId,
		@Param("content") String content,
		@Param("title") String title, Pageable pageable);
}
