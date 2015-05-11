<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageSalesPageList(page) {
        document.salesConForm.curPage.value = page;
        var dataParam = $("#salesConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/master/salespagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#salesPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }

    // 매출 상세 페이지 리스트 Layup
    function fcSales_detailPageList(salesDate,groupId) {

    	$('#salesDetailManage').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 650,
            height : 750,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/master/salesdetailmanage?salesDate='+salesDate+'&groupId='+groupId);
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#salesDetailManage").dialog('close');

                    });
            }
            ,close:function(){
                $('#salesDetailManage').empty();
            }
        });
    };

</SCRIPT>

     <form:form commandName="salesVO" name="salesPageListForm" method="post" action="" >
      <p><span>총 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <colgroup>
	     <col width="15%" />
         <col width="15%" />
         <col width="*" />
         <col width="10%" />
         <col width="10%" />
         <col width="15%" />
        </colgroup>
	    <thead>
	      <tr>
	        <th>매출현황일자</th>
            <th>매장아이디</th>
            <th>매장명</th>
            <th>최종업데이트UserID</th>
            <th>최종업데이트UserName</th>
            <th>최종업데이트</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty salesList}">
             <c:forEach items="${salesList}" var="salesVO" varStatus="status">
             <tr id="select_tr_${salesVO.salesDate}_${salesVO.groupId}">
                 <td><c:out value="${salesVO.salesDate}"></c:out></td>
                 <td><a href="javascript:fcSales_detailPageList('${salesVO.salesDate}','${salesVO.groupId}')"><c:out value="${salesVO.groupId}"></c:out></a></td>
                 <td><c:out value="${salesVO.groupName}"></c:out></td>
                 <td><c:out value="${salesVO.updateUserId}"></c:out></td>
                 <td><c:out value="${salesVO.updateUserName}"></c:out></td>
                 <td><c:out value="${salesVO.updateDateTime}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty userList}">
              <tr>
                  <td colspan='6' class='text-center'>조회된 데이터가 없습니다.</td>
              </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageUserManagePageList" totalCount="${totalCount}" curPage="${userCon.curPage}" rowCount="${userCon.rowCount}" />
     <!-- //페이징 -->
