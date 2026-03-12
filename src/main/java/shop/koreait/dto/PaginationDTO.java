package shop.koreait.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString(exclude = "elements")
public class PaginationDTO<T> {
    private Integer page = 1;   // 현재 보는 페이지 번호
    private Integer size = 2;   // 현재 페이지에서 표시할 원소의 개수
    private Integer category = 1; // 원소가 가지는 카테고리
    private String sort = "RECENT";    // 원소의 정렬 방식
    private Integer totalPageCount; // 전체 페이지 총 개수 (한 페이지에서 표시할 개수로 계산된 값)

    private Integer startPage; // 화면에 보여줄 페이지네이션 시작 번호
    private Integer endPage; // 화면에 보여줄 페이지네이션 마지막 번호
    private Integer pageViewOffset = 2; // 페이지네이션에서 현재 페이지 번호 앞 뒤의 개수
    private Integer totalPageViewCount = 5; // 화면에 보여줄 페이지네이션 번호 총 개수

    private Integer totalElementCount; // 원소의 총 개수 (위 조건에 맞는 페이지네이션에서 가져올 총 개수)
    private List<T> elements; // 현재 페이지에 가져갈 원소들

    // Database에서 요소를 조회 할 때 사용하는 offset 계산
    public Integer getOffset(){
        return (page - 1) * size;
    }

    public void setTotalElementCount(Integer totalElementCount){
        this.totalElementCount = totalElementCount;
        this.totalPageCount = (int) Math.ceil((double) totalElementCount / size);
        this.startPage = Math.max(1, page - pageViewOffset);
        this.endPage = Math.min(totalPageCount, page + pageViewOffset);
//        if (endPage - startPage < totalPageViewCount) {
//            int delta = totalPageViewCount - (endPage - startPage);
//            startPage = Math.max(1, startPage - delta);
//            endPage = Math.min(totalPageCount, startPage + totalPageViewCount - 1);
//        }
    }
}













