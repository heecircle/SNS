package com.heewon.sns.admin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heewon.sns.admin.domain.FeedStatistic;
import com.heewon.sns.admin.dto.AdminStatisticResponseDto;
import com.heewon.sns.admin.service.FeedStatisticService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class FeedStatisticController {

	private final FeedStatisticService feedStatisticService;

	@GetMapping("/user/statistic")
	public List<AdminStatisticResponseDto> getFeedStatisticService() {
		return feedStatisticService.getStatistics();
	}

	@GetMapping("/statistic")
	public List<FeedStatistic> getFeedStatistic() {
		return feedStatisticService.getLatestFeedStatistic();
	}

}
