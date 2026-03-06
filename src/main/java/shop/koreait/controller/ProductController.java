package shop.koreait.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2(topic = "PRODUCT_LOGGER")
@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/list")
    public void get_product_list(){

    }

    @GetMapping("/detail")
    public void get_product_detail(){

    }
}
