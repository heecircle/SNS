package com.heewon.sns.hashtag.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TopHashtag {
	@Id
	private Long id;

	@Column
	private Integer ranking;

	@Setter
	@Column(nullable = false)
	private String text;
}
