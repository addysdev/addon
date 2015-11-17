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
	  <table class="table table-bordered">
	    <colgroup>
	     <col width="15%" />
         <col width="15%" />
         <col width="*" />
         <col width="10%" />
         <col width="10%" />
         <col width="15%" />
        </colgroup>
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'>매출현황일자</th>
            <th class='text-center'>매장</th>
            <th class='text-center'>판매수량</th>
       <!-- <th class='text-center'>입고금액</th>-->
            <th class='text-center'>판매금액</th>
            <th class='text-center'>업데이트User</th>
            <th class='text-center'>업데이트일시</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty salesList}">
             <c:forEach items="${salesList}" var="salesVO" varStatus="status">
             <tr id="select_tr_${salesVO.salesDate}_${salesVO.groupId}">
                 <td class='text-center'><a href="javascript:fcSales_detailPageList('${salesVO.salesDate}','${salesVO.groupId}')"><c:out value="${salesVO.salesDate}"></c:out></a></td>
                 <td class='text-center'><c:out value="${salesVO.groupName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${salesVO.salesCnt}"/></td>
               <!--   <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${salesVO.productPrice}"/></td> -->
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${salesVO.salesPrice}"/></td>
                 <td class='text-center'><c:out value="${salesVO.updateUserName}"></c:out></td>
                 <td class='text-center'><c:out value="${salesVO.updateDateTime}"></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty salesList}">
              <tr>
                  <td colspan='6' class='text-center'>조회된 데이터가 없습니다.</td>
              </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageUserManagePageList" totalCount="${totalCount}" curPage="${salesConVO.curPage}" rowCount="${salesConVO.rowCount}" />
     <!-- //페이징 -->
