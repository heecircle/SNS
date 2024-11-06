package com.heewon.sns.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class FeedCreateRequestDto {

	private String title;

	private String content;

	@NonNull
	private Long authorId;

}
