package com.heewon.sns.feed.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Feed {

	@Id
	private Long id;

	@Column
	private String title;

	@Column(length = 1024)
	private String content;

	@Column
	private String author;

	@Column
	private LocalDateTime createTime;


}
