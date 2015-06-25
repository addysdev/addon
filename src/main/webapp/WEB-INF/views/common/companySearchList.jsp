<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageCompanyPageList(page) {
        document.companyConForm.curPage.value = page;
        var dataParam = $("#companyConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/companysearchlist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#companySearchList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    
</SCRIPT>

     <form:form commandName="companyVO" name="companyPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th class='text-center'>업체코드</th>
            <th class='text-center'>업체명</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty companyList}">
             <c:forEach items="${companyList}" var="companyVO" varStatus="status">
              <tr id="select_tr_${companyVO.companyCode}">
                 <td class='text-center'><a href="javascript:fcCompany_Select('${companyVO.companyCode}','${companyVO.companyName}')"><c:out value="${companyVO.companyCode}"></c:out></a></td>
                 <td><c:out value="${companyVO.companyName}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty companyList}">
           <tr>
           	<td colspan='2' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageCompanyPageList" totalCount="${totalCount}" curPage="${companyConVO.curPage}" rowCount="${companyConVO.rowCount}" />
     <!-- //페이징 -->
