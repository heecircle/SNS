package com.heewon.sns.admin.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heewon.sns.admin.domain.FeedStatistic;

public interface FeedStatisticRepository extends JpaRepository<FeedStatistic, Long> {

	List<FeedStatistic> findFeedStatisticByDate(LocalDate date);
}
