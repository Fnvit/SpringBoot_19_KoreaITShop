package shop.koreait.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductOptionDTO {
    private Integer no;
    private Integer productNo;
    private String name;
    private Integer price;
}
