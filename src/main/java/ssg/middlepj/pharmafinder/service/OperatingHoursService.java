package ssg.middlepj.pharmafinder.service;

import java.util.Map;

import ssg.middlepj.pharmafinder.dto.OperatingHours;

public interface OperatingHoursService {
	void processOperatingHours(Map<String, OperatingHours> operatingHours);
}
