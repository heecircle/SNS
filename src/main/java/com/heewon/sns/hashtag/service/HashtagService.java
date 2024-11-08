package com.heewon.sns.hashtag.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.heewon.sns.feed.dto.FeedReadResponseDto;
import com.heewon.sns.hashtag.domain.TopHashtag;
import com.heewon.sns.hashtag.dto.HashtagCount;
import com.heewon.sns.hashtag.repository.HashtagRankRepository;
import com.heewon.sns.hashtag.repository.HashtagRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagService {

	private final HashtagRepository hashtagRepository;
	private final HashtagRankRepository hashtagRankRepository;

	public List<FeedReadResponseDto> findFeedByHashtag(String hashtag, Long userId, Pageable pageable) {
		return hashtagRepository.findByHashtag(hashtag, userId, pageable).toList();
	}

	public void getHashTag() {
		List<HashtagCount> list = hashtagRepository.top5(LocalDateTime.now().minusHours(1));

		Integer rank = 1;
		for (HashtagCount hashtagCount : list) {
			TopHashtag topHashtag = hashtagRankRepository.findByRanking(rank++);
			topHashtag.setText(hashtagCount.getText());
			System.out.println(topHashtag.toString());
			hashtagRankRepository.save(topHashtag);
		}
	}

	public List<TopHashtag> getTopHashtag() {
		System.out.println(hashtagRankRepository.findTopHashtag().toString());
		return hashtagRankRepository.findTopHashtag();
	}

}
