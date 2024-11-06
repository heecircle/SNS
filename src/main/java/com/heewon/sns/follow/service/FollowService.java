package com.heewon.sns.follow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import com.heewon.sns.follow.domain.Follow;
import com.heewon.sns.follow.dto.FollowCountInfoDto;
import com.heewon.sns.follow.dto.FollowInfoResponseDto;
import com.heewon.sns.follow.repository.FollowRepository;
import com.heewon.sns.user.domain.User;
import com.heewon.sns.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowService {
	private final FollowRepository followRepository;
	private final UserRepository userRepository;

	public void follow(Long followerId, Long followeeId) throws NotFoundException {

		User follower = userRepository.findById(followerId).orElseThrow();
		User followee = userRepository.findById(followeeId).orElseThrow();

		followRepository.save(Follow.builder().follower(follower).followed(followee).build());
	}

	public List<FollowInfoResponseDto> getFollowInfo(Long userId) {
		return followRepository.findFollowById(userId);
	}

	public FollowCountInfoDto countFollowInfo(Long userId) {
		return new FollowCountInfoDto(followRepository.countDistinctByFollowed_Id(userId) - 1,
			followRepository.countDistinctByFollower_Id(userId) - 1);
	}

	@Transactional
	public void deleteFollowInfo(Long userId, Long followerId) {
		followRepository.deleteAllByFollowed_IdAndFollower_Id(userId, followerId);
	}

}
