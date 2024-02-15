package ssg.middlepj.pharmafinder.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ssg.middlepj.pharmafinder.dao.PharmaOutDao;
import ssg.middlepj.pharmafinder.dto.PharmaDailyInOutSearchByPeriodParam;
import ssg.middlepj.pharmafinder.dto.PharmaOutDetailDto;
import ssg.middlepj.pharmafinder.dto.PharmaOutDto;
import ssg.middlepj.pharmafinder.dto.PharmaOutProductDto;

@Repository
public class PharmaOutDaoImpl implements PharmaOutDao{

	@Autowired
	private SqlSession session;

	private final String ns = "PharmaInOut.";

	@Override
	public int insertDailyOutput(PharmaOutDto dto) {
		return session.insert(ns + "insert-daily-output", dto);
	}

	@Override
	public int selectDailyOutputLastId() {
		return session.selectOne(ns + "select-daily-output-last-id");
	}

	@Override
	public int insertDailyOutputProduct(PharmaOutProductDto dto) {
		return session.insert(ns + "insert-daily-output-product", dto);
	}

	@Override
	public int updateStoreStockOutput(PharmaOutProductDto dto) {
		return session.update(ns + "update-store-stock-output", dto);
	}

	@Override
	public PharmaOutDto selectDailyOutputWithProducts(int id) {
		return session.selectOne(ns + "select-daily-output-with-products", id);
	}

	@Override
	public List<PharmaOutDto> selectDailyOutputs(PharmaDailyInOutSearchByPeriodParam param) {
		return session.selectList(ns + "select-daily-outputs", param);
	}

	@Override
	public List<PharmaOutDetailDto> selectDailyOutputProducts(int id) {
		return session.selectList(ns + "select-daily-output-products", id);
	}

	@Override
	public int deleteDailyOutput(int id) {
		return session.delete(ns + "delete-daily-output", id);
	}
}
