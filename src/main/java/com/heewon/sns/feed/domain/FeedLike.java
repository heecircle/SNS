package com.heewon.sns.feed.domain;

import com.heewon.sns.user.domain.User;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedLike {

	@EmbeddedId
	FeedLikeId id;

	@MapsId("feedId")
	@JoinColumn(name = "feed_id")
	@ManyToOne(fetch = FetchType.LAZY)
	Feed feed;

	@MapsId("userId")
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	User user;

}
