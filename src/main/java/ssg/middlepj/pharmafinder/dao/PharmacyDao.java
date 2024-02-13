package ssg.middlepj.pharmafinder.dao;

import ssg.middlepj.pharmafinder.dto.PaginationParam;
import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;

import java.util.List;

public interface PharmacyDao {
//    int countPharmacyList(PharmacyParam pharmacyParam);

//    List<PharmacyDto> selectPharmaciesByDB(PharmacyParam pharmacyParam);

    int countPharmacyList(PharmacyParam paginationParam);

    List<PharmacyDto> selectPharmaciesByDB(PharmacyParam paginationParam);

    PharmacyDto selectPharmacybyDB(PharmacyParam pharmacyParam);

    void insertPharmacy(PharmacyDto pharmacyDto);
}
