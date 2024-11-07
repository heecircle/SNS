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

	@Query(value =
		"SELECT new com.heewon.sns.follow.dto.FollowInfoResponseDto(user.id, user.nickname, user.imgUrl, "
			+ "sum(case when follow1.id.userId = follow2.id.followId then 1 else 0 end)"
			+ ") "
			+ "FROM Follow follow1 "
			+ "left join Follow follow2 on follow1.id.followId = follow2.id.userId "
			+ "left join  User user on follow1.id.followId = user.id "
			+ "where follow1.id.userId = :id "
			+ "group by follow1.id.userId, follow1.id.followId"
	)
	List<FollowInfoResponseDto> findFollowById(@Param("id") Long id);

	Integer countDistinctById_UserId(Long userId);

	Integer countDistinctById_FollowId(Long followId);

	void deleteFollowsById_FollowIdAndId_UserId(Long id, Long userId);

}
