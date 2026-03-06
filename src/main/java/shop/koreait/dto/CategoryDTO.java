package shop.koreait.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDTO {
    private Integer no;
    private Integer parentNo;
    private String name;
    private Integer level;
}
