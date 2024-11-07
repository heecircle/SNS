package com.heewon.sns.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.heewon.sns.hashtag.service.HashtagService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HashtagScheduler {

	private final HashtagService hashtagService;

	@Transactional
	@Scheduled(cron = "0 */10 * * * ?")
	public void top10() {
		hashtagService.getHashTag();
	}
}
