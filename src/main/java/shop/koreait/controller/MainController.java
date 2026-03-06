package shop.koreait.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // 기본 template 경로를 사용하여 전체 경로를 만들어주는 함수
    private String create_template_path(String path){
        return "main/" + path;
    }

    @GetMapping("/")
    public String get_home() {
        return create_template_path("home");
    }

    @GetMapping("/cart")
    public String get_cart() {
        return create_template_path("cart");
    }

    @GetMapping("/order")
    public String get_order() {
        return create_template_path("order");
    }

    @GetMapping("/order/complete")
    public String get_order_complete() {
        return create_template_path("order-complete");
    }




}
