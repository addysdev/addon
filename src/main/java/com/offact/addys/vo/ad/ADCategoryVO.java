package com.offact.addys.vo.ad;

import com.offact.addys.vo.AbstractVO;

public class ADCategoryVO extends AbstractVO {

    /**
     * @author HSH
     */
    private static final long serialVersionUID = 1L;
    // 테이블컬럼
    private String            categorySCode;        // 카테고리소분류
    private String            categorySNm;          // 카테고리소분류명
    private String            categoryLCode;        // 카테고리대분류
    private String            categoryLNm;          // 카테고리대분류명
    private String            frstRegTs;            // 최초등록일시
    private String            frstRegrId;           // 최초등록자ID
    private String            lastModTs;            // 최종수정일시
    private String            lastCortId;           // 최종수정자ID

    public String getCategorySCode() {
        return categorySCode;
    }

    public void setCategorySCode(String categorySCode) {
        this.categorySCode = categorySCode;
    }

    public String getCategorySNm() {
        return categorySNm;
    }

    public void setCategorySNm(String categorySNm) {
        this.categorySNm = categorySNm;
    }

    public String getCategoryLCode() {
        return categoryLCode;
    }

    public void setCategoryLCode(String categoryLCode) {
        this.categoryLCode = categoryLCode;
    }

    public String getCategoryLNm() {
        return categoryLNm;
    }

    public void setCategoryLNm(String categoryLNm) {
        this.categoryLNm = categoryLNm;
    }

    public String getFrstRegTs() {
        return frstRegTs;
    }

    public void setFrstRegTs(String frstRegTs) {
        this.frstRegTs = frstRegTs;
    }

    public String getFrstRegrId() {
        return frstRegrId;
    }

    public void setFrstRegrId(String frstRegrId) {
        this.frstRegrId = frstRegrId;
    }

    public String getLastModTs() {
        return lastModTs;
    }

    public void setLastModTs(String lastModTs) {
        this.lastModTs = lastModTs;
    }

    public String getLastCortId() {
        return lastCortId;
    }

    public void setLastCortId(String lastCortId) {
        this.lastCortId = lastCortId;
    }

}
