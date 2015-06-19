<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 페이지 이동
    function goPageHoldStockPageList(page) {
        document.holdStockConForm.curPage.value = page;
        var dataParam = $("#holdStockConForm").serialize();
        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/analysis/holdstockpagelist",
              data:dataParam,
            success: function(result) {
                   commonDim(false);
                   $("#holdStockPageList").html(result);
            },
            error:function(){
                commonDim(false);
            }
        });
    }
    
    function fcRecomend_update(productCode,groupId,con_applyDateCnt,recomendCnt){

		if (confirm('선택하신 추천 보유재고를 조회하신 조건으로\n업데이트를 하시겠습니까?')){ 
			
	    var frm = document.holdStockPageListForm;
	    
	    frm.productCode.value=productCode;
	    frm.groupId.value=groupId;
	    frm.con_applyDateCnt.value=con_applyDateCnt;
	    frm.recomendCnt.value=recomendCnt;

		 commonDim(true);
	        $.ajax({
	            type: "POST",
	               url:  "<%= request.getContextPath() %>/analysis/holdstockupdate",
	                    data:$("#holdStockPageListForm").serialize(),
	               success: function(result) {
	                   commonDim(false);
	                   if(result=='1'){
							 	alert('추천보유재고 업데이트를 성공했습니다.');
							 	fcHoldStock_listSearch();
  						} else{
  							alert('추천보유재고 업데이트를 실패했습니다.');
  						}
	               },
	               error:function() {
	                   commonDim(false);
	                   alert('추천보유재고 업데이트를 실패했습니다.');
	               }
	        });
			
		}
	}
</SCRIPT>

     <form:form commandName="holdStockVO" name="holdStockPageListForm" id="holdStockPageListForm" method="post" action="" >
      <input type="hidden" ip="productCode" name="productCode" value="" >
      <input type="hidden" ip="groupId" name="groupId" value="" >
      <input type="hidden" ip="con_applyDateCnt" name="con_applyDateCnt" value="" >
      <input type="hidden" ip="recomendCnt" name="recomendCnt" value="" >
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> total : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" /> </span>
      <span style="color:blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#FF9900">[total 보유재고금액] :</font><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalPriceVO.holdStockPrice}" /> 원 
      &nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#FF9900">[total 추천 보유재고금액] :</font> <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalPriceVO.recomendPrice}" /> 원 </span>
      </p>       
	  <table class="table table-bordered">
	  	<colgroup>
	     <col width="10%" />
         <col width="10%" />
         <col width="*" />
         <col width="7%" />
         <col width="7%" />
         <col width="10%" />
         <col width="7%" />
         <col width="7%" />
         <col width="7%" />
         <col width="7%" />
         <col width="5%" />
        </colgroup>
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th rowspan="2" class='text-center'>지점</th>
	        <th rowspan="2" class='text-center'>품목코드</th>
            <th rowspan="2" class='text-center'>품목명</th>
            <th colspan="3" class='text-center'>보유재고</th>
            <th colspan="3" class='text-center'>추천 보유재고</th>
            <th rowspan="2" class='text-center'>증감율</th>
            <th rowspan="2" class='text-center'>업데이트</th>
	      </tr>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'>적용(보유)일수</th>
            <th class='text-center'>보유재고</th>
            <th class='text-center'>보유재고 업데이트일시</th>
            <th class='text-center'>평균매출수량</th>
            <th class='text-center'>적용(보유)일수</th>
            <th class='text-center'>추천 보유재고</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty holdStockList}">
             <c:forEach items="${holdStockList}" var="holdStockVO" varStatus="status">
             <tr id="select_tr_${holdStockVO.productCode}">
                 <td class='text-center'><c:out value="${holdStockVO.groupName}"></c:out></td>
                 <td class='text-center'><c:out value="${holdStockVO.productCode}"></c:out></td>
                 <td><c:out value="${holdStockVO.productName}"></c:out></td>
                 <td class='text-right'><c:out value="${holdStockVO.applyDateCnt}일"></c:out></td>
                 <td class='text-right'><sapn style="color:blue"><c:out value="${holdStockVO.holdStockCnt}"></c:out></sapn></td>
                 <td class='text-center'><c:out value="${holdStockVO.holdStockDateTime}"></c:out></td>
                 <td class='text-right'><c:out value="${holdStockVO.saleAvg}"></c:out></td>
                 <td class='text-right'><c:out value="${holdStockVO.con_applyDateCnt}일"></c:out></td>
                 <td class='text-right'><sapn style="color:red"><c:out value="${holdStockVO.recomendCnt}"></c:out></sapn></td>
                 <td class='text-right'><c:out value="${holdStockVO.resultRate}%"></c:out></td>
                 <td class='text-center'>
                 <c:if test="${strAuth!='03'}">
                 <button type="button" id="updatebtn" class="btn btn-xs btn-success" onClick="fcRecomend_update('${holdStockVO.productCode}','${holdStockVO.groupId}','${holdStockVO.con_applyDateCnt}','${holdStockVO.recomendCnt}');">업데이트</button>
                 </c:if>
                 </td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty holdStockList}">
           <tr>
           	<td colspan='11' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>

	 <!-- 페이징 -->
     <taglib:paging cbFnc="goPageHoldStockPageList" totalCount="${totalCount}" curPage="${holdStockConVO.curPage}" rowCount="${holdStockConVO.rowCount}" />
     <!-- //페이징 -->

