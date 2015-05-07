<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageTargetPageList(page) {
        document.targetConForm.curPage.value = page;
        var dataParam = $("#targetConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/order/targetpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#targetPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    // 재고 상세 페이지 리스트 Layup
    function fcTarget_detail(groupId,groupName,companyCode,orderState) {

    	$('#targetDetailView').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 950,
            height : 850,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/order/targetdetailview?groupId='+groupId+
                		'&groupName='+groupName+'&companyCode='+companyCode+'&orderState='+orderState);
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#targetDetailView").dialog('close');

                    });
            }
            ,close:function(){
                $('#targetDetailView').empty();
            }
        });
    };


</SCRIPT>
     <form:form commandName="targetVO" name="targetPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-striped">
	    <thead>
	      <tr>
	        <th class='text-center'>발주상태</th>
            <th class='text-center'>매장ID</th>
            <th class='text-center'>매장명</th>
            <th class='text-center'>업체코드</th>
            <th class='text-center'>업체명</th>
            <th class='text-center'>수량</th>
            <th class='text-center'>기준금액</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty targetList}">
             <c:forEach items="${targetList}" var="targetVO" varStatus="status">
             <tr id="select_tr_${targetVO.groupId}_${targetVO.companyCode}">
                 <td class='text-center'><c:out value="${targetVO.buyResultView}"></c:out></td>
                 <td class='text-center'><a href="javascript:fcTarget_detail('${targetVO.groupId}','${targetVO.groupName}','${targetVO.companyCode}','${targetVO.buyResult}')"><c:out value="${targetVO.groupId}"></c:out></a></td>
                 <td><c:out value="${targetVO.groupName}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.companyCode}"></c:out></td>
                 <td><c:out value="${targetVO.companyName}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.orderCnt}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.orderAmt}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty targetList}">
           <tr>
           	<td colspan='7' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

    <!-- 페이징 -->
    <taglib:paging cbFnc="goPageTargetPageList" totalCount="${totalCount}" curPage="${targetConVO.curPage}" rowCount="${targetConVO.rowCount}" />
    <!-- //페이징 -->

    