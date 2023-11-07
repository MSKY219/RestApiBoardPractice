package com.api.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
// @MappedSuperCalss를 표시한 클래스는 데이터 베이스 테이블과 매필되지 않지만
// 상속 받는 하위 클래스에서 모든 속성과 매핑 정보를 공유하게 된다.
@EntityListeners(AuditingEntityListener.class)
// 스프링 Data JSP에서 제공하는 내장 엔티티 리스너 중 하나로,
// 주로 엔티티의 생성일자와 수정일자를 자동으로 관리하기 위해 사용한다.
public class TImeStamp {

    @CreatedDate
    private LocalDateTime createdAt;

    @Setter // 수정일자는 수정 시에만 적용되야 해서 별도의 Setter를 설정해준다.
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    // @Setter를 풀어쓰면 아래와 같다.
    // public void setModifiedAt (LocalDateTime modifiedAt) {
    //    this.modifiedAt = modifiedAt;
    //}
}
