package com.heewon.sns.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.heewon.sns.admin.service.FeedStatisticService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StatisticScheduler {

	private final FeedStatisticService feedStatisticService;

	@Transactional
	@Scheduled(cron = "0 0 0 * * *")
	public void makeStatistic() {
		feedStatisticService.makeStatistic();
	}

}
