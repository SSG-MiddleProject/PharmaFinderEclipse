package ssg.middlepj.pharmafinder.dto;

public class PaginationParam {
    private Integer page;
    private Integer recordSize;
    private Integer pageSize;
    private Integer offset;
    private String keyword = "";
    private String searchType = "efcyQes";

    public PaginationParam() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
        this.offset = 0;
        this.keyword = "";
        this.searchType = "efcyQes";
    }

    public PaginationParam(Integer page, Integer recordSize, Integer pageSize, String keyword, String searchType) {
        this.page = page;
        this.recordSize = recordSize;
        this.pageSize = pageSize;
        this.offset = (page - 1) * pageSize;
        this.keyword = keyword;
        this.searchType = searchType;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(Integer recordSize) {
        this.recordSize = recordSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return offset;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "PaginationParam{" +
                "page=" + page +
                ", recordSize=" + recordSize +
                ", pageSize=" + pageSize +
                ", offset=" + offset +
                ", keyword='" + keyword + '\'' +
                ", searchType='" + searchType + '\'' +
                '}';
    }
}
