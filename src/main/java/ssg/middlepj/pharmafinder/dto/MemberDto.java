package ssg.middlepj.pharmafinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MemberDto {
    private int id;//유저의 고유번호
    private int storeId;//약국번호
    private String username;// 유저의 id
    private String email;
    private String password;
    private int state;// 0:비활성화 1:활성화
    private int roll; // 0:약국관리자 1:약국일반 2:고객일반
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public MemberDto() {

    }
}