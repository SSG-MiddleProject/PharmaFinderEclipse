package ssg.middlepj.pharmafinder.dao;

import ssg.middlepj.pharmafinder.dto.PaginationParam;
import ssg.middlepj.pharmafinder.dto.PharmacyProductQtyDto;
import ssg.middlepj.pharmafinder.dto.ProductDto;
import ssg.middlepj.pharmafinder.dto.ProductResDto;

import java.util.List;

public interface ProductDao {
    ProductDto selectProductById(Integer id);

    Integer selectProductsCnt(PaginationParam paginationParam);

    List<ProductResDto> selectProducts(PaginationParam paginationParam);

    List<PharmacyProductQtyDto> selectPharmacyProductQtyByProductId(Integer productId);
}
