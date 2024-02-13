package ssg.middlepj.pharmafinder.dao;

import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;
import ssg.middlepj.pharmafinder.dto.PharmacyResDto;

import java.util.List;

public interface PharmacyDao {
//    int countPharmacyList(PharmacyParam pharmacyParam);

//    List<PharmacyDto> selectPharmaciesByDB(PharmacyParam pharmacyParam);

    int countPharmacyList(PharmacyParam paginationParam);

    List<PharmacyResDto> selectPharmaciesByDB(PharmacyParam paginationParam);

    PharmacyDto selectPharmacybyDB(PharmacyParam pharmacyParam);

    void insertPharmacy(PharmacyDto pharmacyDto);
}
