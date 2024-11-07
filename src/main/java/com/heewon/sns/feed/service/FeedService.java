package com.heewon.sns.feed.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import com.heewon.sns.common.util.AWSS3;
import com.heewon.sns.feed.domain.Feed;
import com.heewon.sns.feed.domain.FeedCheck;
import com.heewon.sns.feed.domain.FeedLike;
import com.heewon.sns.feed.domain.FeedLikeId;
import com.heewon.sns.feed.dto.FeedCreateRequestDto;
import com.heewon.sns.feed.dto.FeedReadResponseDto;
import com.heewon.sns.feed.repository.FeedCheckRepository;
import com.heewon.sns.feed.repository.FeedLikeRepository;
import com.heewon.sns.feed.repository.FeedRepository;
import com.heewon.sns.user.domain.User;
import com.heewon.sns.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedService {

	private final FeedRepository feedRepository;
	private final FeedCheckRepository checkRepository;
	private final UserRepository userRepository;
	private final FeedLikeRepository feedLikeRepository;

	private final AWSS3 s3Uploader = new AWSS3();

	public Feed writeFeed(FeedCreateRequestDto requestDto, MultipartFile image) throws Exception {
		String imgUrl = null;
		if (image != null) {
			imgUrl = s3Uploader.upload(image);
		}
		User user = userRepository.findById(requestDto.getAuthorId()).orElse(null);
		Feed feed = Feed.builder()
			.title(requestDto.getTitle())
			.content(requestDto.getContent())
			.author(user)
			.imgUrl(imgUrl).
			build();

		return feedRepository.save(feed);
	}

	public List<FeedReadResponseDto> readFeed(Long userId, Pageable pageable) throws Exception {
		if (!findFeedCheck(userId)) {
			throw new NotFoundException("호출 횟수 초과");
		}

		Page<FeedReadResponseDto> list = feedRepository.findFeedById(userId, pageable);

		if (list == null) {
			return new ArrayList<>();
		}
		User user =
			userRepository.findById(userId).orElse(null);
		checkRepository.save(FeedCheck.builder().user(user).build());
		return list.toList();
	}

	public boolean findFeedCheck(Long userId) { // 조회 이력 -> 호출 여부
		return checkRepository.findByUser_Id(userId) == null;
	}

	@Transactional
	public void createLike(Long userId, Long feedId) {
		Feed feed = feedRepository.findFeedById(feedId);
		User user = userRepository.findById(userId).orElse(null);
		if (feed == null) {
			throw new NotFoundException("존재하지 않는 피드입니다");
		}

		if (user == null) {
			throw new NotFoundException("존재하지 않는 사용자입니다.");
		}
		FeedLike feedLike = FeedLike.builder().id(new FeedLikeId(userId, feedId)).feed(feed).user(user).build();
		feedLikeRepository.save(feedLike);
	}
}
