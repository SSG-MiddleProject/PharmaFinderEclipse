package ssg.middlepj.pharmafinder.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {

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

    public ProductDto() {
    }

    public ProductDto(Integer id, String entpName, String itemName, String efcyQes, String useMethodQes, String atpnWarnQes, String atpnQes, String intrcQes, String seQes, String depositMethodQes, String itemImage) {
        this.id = id;
        this.entpName = entpName;
        this.itemName = itemName;
        this.efcyQes = efcyQes;
        this.useMethodQes = useMethodQes;
        this.atpnWarnQes = atpnWarnQes;
        this.atpnQes = atpnQes;
        this.intrcQes = intrcQes;
        this.seQes = seQes;
        this.depositMethodQes = depositMethodQes;
        this.itemImage = itemImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntpName() {
        return entpName;
    }

    public void setEntpName(String entpName) {
        this.entpName = entpName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getEfcyQes() {
        return efcyQes;
    }

    public void setEfcyQes(String efcyQes) {
        this.efcyQes = efcyQes;
    }

    public String getUseMethodQes() {
        return useMethodQes;
    }

    public void setUseMethodQes(String useMethodQes) {
        this.useMethodQes = useMethodQes;
    }

    public String getAtpnWarnQes() {
        return atpnWarnQes;
    }

    public void setAtpnWarnQes(String atpnWarnQes) {
        this.atpnWarnQes = atpnWarnQes;
    }

    public String getAtpnQes() {
        return atpnQes;
    }

    public void setAtpnQes(String atpnQes) {
        this.atpnQes = atpnQes;
    }

    public String getIntrcQes() {
        return intrcQes;
    }

    public void setIntrcQes(String intrcQes) {
        this.intrcQes = intrcQes;
    }

    public String getSeQes() {
        return seQes;
    }

    public void setSeQes(String seQes) {
        this.seQes = seQes;
    }

    public String getDepositMethodQes() {
        return depositMethodQes;
    }

    public void setDepositMethodQes(String depositMethodQes) {
        this.depositMethodQes = depositMethodQes;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
