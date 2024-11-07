package com.heewon.sns.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FeedLikeRequestDto {
	Long userId;
	Long feedId;
}
