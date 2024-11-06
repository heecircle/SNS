package com.heewon.sns.feed.domain.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.heewon.sns.feed.domain.Feed;
import com.heewon.sns.feed.domain.repository.FeedRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedService {

	private final FeedRepository feedRepository;

	Feed writeFeed(Feed feed) {

	}
}
