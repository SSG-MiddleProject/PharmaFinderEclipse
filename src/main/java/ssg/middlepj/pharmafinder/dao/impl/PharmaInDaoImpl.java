package ssg.middlepj.pharmafinder.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.middlepj.pharmafinder.dao.PharmaInDao;
import ssg.middlepj.pharmafinder.dto.PharmaDailyInOutSearchByPeriodParam;
import ssg.middlepj.pharmafinder.dto.PharmaInDetailDto;
import ssg.middlepj.pharmafinder.dto.PharmaInDto;
import ssg.middlepj.pharmafinder.dto.PharmaInProductDto;

@Repository
public class PharmaInDaoImpl implements PharmaInDao {

	@Autowired
	private SqlSession session;

	private final String ns = "PharmaInOut.";

	@Override
	public int insertDailyInput(PharmaInDto dto) {
		return session.insert(ns + "insert-daily-input", dto);
	}

	@Override
	public int selectDailyInputLastId() {
		return session.selectOne(ns + "select-daily-input-last-id");
	}

	@Override
	public int insertDailyInputProduct(PharmaInProductDto dto) {
		return session.insert(ns + "insert-daily-input-product", dto);
	}

	@Override
	public int updateStoreStockInput(PharmaInProductDto dto) {
		return session.update(ns + "update-store-stock-input", dto);
	}

	@Override
	public PharmaInDto selectDailyInputWithProducts(int id) {
		return session.selectOne(ns + "select-daily-input-with-products", id);
	}

	@Override
	public List<PharmaInDto> selectDailyInputs(PharmaDailyInOutSearchByPeriodParam param) {
		return session.selectList(ns + "select-daily-inputs", param);
	}

	@Override
	public List<PharmaInDetailDto> selectDailyInputProducts(int id) {
		return session.selectList(ns + "select-daily-input-products", id);
	}

	@Override
	public int deleteDailyInput(int id) {
		return session.delete(ns + "delete-daily-input", id);
	}

}
