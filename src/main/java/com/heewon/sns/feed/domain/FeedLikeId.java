package com.heewon.sns.feed.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FeedLikeId implements Serializable {

	@Column(name = "feed_id")
	Long feedId;

	@Column(name = "user_id")
	Long userId;

	@Override
	public int hashCode() {
		return Objects.hash(feedId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof FeedLikeId) {
			FeedLikeId other = (FeedLikeId)obj;
			return feedId.equals(other.feedId) && userId.equals(other.userId);
		}
		return false;
	}
}
