package shop.koreait.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.koreait.dto.PaginationDTO;
import shop.koreait.dto.ProductDTO;
import shop.koreait.service.ProductService;

@Log4j2(topic = "PRODUCT_LOGGER")
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired private ProductService productService;

    @GetMapping("/list")
    public void get_product_list(
            PaginationDTO<ProductDTO> pagination,
            Model model
    ){
        productService.set_pagination_with_element(pagination);
        log.info(pagination);
        model.addAttribute("pagination", pagination);
    }

    @GetMapping("/detail/{no}")
    public String get_product_detail(
            @PathVariable("no") Integer productNo,
            Model model
    ){
        var product = productService.get_product_by_no(productNo);
        log.info("가져온 상품({}번): {}", productNo, product);
        model.addAttribute("product", product);
        return "product/detail";
    }
}
