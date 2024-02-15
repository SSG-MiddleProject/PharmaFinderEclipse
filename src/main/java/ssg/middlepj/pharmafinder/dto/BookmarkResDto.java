package ssg.middlepj.pharmafinder.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookmarkResDto extends BookmarkDto {
    private String targetName;  // 약국 이름
}
