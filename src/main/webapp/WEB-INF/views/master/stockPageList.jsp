<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageStockPageList(page) {
        document.stockConForm.curPage.value = page;
        var dataParam = $("#stockConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/stockpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#stockPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    
    // 재고 상세 페이지 리스트 Layup
    function fcStock_detailPageList(stockDate,groupId) {

    	$('#stockDetailManage').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 650,
            height : 750,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/master/stockdetailmanage?stockDate='+stockDate+'&groupId='+groupId);
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#stockDetailManage").dialog('close');

                    });
            }
            ,close:function(){
                $('#stockDetailManage').empty();
            }
        });
    };

</SCRIPT>

     <form:form commandName="stockVO" name="stockPageListForm" method="post" action="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span></p>       
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'>재고현황일자</th>
            <th class='text-center'>매장</th>
            <th class='text-center'>재고수량</th>
            <th class='text-center'>입고금액</th>
            <th class='text-center'>재고금액</th>
            <th class='text-center'>업데이트 User</th>
            <th class='text-center'>업데이트 일시</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty stockList}">
             <c:forEach items="${stockList}" var="stockVO" varStatus="status">
             <tr id="select_tr_${stockVO.stockDate}">
                 <td class='text-center'><a href="javascript:fcStock_detailPageList('${stockVO.stockDate}','${stockVO.groupId}')"><c:out value="${stockVO.stockDate}"></c:out></a></td>
                 <td class='text-center'><c:out value="${stockVO.groupName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${stockVO.stockCnt}"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${stockVO.productPrice}"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${stockVO.stockPrice}"/></td>
                 <td class='text-center'><c:out value="${stockVO.lastUserName}"></c:out></td>
                 <td class='text-center'><c:out value="${stockVO.stockDateTime}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty stockList}">
           <tr>
           	<td colspan='7' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageStockPageList" totalCount="${totalCount}" curPage="${stockConVO.curPage}" rowCount="${stockConVO.rowCount}" />
     <!-- //페이징 -->

    