package ssg.middlepj.pharmafinder.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperatingHoursController {

	public String receiveOperatingHours(@RequestBody Map<String, OperatingHours> operatingHours) {
		// 클라이언트로부터 받은 데이터 처리
        // 예: 데이터베이스에 저장, 로그 출력 등
        System.out.println(operatingHours);
        
        return "Received successfully";
	}
}
