# RestApiBoardPractice

1. 개발환경
  - OpenJDK 17.0.2
  - IntelliJ IDEA Community
  - Spring Boot 3.1.5
  - Postman
  - VSC

2. Dependencies
   - Lombok
   - Spring Data JPA
   - H2 Database
   - Spring Web
   - Spring Secucity
  
3. 기능 정의
  1) 전체 목록 조회
     - 게시글 번호, 제목, 내용, 작성자, 작성일, 수정일 조회
     - 작성 날짜 기준 내림차순으로 정렬
    
  2) 게시글 작성
     - 제목, 작성자, 비밀번호, 내용, 작성일을 저장
      
  3) 특정 게시글 조회
     - 특정 게시글의 번호로 제목, 내용, 작성자, 작성일, 수정일 조회
    
  4) 게시글 수정
     - 수정을 요청할 때 수정할 데이터와 비밀번호를 같이 보내 서버에서 비밀번호 검증한다.
     - 비밀번호가 일치할 경우 게시글을 수정하고 그렇지 않은 경우 롤백한다.
    
  5) 게시글 삭제
     - 삭제를 요청할 때는 비밀번호를 같이 보내서 서버에서 비밀번호를 확인한다.
     - 비밀번호가 일치할 경우 게시글을 삭제하고 그렇지 않은 경우 롤백한다.
    

4. API 리스트
   순서     기능                Method       URL
   1        전체 목록 조회      GET          /api/board
   2        특정 게시글 조회    GET          /api/board/{id}
   3        게시글 작성         POST         /api/board
   4        게시글 수정         PATCH        /api/board/{id}
   5        게시글 삭제         DELETE       /api/board/{id}


4-1. 게시글 작성 REQUEST 요청
{
"title" : "제목",
"content" : "내용",
"author" : "작성자",
"password" : "비밀번호"
}

4-2. 게시글 작성 RESPONSE 요청
{
    "id" : 1,
    "title" : "제목",
    "content" : "내용",
    "author" : "작성자",
    "createdAt" : "작성일시",
    "modifiedAt : "수정일시"
}

5. FRONTEND API TEST
   - index.html
   - write.html
     
5-1. FRONTEND 기능 정의
   1) 전체 게시글 조회
   2) 게시글 작성

---
23.11.07
- REST API를 활용하여 게시판 CRUD 구현

23.11.11
- 간단한 View 페이지를 만들어서 전체 게시글 조회 및 게시글 작성 기능 구현

---
블로그 주소
https://blog.naver.com/cropruch/223258637452
