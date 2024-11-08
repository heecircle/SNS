package com.heewon.sns.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AdminStatisticResponseDto {

	Long userId;
	String userNickname;
	Integer dateFeedCount;
	Integer accFeedCount;

}
