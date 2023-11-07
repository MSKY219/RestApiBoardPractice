package com.api.board.entity;

import com.api.board.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity // JPA가 관리하는 클래스를 지정할 때 사용한다.
@NoArgsConstructor
public class Board extends TImeStamp {

    @Id // 기본키 설정 Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) // id 자동 증가를 의미한다. 오라클은 SEQUENCE, MySQL은 IDENTITY, AUTO는 자동 선택
    private Long id;

    @Column(nullable = false) // 객체 필드를 테이블 컬럼에 매핑, nullable은 null의 허용 여부를 결정한다.
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

    // 게시글 작성 시, 클라이언트로부터 넘어온 BoardRequestDto 객체를 Board 타입으로 바꿔준다.
    public Board(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
    }

    // 게시글 수정 시, 클라이언트로부터 넘어온 BoardRequestDto 객체를 Board 타입으로 바꿔준다.
    // 여기에 void가 적용된 이유는, 업데이트를 위해 사용되고 어떠한 값도 반환하지 않기 때문이다.
    public void update(BoardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.setModifiedAt(LocalDateTime.now());
    }
}