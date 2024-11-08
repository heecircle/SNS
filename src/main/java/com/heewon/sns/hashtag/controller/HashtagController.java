package com.heewon.sns.hashtag.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heewon.sns.feed.dto.FeedReadResponseDto;
import com.heewon.sns.hashtag.domain.TopHashtag;
import com.heewon.sns.hashtag.service.HashtagService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/hashtag")
public class HashtagController {

	private final HashtagService hashtagService;

	@GetMapping("/search")
	public List<FeedReadResponseDto> searchHashtag(String keyword, int page, int size, Long userId) {
		log.info("================== hashtag called keyword : " + keyword + " ===================");
		return hashtagService.findFeedByHashtag(keyword, userId, PageRequest.of(page, size));
	}

	@GetMapping("/top")
	public List<TopHashtag> topHashtag() {
		return hashtagService.getTopHashtag();
	}
}
