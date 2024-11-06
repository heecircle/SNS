package com.heewon.sns.common;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

public class BaseTimeEntity {

	@CreationTimestamp
	LocalDateTime createdAt;
}
