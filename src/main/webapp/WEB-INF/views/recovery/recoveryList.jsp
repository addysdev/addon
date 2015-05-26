<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>

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
            }
        });
    };
    function stateSearch(state){
    	
    	document.recoveryConForm.con_recoveryState.value=state;
    	//alert(document.targetConForm.con_orderState.value);
    	fcRecovery_listSearch();
    }
    
    function stateCheck(){

    	if('${strAuth}' != '03'){
    		
    		var frm=document.recoveryPageListForm;
   
    		if(frm.totalCnt.value==frm.waitCnt.value){
    			document.all('rcancelbtn').disabled=false;
    		}else{
    			document.all('rcancelbtn').disabled=true;
    		}
    		
    		if(frm.totalCnt.value==frm.checkCnt.value){
    			document.all('rexportbutton').disabled=false;
    		}else{
    			document.all('rexportbutton').disabled=true;
    		}

    	}

    }

</SCRIPT>
     <form:form commandName="recoveryVO" name="recoveryPageListForm" method="post" action="" >
      <c:if test="${strAuth != '03'}">
      <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span> 전체건수 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryState.totalCnt}" /></span> 
      <span style="color:blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#FF9900">[대기] :</font> <a href="javascript:stateSearch('01')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryState.waitCnt}" /></a>
      &nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#FF9900">[발신] :</font> <a href="javascript:stateSearch('02')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryState.sendCnt}" /></a>    
	  &nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#FF9900">[수신] :</font> <a href="javascript:stateSearch('03')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryState.reciveCnt}" /></a>     
	  &nbsp;&nbsp;&nbsp;&nbsp;<font style="color:#FF9900">[완료] :</font> <a href="javascript:stateSearch('04')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryState.checkCnt}" /></a>
	  </span></p>   
	  </c:if> 
	  <input type="hidden" id="totalCnt" name="totalCnt" value="${recoveryState.totalCnt}">
	  <input type="hidden" id="waitCnt" name="waitCnt" value="${recoveryState.waitCnt}">
	  <input type="hidden" id="sendCnt" name="sendCnt" value="${recoveryState.sendCnt}">
	  <input type="hidden" id="reciveCnt" name="reciveCnt" value="${recoveryState.reciveCnt}">
	  <input type="hidden" id="checkCnt" name="checkCnt" value="${recoveryState.checkCnt}">
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'>진행상태</th>
            <th class='text-center'>회수번호</th>
            <th class='text-center'>매장명</th>
            <th class='text-center'>회수수량</th>
            <th class='text-center'>회수금액</th>
            <th class='text-center'>수신확인</th>
	      </tr>
	    </thead>
	    <tbody>
	    	<c:if test="${!empty recoveryList}">
             <c:forEach items="${recoveryList}" var="recoveryVO" varStatus="status">
             <tr id="select_tr_${recoveryVO.recoveryStateView}">
                 <input type="hidden" id="recoveryState" name="recoveryState" value="${recoveryVO.recoveryState}">
                 <td class='text-center'><c:out value="${recoveryVO.recoveryStateView}"></c:out></td>
                 <td><a href="javascript:fcRecovery_detail('${recoveryVO.recoveryCode}','${recoveryVO.groupId}','${recoveryVO.groupName}','${recoveryVO.recoveryState}','${recoveryVO.collectDateTime}','${recoveryVO.recoveryClosingDate}')"><c:out value="${recoveryVO.recoveryCode}"></c:out></a></td>
                 <td><c:out value="${recoveryVO.groupName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryResultCnt}"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryResultPrice}"/></td>
                 <td class='text-center'>
                 <c:if test="${recoveryVO.recoveryState=='02' && strAuth!='03'}">
                 <button type="button" id="receivebtn" class="btn btn-success" onClick="alert('수신확인 처리 개발중');">수신</button>
                 </c:if>
                 </td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty recoveryList}">
              <tr>
                  <td colspan='6' class='text-center'>조회된 데이터가 없습니다.</td>
              </tr>
          </c:if>
	    </tbody>
	  </table>
	 </form:form>
<script>
//alert('${strAuth}');
stateCheck();
</script>