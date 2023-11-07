package com.api.board.dto;

import com.api.board.entity.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {
    // Request에서 전달받은 요청을 Repository와 Entity를 통해 처리한 결과를 반한다.
    // 전체 목록 조회 : id, title, contents, author, password, createdAt, modifiedAt
    // 게시글 조회 : id, title, contents, author
    // 게시글 작성, 게시글 수정 : id, title, contents, author, password
    // 게시글 삭제 : success, 삭제 후에는 반환할 데이터가 없기 때문에 success 라는 응답으로 대신 처리한다.

    private Long id;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // Board Entity를 받아서 BoardResponseDto 객체를 만들기 위한 생성자
    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.author = entity.getAuthor();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
    }
}
