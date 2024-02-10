package ssg.middlepj.pharmafinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssg.middlepj.pharmafinder.dto.PharmacyProductQtyDto;
import ssg.middlepj.pharmafinder.dto.ProductDto;
import ssg.middlepj.pharmafinder.service.ProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseBody
    @GetMapping("/detail.do")
    public Map<String, Object> detail(Integer productId) {
        Map<String, Object> map = new HashMap<>();
        ProductDto product = productService.selectProductById(productId);
        List<PharmacyProductQtyDto> pharmaciesWithQty = productService.selectPharmacyProductQtyByProductId(productId);

        map.put("product", product);
        map.put("pharmaciesWithQty", pharmaciesWithQty);

        return map;
    }
}
