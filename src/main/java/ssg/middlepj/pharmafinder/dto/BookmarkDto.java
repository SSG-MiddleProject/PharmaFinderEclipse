package ssg.middlepj.pharmafinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookmarkDto {
    private Integer userId;		//유저의 아이디정보
    private Integer targetId;	//즐겨찾기한 정보가담기 id
    private String pharmacyName;  // 약국 이름
    private String productName;   // 제품 이름
}
