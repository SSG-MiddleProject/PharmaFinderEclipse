package ssg.middlepj.pharmafinder.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PharmacyExtDto implements Serializable {
    private List<PharmacyDto> items;
    private int allCount;


}
