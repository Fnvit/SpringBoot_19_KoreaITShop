package shop.koreait.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class ProductDTO {
    private Integer no;     // 상품고유번호
    private String name;    // 상품명
    private Integer price;  // 가격
    private String detail;  // 상세 정보
    private LocalDateTime uploadedAt; // 등록일
    private CategoryDTO category; // 이 상품의 카테고리
    private Integer rating; // 별점
    private List<ProductImageDTO> images; // 상품의 썸네일 이미지들
    private List<ProductOptionDTO> options; // 상품의 옵션들
}
