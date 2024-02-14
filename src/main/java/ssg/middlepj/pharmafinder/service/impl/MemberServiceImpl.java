package ssg.middlepj.pharmafinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssg.middlepj.pharmafinder.dao.MemberDao;
import ssg.middlepj.pharmafinder.dto.MemberDto;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberDao dao;

    @Override
    public boolean idcheck(String username) {
        int count = dao.idcheck(username);
        return count > 0;
    }

    @Override
    public boolean addmember(MemberDto mem) {
        int count = dao.addmember(mem);
        return count > 0;
    }

    @Override
    public boolean addstore(PharmacyDto store) {
        int count = dao.addstore(store);
        return count > 0;
    }

    @Override
    public boolean login(String username, String password) {

        return dao.login(username, password);
    }

    @Override
    public String findPassword(String username, String email) {
        // 여기서는 DAO를 통해 username과 email을 이용하여 비밀번호를 찾아 반환합니다.
        return dao.findPassword(username, email);
    }

    //이메일로 유저아이디 찾기
    @Override
    public String findUsernameByEmail(String email) {
        // 이메일을 통해 아이디를 조회하는 DAO 메서드 호출
        return dao.findUsernameByEmail(email);
    }
}