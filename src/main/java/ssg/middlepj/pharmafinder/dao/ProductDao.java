package ssg.middlepj.pharmafinder.dao;

import ssg.middlepj.pharmafinder.dto.PaginationParam;
import ssg.middlepj.pharmafinder.dto.ProductDto;

import java.util.List;

public interface ProductDao {
    ProductDto selectProductById(Integer id);

    Integer selectProductsCnt(PaginationParam paginationParam);

    List<ProductDto> selectProducts(PaginationParam paginationParam);
}
