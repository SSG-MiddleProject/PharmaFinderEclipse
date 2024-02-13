package ssg.middlepj.pharmafinder.dto;

import lombok.Data;

@Data
public class ProductDto {

    /**
     * itemSeq
     */
    private Integer id;
    /**
     * entpName
     */
    private String entpName;
    /**
     * itemName
     */
    private String itemName;
    /**
     * 문항1(효능)
     */
    private String efcyQes;
    /**
     * 문항2(사용법)
     */
    private String useMethodQes;
    /**
     * 문항3(주의사항 경고)
     */
    private String atpnWarnQes;
    /**
     * 문항4(약제변경)
     */
    private String atpnQes;
    /**
     * 문항5(상호작용)
     */
    private String intrcQes;
    /**
     * 문항6(부작용)
     */
    private String seQes;
    /**
     * 문항7(보관방법)
     */
    private String depositMethodQes;
    /**
     * itemImage
     */
    private String itemImage;
}
