package com.offact.framework.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

public class PagingTag extends TagSupport {

    private String totalCount;

    private String rowCount;

    private String curPage;

    private String cbFnc;

    private final int maxPagingCount = 10;

    /**
     * edit 2014.02.12 HSH cbFnc 추가함
     */
    private static final long serialVersionUID = 1L;

    private final Logger logger = Logger.getLogger(getClass());

    @Override
    public int doEndTag() throws JspException {
        // TODO Auto-generated method stub
        return super.doEndTag();
    }

    @Override
    public int doStartTag() throws JspException {
        // TODO Auto-generated method stub
        String contextRoot = pageContext.getServletContext().getContextPath();
        contextRoot = contextRoot.equals("/") ? "" : contextRoot;

        StringBuffer html = new StringBuffer();

        // logger.info("\n doStartTag \n totalCount ==>" + totalCount
        // + "\n rowCount ==>" + rowCount
        // + "\n curPage ==>" + curPage
        // );

        if (totalCount.equals("") || rowCount.equals("") || curPage.equals("")) {
            return super.doStartTag();
        }

        if (cbFnc == null) {
            cbFnc = "goPage";
        }

        if (cbFnc.equals("")) {
            cbFnc = "goPage";
        }

        long lTotalCount = Long.parseLong(totalCount);
        long lRowCount = Long.parseLong(rowCount);
        long lCurPage = Long.parseLong(curPage);
        // 전체 페이지 수
        long lPageCount = lTotalCount % lRowCount;

        // logger.info("\n doStartTag \n lPageCount ==>" + lPageCount );

        if (lPageCount == 0L) {
            lPageCount = lTotalCount / lRowCount;
        } else {
            lPageCount = lTotalCount / lRowCount + 1L;
        }

        html.append("<div class=\"container\">\n<ul class=\"pagination\">\n");

        if (lTotalCount > 0) {

            // 1 페이지이면 비활성화
            if (lCurPage == 1) {

            } else {
                html.append("<li><a href=\"javascript:"
                        + cbFnc
                        + "('1');\" ><span class=\"glyphicon glyphicon-step-backward\"></span></a></li>");
                html.append("<li><a href=\"javascript:"
                        + cbFnc
                        + "('"
                        + (lCurPage - 1)
                        + "');\" ><span class=\"glyphicon glyphicon-chevron-left\"></span></a></li>");
            }
            long startPage = (lCurPage / maxPagingCount) * maxPagingCount + 1;
            if( 0L == lCurPage % maxPagingCount && lCurPage > 9 ) {
            	startPage = startPage - maxPagingCount;
            }
            long endPage = startPage + maxPagingCount - 1;

            // logger.info("\n doStartTag \n startPage ==>" + startPage
            // + "\n endPage ==>" + endPage
            // );

            if (endPage > lPageCount) {
                endPage = lPageCount;
            }
            for (long page = startPage; page <= endPage; page++) {

                if (lCurPage == page) {
                	 html.append("<li class=\"active\"><a href=\"#\">" + page + "</a></li>");
                } else {
                    html.append("<li><a href=\"javascript:" + cbFnc + "('" + page
                            + "');\">" + page + "</a></li>");
                }
            }
            // 마지막 페이지면 비활성화
            if (lCurPage == lPageCount) {

            } else {
                html.append("<li><a href=\"javascript:"
                        + cbFnc
                        + "('"
                        + (lCurPage + 1)
                        + "');\" > <span class=\"glyphicon glyphicon-chevron-right\"></span></a></li>");
                html.append("<li><a href=\"javascript:"
                        + cbFnc
                        + "('"
                        + lPageCount
                        + "');\" ><span class=\"glyphicon glyphicon-step-forward\"></span></a></li>");
            }
        }

        html.append("</ul></div>\n");

        try {

            // pageContext.getOut().flush();

            JspWriter out = pageContext.getOut();

            out.write(html.toString());
        } catch (IOException exx) {

            throw new JspTagException(exx.getMessage());

        }

        return super.doStartTag();
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }

    public String getCurPage() {
        return curPage;
    }

    public void setCurPage(String curPage) {
        this.curPage = curPage;
    }

    public String getCbFnc() {
        return cbFnc;
    }

    public void setCbFnc(String cbFnc) {
        this.cbFnc = cbFnc;
    }

}
