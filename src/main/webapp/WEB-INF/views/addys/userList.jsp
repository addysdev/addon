<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<SCRIPT>
    // 페이지 이동
    function goPageAdUserListManageList(page) {
        document.adUserListManageConForm.curPage.value = page;
        var dataParam = $("#adUserListManageConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/addys/userlist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#adUserListManageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>
	<div class="container">
     <form:form commandName="userlistVO" name="adUserListManagelistForm" method="post" action="" >
      <p><span>총 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <colgroup>
	     <col width="5%" />
         <col width="15%" />
         <col width="15%" />
         <col width="*" />
         <col width="10%" />
         <col width="10%" />
         <col width="10%" />
         <col width="15%" />
        </colgroup>
	    <thead>
	      <tr>
	        <th><input type="checkbox"></th>
	        <th>아이디</th>
            <th>이름</th>
            <th>지점</th>
            <th>사용유무</th>
            <th>로그인</th>
            <th>근무여부</th>
            <th>업체구분</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty userList}">
             <c:forEach items="${userList}" var="userListVO" varStatus="status">
             <tr id="select_tr_${userListVO.userId}" onClick="javascript:fcAdUserListManage_detailSearch('${userListVO.userId}')">
                 <td><input type="checkbox"></td>
                 <td><c:out value="${userListVO.userId}"></c:out></td>
                 <td><c:out value="${userListVO.userNm}"></c:out></td>
                 <td><c:out value="${userListVO.userGroupCodeNm}"></c:out></td>
                 <td class="text_c"><c:out value="${userListVO.usingYn}"></c:out></td>
                 <td class="text_c"><c:out value="${userListVO.loginYn}"></c:out></td>
                 <td><c:out value="${userListVO.workYn}"></c:out></td>
                 <td><c:out value="${userListVO.companyGbn}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty userList}">
              <tr>
                  <td colspan='7' class='text_c'>조회된 데이터가 없습니다.</td>
              </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>
	</div>
	<div class="container">
	    <!-- 페이징 -->
        <taglib:paging cbFnc="goPageAdUserListManageList" totalCount="${totalCount}" curPage="${userCon.curPage}" rowCount="${userCon.rowCount}" />
        <!-- //페이징 -->
	</div>
    