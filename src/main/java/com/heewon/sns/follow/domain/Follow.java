package com.heewon.sns.follow.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {@Index(name = "idx_userId", columnList = "userId"),
	@Index(name = "idx_followerId", columnList = "followId")})
public class Follow {
	@EmbeddedId
	FollowId id;
}
