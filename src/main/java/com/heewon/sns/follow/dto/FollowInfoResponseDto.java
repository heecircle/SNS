package com.heewon.sns.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FollowInfoResponseDto {
	Long id;
	String nickname;
	String imgUrl;
}
