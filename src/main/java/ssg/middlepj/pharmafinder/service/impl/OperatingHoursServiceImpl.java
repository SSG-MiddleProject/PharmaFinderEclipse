package ssg.middlepj.pharmafinder.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssg.middlepj.pharmafinder.dao.OperatingHoursDao;
import ssg.middlepj.pharmafinder.dto.OperatingHours;
import ssg.middlepj.pharmafinder.service.OperatingHoursService;

@Service
public class OperatingHoursServiceImpl implements OperatingHoursService {

	private final OperatingHoursDao operatingHoursDao;

    @Autowired
    public OperatingHoursServiceImpl(OperatingHoursDao operatingHoursDao) {
        this.operatingHoursDao = operatingHoursDao;
    }

    @Override
    public void processOperatingHours(Map<String, OperatingHours> operatingHours) {
        // 여기에서 비즈니스 로직을 구현합니다.
        // 예를 들어, 유효성 검사, 데이터 변환, 데이터베이스 저장 등
        operatingHoursDao.saveOperatingHours(operatingHours);
    }

}
