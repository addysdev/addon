<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageOrderAddPageList(page) {
        document.orderAddConForm.curPage.value = page;
        var dataParam = $("#orderAddConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/master/orderaddpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#orderAddPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    function fcAdd_cancel(addcode){

     	if (confirm('선택 지점 및 업체의 발주추가를 해제 하시겠습니까?')){    
	
     		var frm=document.orderAddPageListForm;
     		frm.addCode.value=addcode;
     		
		    $.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/master/addcancel",
		           data:$("#orderAddPageListForm").serialize(),
		           success: function(result) {
	
						if(result=='1'){
							 alert('발주추가을 해제를 성공했습니다.');
							 fcOrderAdd_listSearch();
						} else{
							 alert('발주추가을 해제를 실패했습니다.');
						}
		           },
		           error:function(){
		        	   
		        	   alert('발주추가을 해제를 실패했습니다.');
		           }
		    });
	    
     	}
    	
    	
    }
	
</SCRIPT>
     <form:form commandName="orderAddVO" name="orderAddPageListForm" id="orderAddPageListForm" method="post" action="" >
     <input type="hidden" name="addCode" id="addCode">
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span>전체건수 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table  class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'>추가코드</th>
	        <th class='text-center'>추가대상지점</th>
            <th class='text-center'>추가대상업체</th>
            <th class='text-center'>추가시작일</th>
            <th class='text-center'>추가종료일</th>
            <th class='text-center'>추가해제</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty orderAddList}">
             <c:forEach items="${orderAddList}" var="orderAddVO" varStatus="status">
             <tr id="select_tr_${orderAddVO.addCode}">
                 <td class='text-center'><c:out value="${orderAddVO.addCode}"></c:out></td>
                 <td class='text-center'><c:out value="${orderAddVO.groupName}"></c:out></td>
                 <td class='text-center'><c:out value="${orderAddVO.companyName}"></c:out></td>
                 <td class='text-center'><c:out value="${orderAddVO.addStartDate}"></c:out></td>
                 <td class='text-center'><c:out value="${orderAddVO.addEndDate}"></c:out></td>
                 <td class='text-center'><button type="button" id="receivebtn" class="btn btn-xs btn-success" onClick="fcAdd_cancel('${orderAddVO.addCode}');">해제</button></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty orderAddList}">
              <tr>
                  <td colspan='6' class='text-center'>조회된 데이터가 없습니다.</td>
              </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageOrderAddPageList" totalCount="${totalCount}" curPage="${orderAddConVO.curPage}" rowCount="${orderAddConVO.rowCount}" />
     <!-- //페이징 -->

    