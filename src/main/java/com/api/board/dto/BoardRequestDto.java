package com.api.board.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    // 게시글 작성, 수정, 삭제 시 들어오는 request 요청을 받아 요청하는 데이터를 전달한다.
    // 게시글 작성 및 수정 : title, content, author, password
    // 게시글 삭제 : password

    private String title;
    private String contents;
    private String author;
    private String password;

}
