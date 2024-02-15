package ssg.middlepj.pharmafinder.dao;

import ssg.middlepj.pharmafinder.dto.InventoryStockParam;

public interface PharmaInventoryDao {
	int selectcurrentStockCnt(InventoryStockParam param);
	int selectInputCnt(InventoryStockParam param);
	int selectOutputCnt(InventoryStockParam param);
}
