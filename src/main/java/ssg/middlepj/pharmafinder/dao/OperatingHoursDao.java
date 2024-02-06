package ssg.middlepj.pharmafinder.dao;

import java.util.Map;

import ssg.middlepj.pharmafinder.dto.OperatingHours;

public interface OperatingHoursDao {
	void saveOperatingHours(Map<String, OperatingHours> operatingHours);
}
