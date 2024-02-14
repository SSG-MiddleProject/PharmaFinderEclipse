package ssg.middlepj.pharmafinder.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PharmacyResDto extends PharmacyDto {
    private boolean isBookmark;
}
