package com.heewon.sns.feed.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class FeedReadRequestDto {

	@JsonProperty("userId")
	private Long userId;

	@JsonProperty("page")
	private int page;
	
	@JsonProperty("size")
	private int size;
}
