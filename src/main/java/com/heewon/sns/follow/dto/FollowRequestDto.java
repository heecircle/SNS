package com.heewon.sns.follow.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FollowRequestDto {
	private Long followerId;
	private Long followeeId;
}
