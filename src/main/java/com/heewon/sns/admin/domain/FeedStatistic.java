package com.heewon.sns.admin.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class FeedStatistic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Integer count;

	@Column
	private Integer accCount;

	@Column
	private LocalDate date;

	FeedStatistic(Long count, Long accCount) {
		this.count = count.intValue();
		this.accCount = accCount.intValue();
	}

	FeedStatistic(Integer count, Long accCount) {
		this.count = count;
		this.accCount = accCount.intValue();
	}

	FeedStatistic(Long count, Integer accCount) {
		this.count = count.intValue();
		this.accCount = accCount;
	}

	public void applyTime() {
		this.date = LocalDate.now();
	}
}
