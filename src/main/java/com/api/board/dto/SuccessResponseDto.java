package com.api.board.dto;

import lombok.Getter;

@Getter // 삭제 시에 만약 406 에러가 발생한다면 @Getter가 빠진 경우이다.
public class SuccessResponseDto {
    // 게시글 삭제 : success, 삭제 후에는 반환할 데이터가 없기 때문에 success 라는 응답으로 대신 처리한다.
    private boolean success;

    // 게시글 삭제 삭제 성공 여부를 true로 전달하기 위해 사용.
    // false는 Service에서 예외처리를 한다.
    public SuccessResponseDto(boolean success) {this.success = success;}
}