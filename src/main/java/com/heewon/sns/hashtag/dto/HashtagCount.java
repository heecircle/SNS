package com.heewon.sns.hashtag.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class HashtagCount {
	private int count;
	private String text;

	public HashtagCount(Long count, String text) {
		this.count = count.intValue();
		this.text = text;
	}
}
