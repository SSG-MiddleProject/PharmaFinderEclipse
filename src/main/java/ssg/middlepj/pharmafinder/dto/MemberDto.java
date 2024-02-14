package ssg.middlepj.pharmafinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MemberDto {
    private int id;
    private int storeId;
    private String username;
    private String email;
    private String password;
    private int state;
    private int roll; // 0:약국관리자 1:약국일반 2:고객일반
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public MemberDto() {

    }
}