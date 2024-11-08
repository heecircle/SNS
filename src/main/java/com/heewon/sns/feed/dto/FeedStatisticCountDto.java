package com.heewon.sns.feed.dto;

import com.heewon.sns.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FeedStatisticCountDto {
	private User user;
	private Integer dateCount;
	private Integer dateCountAcc;

	FeedStatisticCountDto(User user, Integer dateCount, Long dateCountAcc) {
		this.user = user;
		this.dateCount = dateCount;
		this.dateCountAcc = dateCountAcc.intValue();
	}

	FeedStatisticCountDto(User user, Long dateCount, Long dateCountAcc) {
		this.user = user;
		this.dateCount = dateCount.intValue();
		this.dateCountAcc = dateCountAcc.intValue();
	}
	
}
