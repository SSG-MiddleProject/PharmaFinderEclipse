package ssg.middlepj.pharmafinder.service;

import java.util.List;

import ssg.middlepj.pharmafinder.dto.InventoryDto;
import ssg.middlepj.pharmafinder.dto.PharmaInventoryParam;

public interface PharmaInventoryService {
	List<InventoryDto> selectInventory(PharmaInventoryParam param);
}
