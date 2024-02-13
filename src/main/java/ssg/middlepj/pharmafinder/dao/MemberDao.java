package ssg.middlepj.pharmafinder.dao;

import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;

public interface MemberDao {
    // 아이디 중복체크
    int idcheck(String username);

    // 회원가입
    int addmember(MemberDto mem);

    // 로그인
    boolean login(String userString, String password);// 인자를 두 개로 변경

    // 비밀번호찾기
    String findPassword(String username, String email); // 비밀번호 찾기 메서드 추가

    // 아이디찾기
    String findUsernameByEmail(String email);

    /* 회원 추가(회원가입) */
    int addstore(PharmacyDto store);    // 약국 추가정보
}