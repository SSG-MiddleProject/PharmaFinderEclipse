package ssg.middlepj.pharmafinder.dao;

import ssg.middlepj.pharmafinder.dto.PharmacyDto;
import ssg.middlepj.pharmafinder.dto.PharmacyParam;

import java.util.List;

public interface PharmacyDao {
    int countPharmacyList(PharmacyParam pharmacyParam);

    List<PharmacyDto> selectPharmaciesByDB(PharmacyParam pharmacyParam);

    PharmacyDto selectPharmacybyDB(PharmacyParam pharmacyParam);
}
