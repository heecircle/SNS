package com.heewon.sns.feed.dto;

import java.time.LocalDateTime;

public interface FeedReadResponseInterface {

	String getId();

	String getTitle();

	String getAuthor();

	String getContent();

	String getImg_url();

	LocalDateTime getUpdated_at();
}
