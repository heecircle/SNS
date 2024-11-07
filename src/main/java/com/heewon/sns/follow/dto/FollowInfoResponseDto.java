package com.heewon.sns.follow.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowInfoResponseDto {
	Long id;
	String nickname;
	String imgUrl;
	Boolean eachFollow;

	FollowInfoResponseDto(Long id, String nickname, String imgUrl, Long isFollow) {
		this.id = id;
		this.nickname = nickname;
		this.imgUrl = imgUrl;
		this.eachFollow = isFollow == 1;
	}
}
