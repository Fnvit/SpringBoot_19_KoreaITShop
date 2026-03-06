package shop.koreait.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.koreait.dto.ProductDTO;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDTO> selectProducts(); // 모든 상품목록을 가져오기
    ProductDTO selectProductByNo(Integer no); // 하나의 상품을 가져오기
}









