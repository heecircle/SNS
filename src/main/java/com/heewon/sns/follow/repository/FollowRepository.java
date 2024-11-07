package com.heewon.sns.follow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.heewon.sns.follow.domain.Follow;
import com.heewon.sns.follow.domain.FollowId;
import com.heewon.sns.follow.dto.FollowInfoResponseDto;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {

	@Query(value = "select DISTINCT new com.heewon.sns.follow.dto.FollowInfoResponseDto(follow.follower.id, follow.follower.nickname, follow.follower.imgUrl) from Follow follow where follow.followed.id = :id")
	List<FollowInfoResponseDto> findFollowById(@Param("id") Long id);

	Integer countDistinctByFollowed_Id(Long followedId);

	Integer countDistinctByFollower_Id(Long followerId);

	void deleteAllByFollowed_IdAndFollower_Id(Long followedId, Long followerId);

}
