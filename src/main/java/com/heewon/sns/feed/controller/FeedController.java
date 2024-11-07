package com.heewon.sns.feed.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.heewon.sns.common.util.TimeTrace;
import com.heewon.sns.feed.domain.Feed;
import com.heewon.sns.feed.dto.FeedCreateRequestDto;
import com.heewon.sns.feed.dto.FeedLikeRequestDto;
import com.heewon.sns.feed.dto.FeedReadResponseDto;
import com.heewon.sns.feed.repository.FeedLikeRepository;
import com.heewon.sns.feed.service.FeedService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
	private final FeedService feedService;
	private final FeedLikeRepository feedLikeRepository;

	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Feed createFeed(@RequestPart(name = "requestDto") FeedCreateRequestDto requestDto,
		@RequestPart(name = "file") MultipartFile file) {
		try {
			System.out.println(requestDto.getHashtagString());
			return feedService.writeFeed(requestDto, file);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FeedReadResponseDto> listFeed(@RequestParam int page, @RequestParam int size,
		@RequestParam Long userId) {
		try {
			return feedService.readFeed(userId,
				PageRequest.of(page, size));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ArrayList<>();
	}

	@PostMapping(value = "/like")
	public void like(@RequestBody FeedLikeRequestDto requestDto) {
		feedService.createLike(requestDto.getUserId(), requestDto.getFeedId());
	}

<<<<<<< Updated upstream
=======
	@TimeTrace
	@GetMapping(value = "/search")
	public List<FeedReadResponseDto> searchFeed(@RequestParam(required = false) String keyword,
		@RequestParam Long userId,
		@RequestParam int page,
		@RequestParam int size) throws Exception {
		log.info("=================" + keyword + " search called================");
		if (keyword == null)
			throw new NotFoundException("검색어를 입력하세요");
		return feedService.searchFeed(userId, keyword, PageRequest.of(page, size));
	}

>>>>>>> Stashed changes
}
