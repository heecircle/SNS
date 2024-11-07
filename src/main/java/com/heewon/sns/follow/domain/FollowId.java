package com.heewon.sns.follow.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class FollowId implements Serializable {

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private Long followId;

	@Override
	public int hashCode() {
		return Objects.hash(userId, followId);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FollowId) {
			FollowId other = (FollowId)obj;
			return this.userId.equals(other.userId) && this.followId.equals(other.followId);
		}
		return false;
	}
}
