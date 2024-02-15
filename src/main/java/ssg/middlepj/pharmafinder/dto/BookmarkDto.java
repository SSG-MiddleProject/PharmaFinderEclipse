package ssg.middlepj.pharmafinder.dto;

import lombok.Data;

@Data
public class BookmarkDto {
    private Integer userId;        //유저의 아이디정보
    private Integer targetId;    //즐겨찾기한 정보가담기 id

    public BookmarkDto() {
    }

    public BookmarkDto(int userId, Integer targetId) {
    	this.userId = userId;
    	this.targetId = targetId;
    }
}
