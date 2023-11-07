package com.api.board.controller;

import com.api.board.dto.BoardRequestDto;
import com.api.board.dto.BoardResponseDto;
import com.api.board.dto.SuccessResponseDto;
import com.api.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor // 생성자를 자동으로 생성해주는 기능을 제공한다.
// @RequiredArgsConstructor 어노테이션을 사용하면 해당되는 클래스의 final 필드에 대한 생성자가 자동으로 생성된다.
// 정리하자면, @RequiredArgsConstructor 를 사용하면 스프링 컴포넌트 (Service, Repository) 에 대한 의존성 주입 코드를
// 간단하게 유지하고 가독성을 향상 시킬수 있으며 변경사항 관리를 편하게 할 수 있다.
@RequestMapping("/api") // 지정된 URL로 오는 모든 요청을 받는다.
// 여기서는 /api/board 이기에 중복되는 api를 모두 일괄 처리 하였다.
public class BoardController {

    private final BoardService boardService;

    //전체 목록 조회
    @GetMapping("/board")
    public List<BoardResponseDto> getPosts () {
        // 전체 목록을 가져와야 하기 때문에 List를 사용하였다.
        // 또한 DB에서 조회한 결과를 가지고 와야하기 때문에 BoardResponseDto를 사용해 데이터를 가지고 온다.
        return boardService.getPosts();
    }

    // 게시글 작성
    @PostMapping("/board")
    public BoardResponseDto createPost(@RequestBody BoardRequestDto requestDto) {
        return boardService.createPost(requestDto);
        // 반환 타입을 BoardResponseDto 으로 지정한 이유는, 게시글을 성공적으로 DB에 등록 했을 때, 클라이언트로 전달할 데이터의 타입을 지정한 것이다.
        // @RequestBody 어노테이션을 사용하여 Body(==본문)에 담긴 데이터를 자동으로 BoardResponseDto 객체로 바꿀 수 있다.

    }

    // 특정 게시글 조회
    @GetMapping("/board/{id}")
    public BoardResponseDto getPost(@PathVariable("id") Long id) {
        return boardService.getPost(id);
        // @PathVariable은 URL 경로에서 지정된 파라미터 명으로 데이터 값을 가지고 오는 기능을 한다.
        // 여기서는 경로상에 {id}를 지정하여 클라이언트로부터 특정 게시글의 번호를 받아 올 수 있다.
        // @PathVariable Long id 이렇게 게시글 번호({id})의 타입과 파라미터 명을 지정하였다.
    }

    // 게시글 수정
    @PatchMapping("/board/{id}")
    public BoardResponseDto updatePost(@PathVariable("id") Long id, @RequestBody BoardRequestDto requestDto) throws Exception{
        return boardService.updatePost(id, requestDto);
        // 게시글을 업데이트하기 위해서, URL 상에서 id를 가지고 오고, Body에서 입력한 데이터를 가지고 온다.
        // 해당되는 게시글이 없을 경우를 대비해 "throws Exception" 을 붙여 예외 처리를 반드시 해준다.
    }

    // 게시글 삭제
    @DeleteMapping("/board/{id}")
    public SuccessResponseDto deletePost(@PathVariable("id") Long id, @RequestBody BoardRequestDto requestDto) throws Exception{
        // 게시글 삭제를 하면 리턴되는 값이 없기 때문에 앞서 만들어둔 SuccessResponseDto 안의 boolean을 사용해 삭제 여부를 확인한다.
        // 게시글을 삭제하기 위해서, URL 상에서 id를 가지고 오고, Body에서 입력한 데이터를 가지고 온다.
        // 해당되는 게시글이 없을 경우를 대비해 "throws Exception" 을 붙여 예외 처리를 반드시 해준다.
        return boardService.deletePost(id, requestDto);
    }

}