package ssg.middlepj.pharmafinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookmarkDto {
    private Integer userId;
    private Integer targetId;
}
