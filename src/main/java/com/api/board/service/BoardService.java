package com.api.board.service;

import com.api.board.dto.BoardRequestDto;
import com.api.board.dto.BoardResponseDto;
import com.api.board.dto.SuccessResponseDto;
import com.api.board.entity.Board;
import com.api.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // BoardRepository DI 하기 위해 사용
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    //전체 목록 조회
    public List<BoardResponseDto> getPosts() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
        // Repository 에서 데이터를 검색한다.
        // .findAllByOrderByModifiedAtDesc 메서드를 사용해 (수정된 날짜를 기준으로 내림차순 순으로 전체 조회)
        // .steam() : 검색 결과를 스트림으로 변환하고 데이터를 연속적으로 처리할 수 있게 해준다.
        // .map(BoardResponseDto::new) : Board 엔티티를 BoardResponseDto로 변환하여
        // 스트림을 BoardResponseDto 객체의 스트림으로 변환하는 역할을 한다.
        // collection의 map과 유사한 역할을 하나 같지는 않다.
        // :: new 는 Java 8에서 도입된 메서드 참조 표기법이다.
        // .toList() : 스트림의 요소를 리스트로 변환하여 최종 결과를 반환한다.
        // 이로써 데이터베이스에서 검색한 내림차순으로 정렬된 데이터를 BoardResponseDto로 변환한 후 리스트로 반환한다.
        // 데이터를 검색하고 DTO로 변환한 결과를 리스트로 반환한다.


    }

    // 게시글 작성
    @Transactional
    public BoardResponseDto createPost(BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        // Board 생성자를 통해 클라이언트로부터 들어온 BoardRequestDto 객체를 Board타입으로 바꿔준다.
        boardRepository.save(board); // 별도로 생성하지 않은 save 메서드
        // Spring Data JPA가 자동으로 생성한 save( ) 메서드를 통해 Board 엔티티를 DB에 저장하게 된다.
        return new BoardResponseDto(board);
        // 게시글 작성이 성공적으로 완료 된 후, 새로 생성된 Board 엔티티를 BoardResponseDto 객체로 변환하여 클라이언트로 반환한다.
        // 컨트롤러에서 넘어온 데이터 타입이 BoardResponseDto 이기 때문에 리턴할때도 같은 타입으로 넘어가야 한다.
    }   // return new BoardResponseDto(board);는 생성자가 아니라 객체를 생성하는 코드이다.

    // 특정 게시글 조회
    @Transactional
    public BoardResponseDto getPost(Long id) {
        return boardRepository.findById(id).map(BoardResponseDto::new).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        // findById()는 save() 메서드와 같이 Spring Data JPA가 자동으로 생성하는 메서드 중 하나이다.
        // 특정한 id 값으로 데이터를 찾을때 사용한다.
        // .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다.")):
        // 입력한 id로 해당되는 게시글을 찾을 수 없을 경우 예외 처리를 하기 위해서 작성되었다.
        // boardRepository에서 findById() 메서드를 찾아 전달된 id로 해당되는 게시글을 찾은 다음,
        // Board Entity를 BoardResponseDto 로 변환하여 클라이언트로 반환한다. 이 때, 일치하는 게시글이 없으면 게시글이 존재하지 않습니다. 를 반환한다.
    }

    // 게시글 수정
    @Transactional // 만약 수정이 제대로 작동하지 않는다면 @Transactional이 빠졌을수도 있다.
    public BoardResponseDto updatePost(Long id, BoardRequestDto requestDto) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        // 클라이언트에서 전달받은 id를 통해 DB에서 해당되는 게시글을 조회한다. 만야 해당되는 게시글이 없을 경우 예외처리.
        if (!requestDto.getPassword().equals(board.getPassword())) {
            // 만약 클라이언트에서 가지고온 requestDto의 비밀번호가 DB에 저장되어 있는 해당하는 게시글의 비밀번호와 일치하지 않는다면,
            // .equals() : 문자열 비교
            // == : 객체의 주소값 비교
            throw new Exception("비밀번호가 일치하지 않습니다.");
        } else {
            board.update(requestDto);
            return new BoardResponseDto(board);
        }
        // 비밀번호를 확인해서 일치할 경우에만 수정을 하고 불일치 할 경우, 예외처리를 한다.
    }


    // 게시글 삭제
    @Transactional
    public SuccessResponseDto deletePost(Long id, BoardRequestDto requestDto) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        // 클라이언트에서 전달받은 id를 통해 DB에서 해당되는 게시글을 조회한다. 만야 해당되는 게시글이 없을 경우 예외처리.

        if (!requestDto.getPassword().equals(board.getPassword())) {
            // 만약 클라이언트에서 가지고온 requestDto의 비밀번호가 DB에 저장되어 있는 해당하는 게시글의 비밀번호와 일치하지 않는다면,
            throw new Exception("비밀번호가 일치하지 않습니다.");
        } else {
            boardRepository.deleteById(id);
            return new SuccessResponseDto(true);
        }
        // 비밀번호를 확인해서 일치할 경우에만 삭제를 하고 불일치 할 경우, 예외처리를 한다.
    }
}
