package com.api.board.repository;

import com.api.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // @Repository 어노테이션을 사용한 인터페이스는 데이터 엑세스 레이어를 추상화하고 캡슐화 한다.
    // 주로 DB와 상호 작용하는데 사용된다.
    // @Repository는 주로 interface에 자주 사용되지만 꼭 interface에만 제한되는 것은 아니다.
    // 일반적으로 Spring Data JPA에서는 interface를 사용한다.

    // JpaRepository<Board, Long>
    // JpaRepository 인터페이스를 확장하여 DB에 대한 기본적인 CRUD 작업을 자동으로 처리해준다.
    // Board는 엔티티 클래스의 유형을 나타낸다.
    // Long은 앞서 정의한 엔티티 클래스의 기본 키 (Primary Key)의 타입을 나타낸다.

    List<Board> findAllByOrderByModifiedAtDesc();
}