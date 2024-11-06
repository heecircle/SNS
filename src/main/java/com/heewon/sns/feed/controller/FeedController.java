package com.heewon.sns.feed.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.heewon.sns.feed.domain.Feed;
import com.heewon.sns.feed.dto.FeedCreateRequestDto;
import com.heewon.sns.feed.dto.FeedReadResponseDto;
import com.heewon.sns.feed.service.FeedService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {
	private final FeedService feedService;

	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Feed createFeed(@RequestPart(name = "requestDto") FeedCreateRequestDto requestDto,
		@RequestPart(name = "file") MultipartFile file) {
		try {
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
}
