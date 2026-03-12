package shop.koreait.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.koreait.dto.PaginationDTO;
import shop.koreait.dto.ProductDTO;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductDTO> selectProducts(); // 모든 상품목록을 가져오기
    Integer selectPaginationProductCount(PaginationDTO<ProductDTO> pagination); // 카테고리에 해당하는 상품 전체를 개수를 가져오기
    List<ProductDTO> selectPaginationProducts(PaginationDTO<ProductDTO> pagination); // 카테고리에 해당하는 상품 전체를 가져오기
    ProductDTO selectProductByNo(Integer no); // 하나의 상품을 가져오기
}









