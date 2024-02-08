package ssg.middlepj.pharmafinder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssg.middlepj.pharmafinder.dao.ProductDao;
import ssg.middlepj.pharmafinder.dto.PaginationParam;
import ssg.middlepj.pharmafinder.dto.PharmacyProductQtyDto;
import ssg.middlepj.pharmafinder.dto.ProductDto;
import ssg.middlepj.pharmafinder.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public ProductDto selectProductById(Integer id) {
        return productDao.selectProductById(id);
    }

    @Override
    public Integer selectProductsCnt(PaginationParam paginationParam) {
        return productDao.selectProductsCnt(paginationParam);
    }

    @Override
    public List<ProductDto> selectProducts(PaginationParam paginationParam) {
        return productDao.selectProducts(paginationParam);
    }

    @Override
    public List<PharmacyProductQtyDto> selectPharmacyProductQtyByProductId(Integer productId) {
        return productDao.selectPharmacyProductQtyByProductId(productId);
    }
}
