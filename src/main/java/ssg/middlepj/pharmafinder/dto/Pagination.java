package ssg.middlepj.pharmafinder.dto;

public class Pagination {
    private Integer totalRecordCount;
    private Integer totalPageCount;
    private Integer startPage;
    private Integer endPage;
    private Integer limitStart;
    private Boolean existPrev;
    private Boolean existNext;
    private PaginationParam paginationParam;

    public Pagination(Integer totalRecordCount, PaginationParam paginationParam) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            this.paginationParam = paginationParam;
            calculatePagination(paginationParam);
        }
    }

    private void calculatePagination(PaginationParam paginationParam) {
        this.totalPageCount = ((totalRecordCount) - 1) / paginationParam.getRecordSize() + 1;

        if (paginationParam.getPage() > totalPageCount) {
            paginationParam.setPage(totalPageCount);
        }

        this.startPage = ((paginationParam.getPage() - 1) / paginationParam.getPageSize()) * paginationParam.getPageSize() + 1;
        this.endPage = startPage + paginationParam.getPageSize() - 1;

        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        this.limitStart = (paginationParam.getPage() - 1) * paginationParam.getRecordSize();
        this.existPrev = startPage != 1;
        this.existNext = !endPage.equals(totalPageCount);
    }

    public Integer getTotalRecordCount() {
        return totalRecordCount;
    }

    public void setTotalRecordCount(Integer totalRecordCount) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            calculatePagination(this.paginationParam);
        }
    }

    public Integer getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(Integer totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart = limitStart;
    }

    public Boolean getExistPrev() {
        return existPrev;
    }

    public void setExistPrev(Boolean existPrev) {
        this.existPrev = existPrev;
    }

    public Boolean getExistNext() {
        return existNext;
    }

    public void setExistNext(Boolean existNext) {
        this.existNext = existNext;
    }

    public PaginationParam getPaginationParam() {
        return paginationParam;
    }

    public void setPaginationParam(PaginationParam paginationParam) {
        this.paginationParam = paginationParam;
        calculatePagination(paginationParam);
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "totalRecordCount=" + totalRecordCount +
                ", totalPageCount=" + totalPageCount +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", limitStart=" + limitStart +
                ", existPrev=" + existPrev +
                ", existNext=" + existNext +
                '}';
    }
}
