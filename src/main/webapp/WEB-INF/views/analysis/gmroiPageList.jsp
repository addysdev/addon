<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageGmroiPageList(page) {
   
        document.gmroiConForm.curPage.value = page;
        var dataParam = $("#gmroiConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/analysis/gmroipagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#gmroiPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
 // 
    function goOrderByPageList(orderByName,orderBySort) {
        
    	document.gmroiConForm.orderByName.value = orderByName;
        document.gmroiConForm.orderBySort.value = orderBySort;
        
        var dataParam = $("#gmroiConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/analysis/gmroipagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#gmroiPageList").html(result);
                   
                   document.gmroiConForm.orderByName.value = '';
                   document.gmroiConForm.orderBySort.value = '';
            },
            error:function(){
                commonDim(false);
                
                document.gmroiConForm.orderByName.value = '';
                document.gmroiConForm.orderBySort.value = '';
            }
        });
    }
</SCRIPT>

     <form:form commandName="gmroiVO" name="gmroiPageListForm" method="post" action="" >
      <p><table   class="table table-bordered tbl_type" >
	     <colgroup>
	      <col width="100px" >
	      <col width="100px" >
	      <col width="100px" >
	      <col width="100px">
	      <col width="100px" >
	      <col width="100px" >
	      <col width="100px" >
	      <col width="100px">
	      <col width="100px" >
	      <col width="100px" >
	      <col width="100px" >
	      <col width="100px" >
	      <col width="100px" >
	      <col width="100px">
	      <col width="100px" >
	      <col width="100px" >
	     </colgroup>
	     <tr>
	     	<td class='text-center' style="background-color:#E6F3FF">전체건수</td>
	     	<td class='text-right'><a href="javascript:stateSearch('')"><span style="color:red">
	          <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" />
	          </span></a></td>
	     	<td class='text-center' style="background-color:#E6F3FF">재고수량</td>
	     	<td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalGmroiVO.avgStockCnt}" /></td>
	     	<td class='text-center' style="background-color:#E6F3FF">재고금액</td>
	     	<td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalGmroiVO.avgStockAmt}" /></td>
	     	<td class='text-center' style="background-color:#E6F3FF">매출수량</td>
	     	<td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalGmroiVO.totalSaleCnt}" /></td>
	     	<td class='text-center' style="background-color:#E6F3FF">매출금액</td>
	     	<td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalGmroiVO.totalSaleAmt}" /></td>
	     	<td class='text-center' style="background-color:#E6F3FF">이익금액</td>
	     	<td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalGmroiVO.profitSaleAmt}" /></td>
	     	<td class='text-center' style="background-color:#E6F3FF">총이익율</td>
	     	<td class='text-right'>${totalGmroiVO.avgSaleRate}</td>
	     	<td class='text-center' style="background-color:#E6F3FF">회전율</td>
	     	<td class='text-right'>${totalGmroiVO.stockCycleRate}</td>
	     	<td class='text-center' style="background-color:#E6F3FF">GMROI</td>
	     	<td class='text-right'>${totalGmroiVO.gmroiRate}</td>
	     </tr>
     </table>
      </p>       
	  <table class="table table-bordered">
	  	<colgroup>
	     <col width="10%" />
	     <col width="*" />
         <col width="9%" />
         <col width="9%" />
         <col width="9%" />
         <col width="9%" />
         <col width="9%" />
         <col width="9%" />
         <col width="6%" />
         <col width="6%" />
        </colgroup>
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th rowspan="2" class='text-center'>품목코드</th>
            <th rowspan="2" class='text-center'>품목명</th>
            <th colspan="2" class='text-center'>재고</th>
            <th colspan="4" class='text-center'>매출</th>
            <th rowspan="2" class='text-center'>재고금액<br><a href="javascript:goOrderByPageList('stockCycleRate','desc')">▲</a>회전율<a href="javascript:goOrderByPageList('stockCycleRate','asc')">▼</a></th>
            <th rowspan="2" class='text-center'><a href="javascript:goOrderByPageList('gmroiRate','desc')">▲</a>GMROI<a href="javascript:goOrderByPageList('gmroiRate','asc')">▼</a></th>
	      </tr>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'><a href="javascript:goOrderByPageList('avgStockCnt','desc')">▲</a>평균 재고수량<a href="javascript:goOrderByPageList('avgStockCnt','asc')">▼</a></th>
            <th class='text-center'><a href="javascript:goOrderByPageList('avgStockAmt','desc')">▲</a>평균 재고금액<a href="javascript:goOrderByPageList('avgStockAmt','asc')">▼</a></th>
            <th class='text-center'><a href="javascript:goOrderByPageList('totalSaleCnt','desc')">▲</a>총 매출수량<a href="javascript:goOrderByPageList('totalSaleCnt','asc')">▼</a></th>
            <th class='text-center'><a href="javascript:goOrderByPageList('totalSaleAmt','desc')">▲</a>총 매출금액<a href="javascript:goOrderByPageList('totalSaleAmt','asc')">▼</a></th>
            <th class='text-center'><a href="javascript:goOrderByPageList('profitSaleAmt','desc')">▲</a>총 이익금액<a href="javascript:goOrderByPageList('profitSaleAmt','asc')">▼</a></th>
            <th class='text-center'><a href="javascript:goOrderByPageList('avgSaleRate','desc')">▲</a>총 이익율<a href="javascript:goOrderByPageList('avgSaleRate','asc')">▼</a></th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty gmroiList}">
             <c:forEach items="${gmroiList}" var="gmroiVO" varStatus="status">
             <tr id="select_tr_${gmroiVO.productCode}">
                 <td class='text-center'><c:out value="${gmroiVO.productCode}"></c:out></td>
                 <td><c:out value="${gmroiVO.productName}"></c:out></td>
                 <td class='text-right'><c:out value="${gmroiVO.avgStockCnt}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${gmroiVO.avgStockAmt}"/>&nbsp;원</td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${gmroiVO.totalSaleCnt}"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${gmroiVO.totalSaleAmt}"/>&nbsp;원</td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${gmroiVO.profitSaleAmt}"/>&nbsp;원</td>
                 <td class='text-right'><c:out value="${gmroiVO.avgSaleRate}"></c:out>&nbsp;%</td>
                 <td class='text-right'><c:out value="${gmroiVO.stockCycleRate}"></c:out>&nbsp;회전</td>
                 <td class='text-right'><c:out value="${gmroiVO.gmroiRate}"></c:out>&nbsp;%</td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty gmroiList}">
           <tr>
           	<td colspan='10' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageGmroiPageList" totalCount="${totalCount}" curPage="${gmroiConVO.curPage}" rowCount="${gmroiConVO.rowCount}" />
     <!-- //페이징 -->

