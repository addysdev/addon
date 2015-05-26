<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcStockDetail_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        stockDetailConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/master/stockdetailpagelist",
                    data:$("#stockDetailConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#stockDetailPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcStockDetail_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
</SCRIPT>
<!-- 사용자관리 -->
<div class="container-fluid">     
     <div class="form-group" >
	 <form:form commandName="productMasterVO" name="productDetailForm" method="post" action="" >
	  <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 품목 상세현황</font></strong></h4>
	  <br>
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >품목코드</th>
          <th class='text-center'><c:out value="${productMasterVO.productCode}"/></th>
          <th class='text-center' style="background-color:#E6F3FF">바코드</th>
          <th class='text-center'><c:out value="${productMasterVO.barCode}"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >품목명</th>
          <th colspan='3' class='text-center'><c:out value="${productMasterVO.productName}"/></th>
        </tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >입고단가</th>
          <th class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${productMasterVO.productPrice}"/></th>
          <th class='text-center'style="background-color:#E6F3FF">VAT</th>
          <th class='text-right'><c:out value="${productMasterVO.vatRate}"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >구매처코드</th>
          <th class='text-center'><c:out value="${productMasterVO.companyCode}"/></th>
          <th class='text-center' style="background-color:#E6F3FF">구매처명</th>
          <th class='text-center'><c:out value="${productMasterVO.companyName}"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >그룹1</th>
          <th class='text-center'><c:out value="${productMasterVO.group1}"/></th>
          <th class='text-center' style="background-color:#E6F3FF">그룹1명</th>
          <th class='text-center'><c:out value="${productMasterVO.group1Name}"/></th>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >그룹2</th>
          <th class='text-center'><c:out value="${productMasterVO.group2}"/></th>
          <th class='text-center' style="background-color:#E6F3FF">그룹2명</th>
          <th class='text-center'><c:out value="${productMasterVO.group2Name}"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >그룹3</th>
          <th class='text-center'><c:out value="${productMasterVO.group3}"/></th>
          <th class='text-center' style="background-color:#E6F3FF">그룹3명</th>
          <th class='text-center'><c:out value="${productMasterVO.group3Name}"/></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
     <br>
	  <form:form commandName="stockMasterVO" name="stockMasterPageListForm" method="post" action="" >
	    <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'>지점</th>
	        <th class='text-center'>안전재고</th>
	        <th class='text-center'>보유재고</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty stockMasterList}">
	            <c:forEach items="${stockMasterList}" var="stockMasterVO" varStatus="status">
	            <tr id="select_tr_${stockMasterVO.groupId}">
	              <td><c:out value="${stockMasterVO.groupName}"></c:out></td>
	              <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${stockMasterVO.safeStock}"/></td>
	              <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${stockMasterVO.holdStock}"/></td>
	             </tr>
	            </c:forEach>
	           </c:if>
	          <c:if test="${empty stockMasterList}">
	             <tr>
	                 <td colspan='3' class='text-center'>조회된 데이터가 없습니다.</td>
	             </tr>
	         </c:if>
	    </tbody>
	  </table>
	 </form:form>
</div >
