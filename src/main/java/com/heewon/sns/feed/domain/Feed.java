package com.heewon.sns.feed.domain;

import com.heewon.sns.common.BaseTimeEntity;
import com.heewon.sns.user.domain.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
 
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {@Index(name = "idx_feed_author", columnList = "author"),
	@Index(name = "idx_feed_search", columnList = "title, content")})
public class Feed extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String title;

	@Column(length = 255)
	private String content;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private User author;

	@Column
	private String imgUrl;

	@Builder
	Feed(String title, String content, User author, String imgUrl) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.imgUrl = imgUrl;
	}

}
