<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageOrderPageList(page) {
        document.orderConForm.curPage.value = page;
        var dataParam = $("#orderConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/order/orderpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#orderPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    // 검수 상세 페이지 리스트 Layup
    function fcOrder_detail(orderCode) {
    	
    	var url='<%= request.getContextPath() %>/order/orderdetailview';

    	$('#orderDetailView').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 950,
            height : 850,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load(url+'?orderCode='+orderCode);
               
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#orderDetailView").dialog('close');

                    });
            }
            ,close:function(){
                $('#orderDetailView').empty();
            }
        });
    };
    
    function stateSearch(state){
    	
    	document.orderConForm.con_orderState.value=state;
    	document.orderConForm.searchGubun.value='';
    	document.orderConForm.searchValue.value='';
    	fcOrder_listSearch();
    }
</SCRIPT>
     <form:form commandName="orderVO" name="orderPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> 전체건수 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /></span> 
      <span style="color:blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#FF9900">[검수대기] :</font> <a href="javascript:stateSearch('03')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${stateVO.waitCnt}" /></a>
      &nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#FF9900">[검수보류] :</font> <a href="javascript:stateSearch('04')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${stateVO.deferCnt}" /></a>    
	  &nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#FF9900">[검수완료] :</font> <a href="javascript:stateSearch('06')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${stateVO.completeCnt}" /></a>     
	  &nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#FF9900">[등록완료] :</font> <a href="javascript:stateSearch('07')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${stateVO.registCnt}" /></a>
	  </span></p>       
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'>검수상태</th>
            <th class='text-center'>발주번호</th>
            <th class='text-center'>발주일자</th>
            <th class='text-center'>발주자</th>
            <th class='text-center'>매장명</th>
            <th class='text-center'>업체명</th>
            <!--  >th class='text-center'>공급가</th>
            <th class='text-center'>부가세</th-->
            <th class='text-center'>금액(VAT포함)</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty orderList}">
             <c:forEach items="${orderList}" var="orderVO" varStatus="status">
             <tr id="select_tr_${orderVO.orderCode}">
                 <td class='text-center'><c:out value="${orderVO.orderStateView}"></c:out></td>
                 <td class='text-center'><a href="javascript:fcOrder_detail('${orderVO.orderCode}')"><c:out value="${orderVO.orderCode}"></c:out></a></td>
                 <td class='text-center'><c:out value="${orderVO.orderDateTime}"></c:out></td>
                 <td class='text-center'><c:out value="${orderVO.orderUserName}"></c:out></td>
                 <td class='text-center'><c:out value="${orderVO.groupName}"></c:out></td>
                 <td class='text-center'><c:out value="${orderVO.companyName}"></c:out></td>
                 <!--td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${orderVO.supplyPrice}"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${orderVO.vat}"/></td-->
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${orderVO.totalOrderPrice}"/></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty orderList}">
           <tr>
           	<td colspan='7' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

    <!-- 페이징 -->
    <taglib:paging cbFnc="goPageOrderPageList" totalCount="${totalCount}" curPage="${orderConVO.curPage}" rowCount="${orderConVO.rowCount}" />
    <!-- //페이징 -->

    