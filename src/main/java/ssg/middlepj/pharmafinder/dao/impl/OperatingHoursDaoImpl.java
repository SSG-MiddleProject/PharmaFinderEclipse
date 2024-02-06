package ssg.middlepj.pharmafinder.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import ssg.middlepj.pharmafinder.dao.OperatingHoursDao;
import ssg.middlepj.pharmafinder.dto.OperatingHours;

@Repository
public class OperatingHoursDaoImpl implements OperatingHoursDao {

	@Override
	public void saveOperatingHours(Map<String, OperatingHours> operatingHours) {
		// 데이터베이스에 저장하는 로직 구현
		
	}
	
	
}
