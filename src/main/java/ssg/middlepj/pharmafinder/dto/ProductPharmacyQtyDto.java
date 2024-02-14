package ssg.middlepj.pharmafinder.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductPharmacyQtyDto extends ProductDto {
    private Integer qty;
    private Integer price;
}
