package ssg.middlepj.pharmafinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssg.middlepj.pharmafinder.dto.Pagination;
import ssg.middlepj.pharmafinder.dto.PaginationParam;
import ssg.middlepj.pharmafinder.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search.do")
    public String getProductByEfcy(Model model, PaginationParam paginationParam) {
        model.addAttribute("products", productService.selectProducts(paginationParam));
        model.addAttribute("pagination", new Pagination(productService.selectProductsCnt(paginationParam), paginationParam));
        return "product/list";
    }

    @GetMapping("/search2.do")
    public String getProductByEfcy2(Model model, PaginationParam paginationParam) {
        model.addAttribute("test", "test");
        model.addAttribute("products", productService.selectProducts(paginationParam));
        model.addAttribute("pagination", new Pagination(productService.selectProductsCnt(paginationParam), paginationParam));
        return "product/list";
    }
}
