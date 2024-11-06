package com.heewon.sns.follow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heewon.sns.follow.dto.FollowCountInfoDto;
import com.heewon.sns.follow.dto.FollowInfoResponseDto;
import com.heewon.sns.follow.dto.FollowRequestDto;
import com.heewon.sns.follow.service.FollowService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {
	private final FollowService followService;

	@PostMapping("/create")
	public void makeFollow(@RequestBody FollowRequestDto requestDto) {
		followService.follow(requestDto.getFollowerId(), requestDto.getFolloweeId());
	}

	@GetMapping("/list")
	public List<FollowInfoResponseDto> getFollowerList(@RequestParam Long userId) {
		return followService.getFollowInfo(userId);
	}

	@GetMapping("/mycount")
	public FollowCountInfoDto getMyFollowerCount(@RequestParam Long userId) {
		return followService.countFollowInfo(userId);
	}

	@DeleteMapping("/unfollow")
	public void unfollow(@RequestParam Long userId, @RequestParam Long followerId) {
		followService.deleteFollowInfo(userId, followerId);
	}

}
