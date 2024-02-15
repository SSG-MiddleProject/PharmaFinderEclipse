package ssg.middlepj.pharmafinder.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
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
	public boolean emailcheck(String email) {
		int count = dao.emailcheck(email);
		return count > 0;
	}

	@Override
	public boolean addmember(MemberDto mem) {
		int count = dao.addmember(mem);
		return count > 0;
	}

	@Override
	public boolean addpharmacy(PharmacyDto pharmacy) {
		int count = dao.addpharmacy(pharmacy);
		return count > 0;
	}

	@Override
	public MemberDto login(String username, String password) {
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("username", username);
		paramMap.put("password", password);
		// DAO의 수정된 login 메서드 호출
		return dao.login(paramMap);
	}

	// 이메일로 유저아이디 찾기
	@Override
	public String findUsernameByEmail(String email) {
		// 이메일을 통해 아이디를 조회하는 DAO 메서드 호출
		return dao.findUsernameByEmail(email);
	}

	// 이메일과 유저 로그인id로 비밀번호 찾기
	@Override
	public String findPassword(String username, String email) throws NoSuchAlgorithmException {
		
		// 아이디와 이메일을 기준으로 임시 비밀번호 생성
		String temporaryPassword = generateTemporaryPassword();

		// 임시 비밀번호를 SHA-256으로 암호화
		String encryptedTemporaryPassword = encryptStringBySHA256(temporaryPassword);

		// 데이터베이스에 암호화된 임시 비밀번호를 업데이트
		boolean updateResult = dao.updateTemporaryPassword(username, email, encryptedTemporaryPassword);

		if (updateResult) {
			// 업데이트 성공 시, 임시 비밀번호를 사용자 이메일로 전송
			sendTemporaryPasswordEmail(email, temporaryPassword);
			return temporaryPassword;
		}

		// 업데이트 실패 시, 예외발생 시
		return null;
	}

	// 입력 문자열을 SHA-256으로 암호화하여 해시 값을 반환하는 메서드
	private String encryptStringBySHA256(String text) throws NoSuchAlgorithmException {
		
		// SHA-256 알고리즘의 인스턴스 생성
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		// 입력 문자열의 바이트 배열을 업데이트하여 해시 값을 계산
		md.update(text.getBytes());
		// 계산된 해시 값을 16진수 문자열로 변환하여 반환
		return bytesToHex(md.digest());
	}

	// 바이트 배열을 16진수 문자열로 변환하는 메서드
	private String bytesToHex(byte[] bytes) {
		
		StringBuilder builder = new StringBuilder();
		for (byte b : bytes) {
			// 바이트 값을 16진수 문자열로 변환하여 StringBuilder에 추가
			builder.append(String.format("%02x", b));
		}
		// StringBuilder의 내용을 문자열로 반환
		return builder.toString();
	}

	private String generateTemporaryPassword() {
		// 임시 비밀번호 생성 로직 구현 (예: 랜덤 문자열 생성)
		return RandomStringUtils.randomAlphanumeric(10);
	}

	private void sendTemporaryPasswordEmail(String email, String temporaryPassword) {
		// 임시 비밀번호를 포함하는 이메일 전송 로직 구현
		// 이메일 전송 기능에 대한 구현은 프로젝트의 이메일 전송 방식에 따라 다를 수 있음
	}
	
	@Override
    public boolean updatePasswordWithTemporary(String username, String temporaryPassword, String newPassword) throws NoSuchAlgorithmException {
        // 임시 비밀번호 암호화
        String encryptedTempPassword = encryptStringBySHA256(temporaryPassword);
        // 새로운 비밀번호 암호화
        String encryptedNewPassword = encryptStringBySHA256(newPassword);

        return dao.updatePasswordWithTemporary(username, encryptedTempPassword, encryptedNewPassword);
    }

	@Override
	public boolean updateMember(MemberDto member) throws NoSuchAlgorithmException {
		// 필요한 경우, 비밀번호 암호화 등의 추가 처리를 여기에 구현
	    if (member.getPassword() != null && !member.getPassword().isEmpty()) {
	        String encryptedPassword = encryptStringBySHA256(member.getPassword());
	        member.setPassword(encryptedPassword);
	    }
	    
	    // DAO를 사용하여 데이터베이스 업데이트 시도
	    int updateCount = dao.updateMember(member);
	    
	    // 업데이트된 레코드 수를 통해 성공 여부 반환
	    return updateCount > 0;
	}

}