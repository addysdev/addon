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
    
    function fcRecomend_update(productCode,groupId,con_applyDateCnt,recomendCnt,index){

		if (confirm('선택하신 추천 보유재고를 조회하신 조건으로\n업데이트를 하시겠습니까?')){ 
			
	    var frm = document.holdStockPageListForm;
	    
		var amtCnt = frm.recomentCnt_List.length;
		
    	if(amtCnt==undefined){
    		amtCnt=1;
    	}
	    
	    frm.productCode.value=productCode;
	    frm.groupId.value=groupId;
	    frm.con_applyDateCnt.value=con_applyDateCnt;
	    
	    //frm.recomendCnt.value=recomendCnt;
	    
        if(amtCnt > 1){
     		
        	frm.recomendCnt.value=frm.recomentCnt_List[index-1].value;

     	}else{
     		
     		frm.recomendCnt.value=frm.recomentCnt_List.value;

     	}

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
    function fcRecomend_pageupdates(){
    	
    	var frm = document.holdStockPageListForm;
    	  
      	if(frm.seqs.length!=undefined){
      		for(i=0;i<frm.seqs.length;i++){
					frm.seqs[i].value=fillSpace(frm.productCode_List[i].value)+
      			'|'+fillSpace(frm.groupId_List[i].value)+'|'+fillSpace(deleteCommaStr(frm.con_applyDateCnt_List[i].value))+'|'+fillSpace(frm.recomentCnt_List[i].value);

      		}
      	}else{
      		
      		frm.seqs.value=fillSpace(frm.productCode_List.value)+
  			'|'+fillSpace(frm.groupId_List.value)+'|'+fillSpace(deleteCommaStr(frm.con_applyDateCnt_List.value))+'|'+fillSpace(frm.recomentCnt_List.value);

      	}

       if (confirm('추천보유 재고값을 설정된 페이지 정보로 업데이트 하시겠습니까?')){ 
       	
       var paramString = $("#holdStockPageListForm").serialize();

       commonDim(true);
		  		$.ajax({
			       type: "POST",
			       async:false,
			          url:  "<%= request.getContextPath() %>/analysis/holdstockpageupdate",
			          data:paramString,
			          success: function(result) {
			        	  commonDim(false);
		                   if(result=='1'){
								 	alert('추천보유재고 업데이트를 성공했습니다.');
								 	fcHoldStock_listSearch();
	  						} else{
	  							alert('추천보유재고 업데이트를 실패했습니다.');
	  						}
			          },
			          error:function(){
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
	     </colgroup>
	     <tr>
	     	<td class='text-center' style="background-color:#E6F3FF">전체건수</td>
	     	<td class='text-right'><a href="javascript:stateSearch('')"><span style="color:red">
	          <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalCount}" />
	          </span></a></td>
	     	<td class='text-center' style="background-color:#E6F3FF">총 보유재고금액</td>
	     	<td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalPriceVO.holdStockPrice}" /></td>
	     	<td class='text-center' style="background-color:#E6F3FF">총 추천 보유재고금액</td>
	     	<td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalPriceVO.recomendPrice}" /></td>
	     	<td class='text-center' style="background-color:#E6F3FF">보유재고 차액</td>
	     	<td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${totalPriceVO.calPrice}" /></td>
	     </tr>
     </table>
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
            <th rowspan="2" class='text-center'>업데이트
            <c:if test="${strAuth!='03'}">
  				<br><button type="button" class="btn btn-xs btn-primary" onClick="fcRecomend_pageupdates()">페이지 업데이트</button>
  			</c:if>
            </th>
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
                 <input type="hidden" id="seqs" name="seqs" >
                 <input type="hidden" ip="productCode_List" name="productCode_List" value="${holdStockVO.productCode}" >
                 <input type="hidden" ip="groupId_List" name="groupId_List" value="${holdStockVO.groupId}" >
                 <input type="hidden" ip="con_applyDateCnt_List" name="con_applyDateCnt_List" value="${holdStockVO.con_applyDateCnt}" >
                 <td class='text-center'><c:out value="${holdStockVO.groupName}"></c:out></td>
                 <td class='text-center'><c:out value="${holdStockVO.productCode}"></c:out></td>
                 <td><c:out value="${holdStockVO.productName}"></c:out></td>
                 <td class='text-right'><c:out value="${holdStockVO.applyDateCnt}일"></c:out></td>
                 <td class='text-right'><sapn style="color:blue"><c:out value="${holdStockVO.holdStockCnt}"></c:out></sapn></td>
                 <td class='text-center'><c:out value="${holdStockVO.holdStockDateTime}"></c:out></td>
                 <td class='text-right'><c:out value="${holdStockVO.saleAvg}"></c:out></td>
                 <td class='text-right'><c:out value="${holdStockVO.con_applyDateCnt}일"></c:out></td>
                 <c:choose>
		    		<c:when test="${strAuth!='03'}">
						 <td class='text-right'><sapn style="color:red">
                				<input style="width:60px;text-align:right;color:red" type="text" class="form-control" id="recomentCnt_List" name="recomentCnt_List" maxlength="3" numberOnly value="${holdStockVO.recomendCnt}">   
               			 </sapn></td>
					</c:when>
					<c:otherwise>
						 <td class='text-right'><sapn style="color:red"><c:out value="${holdStockVO.recomendCnt}"></c:out></sapn></td>
					</c:otherwise>
				 </c:choose>
                 <td class='text-right'><c:out value="${holdStockVO.resultRate}%"></c:out></td>
                 <td class='text-center'>
                 <c:if test="${strAuth!='03'}">
                 <button type="button" id="updatebtn" class="btn btn-xs btn-success" onClick="fcRecomend_update('${holdStockVO.productCode}','${holdStockVO.groupId}','${holdStockVO.con_applyDateCnt}','${holdStockVO.recomendCnt}','${status.count}');">업데이트</button>
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

