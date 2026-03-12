package shop.koreait.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.koreait.dto.PaginationDTO;
import shop.koreait.dto.ProductDTO;
import shop.koreait.mapper.ProductMapper;

import java.util.List;

@Service
public class ProductService {
    @Autowired private ProductMapper productMapper;

    public void set_pagination_with_element(PaginationDTO<ProductDTO> pagination){
        // 1. DB에 존재하는 요소 중, 조건에 맞는 전체 요소 개수를 조회한다.
        Integer totalElementCount = productMapper.selectPaginationProductCount(pagination);
        // 상품이 존재한다면
        if(totalElementCount != 0){
            // 2. 가져와야 하는 실제 요소들을 조회한다.
            var products = productMapper.selectPaginationProducts(pagination);
            // 3. 조건에 맞는 총 개수를 설정함과 동시에 페이지네이션 내용도 전부 update
            pagination.setTotalElementCount(totalElementCount);
            pagination.setElements(products);
        }
    }
    /*
    
     */
    public ProductDTO get_product_by_no(Integer productNo){
        return productMapper.selectProductByNo(productNo);
    }
    
    
    
    
}
