<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageRecoveryPageList(page) {
        document.recoveryConForm.curPage.value = page;
        var dataParam = $("#recoveryConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/recovery/recoverypagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#recoveryPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    // 회수 상세 페이지 리스트 Layup
    function fcRecovery_detail(recoveryCode,groupId,groupName,recoveryState,regDateTime,recoveryClosingDate) {
   
    	var url='<%= request.getContextPath() %>/recovery/recoverydetailview';

    	$('#recoveryDetailView').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 950,
            height : 850,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load(url+'?recoveryCode='+recoveryCode+'&groupId='+groupId+'&groupName='+encodeURIComponent(groupName)+
                		'&recoveryState='+recoveryState);
               
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#recoveryDetailView").dialog('close');

                    });
            }
            ,close:function(){
         
                $('#recoveryDetailView').empty();
                try{
	           		 // alert(barcodeObj);
	               	  if(transObj==undefined){
	               		 	return;
	                 	  }else{
	                 		 transObj.close();
	                 	  }
	           		  
	           	  }catch(e){
	           		 return;
	           	  }
            }
        });
    };

</SCRIPT>
     <form:form commandName="recoveryVO" name="recoveryPageListForm" method="post" action="" >
      <p><span>총 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th>회수상태</th>
            <th>회수번호</th>
            <th>회수요청일자</th>
            <th>회수마감일자</th>
            <th>매장명</th>
            <th>회수수량</th>
            <th>회수금액</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty recoveryList}">
             <c:forEach items="${recoveryList}" var="recoveryVO" varStatus="status">
             <tr id="select_tr_${recoveryVO.recoveryStateView}">
                 <td><c:out value="${recoveryVO.recoveryStateView}"></c:out></td>
                 <td><a href="javascript:fcRecovery_detail('${recoveryVO.recoveryCode}','${recoveryVO.groupId}','${recoveryVO.groupName}','${recoveryVO.recoveryState}','${recoveryVO.collectDateTime}','${recoveryVO.recoveryClosingDate}')"><c:out value="${recoveryVO.recoveryCode}"></c:out></a></td>
                 <td><c:out value="${recoveryVO.collectDateTime}"></c:out></td>
                 <td><c:out value="${recoveryVO.recoveryClosingDate}"></c:out></td>
                 <td><c:out value="${recoveryVO.groupName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryResultCnt}"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryResultPrice}"/></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty recoveryList}">
              <tr>
                  <td colspan='7' class='text-center'>조회된 데이터가 없습니다.</td>
              </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageRecoveryPageList" totalCount="${totalCount}" curPage="${recoveryConVO.curPage}" rowCount="${recoveryConVO.rowCount}" />
     <!-- //페이징 -->

    