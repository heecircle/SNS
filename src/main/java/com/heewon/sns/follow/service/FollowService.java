package com.heewon.sns.follow.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import com.heewon.sns.follow.domain.Follow;
import com.heewon.sns.follow.domain.FollowId;
import com.heewon.sns.follow.dto.FollowCountInfoDto;
import com.heewon.sns.follow.dto.FollowInfoResponseDto;
import com.heewon.sns.follow.repository.FollowRepository;
import com.heewon.sns.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowService {
	private final FollowRepository followRepository;
	private final UserRepository userRepository;

	public void follow(Long userId, Long followId) throws NotFoundException {

		followRepository.save(Follow.builder().id(new FollowId(userId, followId)).build());
	}

	public List<FollowInfoResponseDto> getFollowInfo(Long userId) {
		return followRepository.findFollowById(userId);
	}

	public FollowCountInfoDto countFollowInfo(Long userId) {
		return new FollowCountInfoDto(followRepository.countDistinctById_UserId(userId) - 1,
			followRepository.countDistinctById_FollowId(userId) - 1);
	}

	@Transactional
	public void deleteFollowInfo(Long userId, Long followerId) {
		followRepository.deleteFollowsById_FollowIdAndId_UserId(followerId, userId);
	}

}
