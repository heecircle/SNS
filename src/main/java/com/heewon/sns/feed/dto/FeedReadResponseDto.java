package com.heewon.sns.feed.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class FeedReadResponseDto {
	private Long id;
	private String title;
	private String author;
	private String content;
	@JsonProperty("img_url")
	private String imgUrl;
	@JsonProperty("update_at")
	private LocalDateTime updateAt;
}
