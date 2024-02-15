package ssg.middlepj.pharmafinder.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.middlepj.pharmafinder.dao.PharmaInventoryDao;
import ssg.middlepj.pharmafinder.dto.InventoryStockParam;

@Repository
public class PharmaInventoryDaoImpl implements PharmaInventoryDao {

	@Autowired
	private SqlSession session;

	private final String ns = "PharmaInventory.";

	@Override
	public int selectcurrentStockCnt(InventoryStockParam param) {
		return session.selectOne(ns + "select-current-stock-cnt",param);
	}

	@Override
	public int selectInputCnt(InventoryStockParam param) {
		return session.selectOne(ns + "select-input-cnt", param);
	}

	@Override
	public int selectOutputCnt(InventoryStockParam param) {
		return session.selectOne(ns + "select-output-cnt", param);
	}

}
