<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<SCRIPT>
    // 페이지 이동
    function goPageUserManagePageList(page) {
        document.userManageConForm.curPage.value = page;
        var dataParam = $("#userManageConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/userpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#userManagePageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

</SCRIPT>
	<div class="container">
     <form:form commandName="userlistVO" name="userManagePageListForm" method="post" action="" >
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
	        <th><input type="checkbox"  id="userCheckAll"  name="userCheckAll" onchange="fcUserManage_checkAll();" title="전체선택" /></th>
	        <th>아이디</th>
            <th>이름</th>
            <th>지점</th>
            <th>사용유무</th>
            <th>조회권한</th>
            <th>Email</th>
            <th>수정자</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty userList}">
             <c:forEach items="${userList}" var="userListVO" varStatus="status">
             <tr id="select_tr_${userListVO.userId}">
                 <td><input type="checkbox" id="userCheck" name="userCheck" value="${userListVO.userId}" title="선택" /></td>
                 <td><a href="javascript:fcUserManage_detailSearch('${userListVO.userId}')"><c:out value="${userListVO.userId}"></c:out></a></td>
                 <td><c:out value="${userListVO.userName}"></c:out></td>
                 <td><c:out value="${userListVO.groupName}"></c:out></td>
                 <td class="text_c"><c:out value="${userListVO.useYn}"></c:out></td>
                 <td class="text_c"><c:out value="${userListVO.authName}"></c:out></td>
                 <td><c:out value="${userListVO.email}"></c:out></td>
                 <td><c:out value="${userListVO.updateUserId}"></c:out></td>
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
        <taglib:paging cbFnc="goPageUserManagePageList" totalCount="${totalCount}" curPage="${userCon.curPage}" rowCount="${userCon.rowCount}" />
        <!-- //페이징 -->
	</div>
    