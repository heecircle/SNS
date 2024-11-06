package com.heewon.sns.follow.domain;

import com.heewon.sns.user.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Follow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private User follower;

	@JoinColumn
	@ManyToOne(fetch = FetchType.LAZY)
	private User followed;

	@Builder
	public Follow(User follower, User followed) {
		this.follower = follower;
		this.followed = followed;
	}
}
