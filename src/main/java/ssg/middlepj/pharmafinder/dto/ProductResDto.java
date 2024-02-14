package ssg.middlepj.pharmafinder.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductResDto extends ProductDto {
    private boolean isBookmark;
}
