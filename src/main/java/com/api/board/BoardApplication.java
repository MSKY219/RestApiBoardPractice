package com.api.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
// @EnableJpaAuditing은 생성일자, 수정일자와 같이 Spring Data JPA 에서 시간에 대해 자동으로 값을 넣어주는 기능이다.
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}
