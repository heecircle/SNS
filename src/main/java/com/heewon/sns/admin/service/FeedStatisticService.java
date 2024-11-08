package com.heewon.sns.admin.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.heewon.sns.admin.domain.FeedStatistic;
import com.heewon.sns.admin.domain.FeedStatisticUser;
import com.heewon.sns.admin.dto.AdminStatisticResponseDto;
import com.heewon.sns.admin.repository.FeedStatisticRepository;
import com.heewon.sns.admin.repository.FeedStatisticUserRepository;
import com.heewon.sns.feed.dto.FeedStatisticCountDto;
import com.heewon.sns.feed.repository.FeedRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedStatisticService {

	private final FeedRepository feedRepository;
	private final FeedStatisticUserRepository feedStatisticUserRepository;
	private final FeedStatisticRepository statisticRepository;
	private final FeedStatisticRepository feedStatisticRepository;

	public void makeStatistic_1() {

		List<FeedStatisticCountDto> list = feedRepository.findFeedStatisticsByDateRange(
			LocalDateTime.now().minusDays(1),
			LocalDateTime.now());

		List<FeedStatisticUser> feedStatisticUsers = list.stream().map(
			dto -> FeedStatisticUser.builder()
				.feedCountOneDay(dto.getDateCount())
				.feedCountAcc(dto.getDateCountAcc())
				.user(dto.getUser())
				.build()
		).toList();
		int idx = 10;
		for (FeedStatisticUser feedStatisticUser : feedStatisticUsers) {
			if (idx == 0) {
				break;
			}
			idx--;
			System.out.println(feedStatisticUser);
		}
		feedStatisticUserRepository.saveAll(feedStatisticUsers);
	}

	public List<AdminStatisticResponseDto> getStatistics() {
		List<FeedStatisticUser> list = feedStatisticUserRepository.findByDate(LocalDate.now());
		return list.stream().map(dto -> AdminStatisticResponseDto.builder()
			.userId(dto.getUser().getId())
			.userNickname(dto.getUser().getNickname())
			.accFeedCount(dto.getFeedCountAcc())
			.dateFeedCount(dto.getFeedCountOneDay())
			.build()
		).toList();
	}

	public void makeStatistic() {
		List<FeedStatistic> list = feedRepository.getLatestFeedStatistic(LocalDateTime.now().minusDays(1),
			LocalDateTime.now());
		for (FeedStatistic feedStatistic : list) {
			feedStatistic.applyTime();
		}
		feedStatisticRepository.saveAll(list);
	}

	public List<FeedStatistic> getLatestFeedStatistic() {
		return feedStatisticRepository.findFeedStatisticByDate(LocalDate.now());
	}

}
