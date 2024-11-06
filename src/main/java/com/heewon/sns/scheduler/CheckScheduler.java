package com.heewon.sns.scheduler;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.heewon.sns.feed.repository.FeedCheckRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CheckScheduler {

	private final FeedCheckRepository feedCheckRepository;

	@Transactional
	@Scheduled(cron = "0 * * * * *")
	public void checkMinute() {
		System.out.println(new Date().toString());
		feedCheckRepository.deleteByCheckTimeBefore(LocalDateTime.now().minusMinutes(5));
	}
}
