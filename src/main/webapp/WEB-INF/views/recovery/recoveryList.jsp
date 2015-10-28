<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>

    // 회수 상세 페이지 리스트 Layup
    function fcRecovery_detail(recoveryCode,groupId,groupName,recoveryState,regDateTime,recoveryClosingDate,totalCnt,receiveCnt,checkCnt) {
   
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
                $(this).load(url+'?recoveryCode='+recoveryCode+'&totalCnt='+totalCnt+'&receiveCnt='+receiveCnt+'&checkCnt='+checkCnt+'&groupId='+groupId+'&groupName='+encodeURIComponent(groupName)+
                		'&recoveryState='+recoveryState);
             //   $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
             //       $("#recoveryDetailView").dialog('close');

             //       });
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
    function stateSearch(state){
    	
    	document.recoveryConForm.con_recoveryState.value=state;
    	//alert(document.targetConForm.con_orderState.value);
    	fcRecovery_listSearch();
    }
    
    function stateCheck(){

    	if('${strAuth}' != '03' || '${strAuthId}' =='AD001'){
    		
    		var frm=document.recoveryPageListForm;
   
    		if('${strAuth}' != '03'){
	    		if(frm.totalCnt.value==frm.waitCnt.value){
	    			document.all('rcancelbtn').disabled=false;
	    			$('#rcancelbtn').attr("style","display:inline");
	    		}else{
	    			document.all('rcancelbtn').disabled=true;
	    			$('#rcancelbtn').attr("style","display:none");
	    		}
    		}
    		
    		if(frm.totalCnt.value==frm.checkCnt.value){
    			document.all('rexportbutton').disabled=false;
    			$('#rexportbutton').attr("style","display:inline");
    			
    			document.all('transbutton').disabled=false;
    			$('#transbutton').attr("style","display:inline");
    			
    			document.all('returnbutton').disabled=false;
    			$('#returnbutton').attr("style","display:inline");
    			
    		}else{
    			document.all('rexportbutton').disabled=true;
    			$('#rexportbutton').attr("style","display:none");
    			
    			document.all('transbutton').disabled=true;
    			$('#transbutton').attr("style","display:none");
    			
    			document.all('returnbutton').disabled=true;
    			$('#returnbutton').attr("style","display:none");
    		}

    	}

    }

    function fcRecovery_receive(recoveryCode,groupId){
    	
    	 if (confirm('발신된 회수건을 수신확인 상태로 처리하시겠습니까?')){ 

 	 		$.ajax({
 		       type: "POST",
 		       async:false,
 		          url:  "<%= request.getContextPath() %>/recovery/receiveprocess?recoveryCode="+recoveryCode+"&groupId="+groupId,
 		           success: function(result) {
 		
 		        	resultMsg(result);
 		
 		        	fcRecovery_listSearch();
 						
 		          },
 		          error:function(){
 		
 		          alert('호출오류!');
 		          fcRecovery_listSearch();
 			     
 		          }
 		    });
 	   }
    }
</SCRIPT>
     <form:form commandName="recoveryVO" name="recoveryPageListForm" method="post" action="" >
      <input type="hidden" name="collectCode" id="collectCode" value="${recoveryConVO.collectCode}">
      <c:if test="${strAuth != '03' || strAuthId=='AD001'}">
      <br>
	  <table style="width:530px" class="table table-bordered tbl_type" >
	     <colgroup>
	      <col width="80px" >
	      <col width="50px" >
	      <col width="50px" >
	      <col width="50px">
	      <col width="50px" >
	      <col width="50px" >
	      <col width="50px" >
	      <col width="50px">
	      <col width="50px" >
	      <col width="50px" >
	     </colgroup>
	     <tr>
	     	<td class='text-center' style="background-color:#E6F3FF">전체건수</td>
	     	<td class='text-right'><a href="javascript:stateSearch('')"><span style="color:red">
	          <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryState.totalCnt}" />
	          </span></a></td>
	     	<td class='text-center' style="background-color:#E6F3FF">대기</td>
	     	<td class='text-right'><a href="javascript:stateSearch('01')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryState.waitCnt}" /></a></td>
	     	<td class='text-center' style="background-color:#E6F3FF">발신</td>
	     	<td class='text-right'><a href="javascript:stateSearch('02')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryState.sendCnt}" /></a></td>
	     	<td class='text-center' style="background-color:#E6F3FF">수신</td>
	     	<td class='text-right'><a href="javascript:stateSearch('03')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryState.receiveCnt}" /></a></td>
	     	<td class='text-center' style="background-color:#E6F3FF">완료</td>
	     	<td class='text-right'><a href="javascript:stateSearch('04')"><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryState.checkCnt}" /></a></td>
	     </tr>
     </table>
	  </c:if> 
	  <input type="hidden" id="totalCnt" name="totalCnt" value="${recoveryState.totalCnt}">
	  <input type="hidden" id="waitCnt" name="waitCnt" value="${recoveryState.waitCnt}">
	  <input type="hidden" id="sendCnt" name="sendCnt" value="${recoveryState.sendCnt}">
	  <input type="hidden" id="receiveCnt" name="receiveCnt" value="${recoveryState.receiveCnt}">
	  <input type="hidden" id="checkCnt" name="checkCnt" value="${recoveryState.checkCnt}">
	  <table class="table table-bordered">
	    <thead>
	      <tr style="background-color:#E6F3FF">
	        <th class='text-center'>진행상태</th>
	        <th class='text-center'>진행일시</th>
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
                 <td class='text-center'><c:out value="${recoveryVO.stateDateTime }"></c:out></td>
                 <td><a href="javascript:fcRecovery_detail('${recoveryVO.recoveryCode}','${recoveryVO.groupId}','${recoveryVO.groupName}','${recoveryVO.recoveryState}',
                 '${recoveryVO.collectDateTime}','${recoveryVO.recoveryClosingDate}','${recoveryState.totalCnt}','${recoveryState.receiveCnt}','${recoveryState.checkCnt}')"><c:out value="${recoveryVO.recoveryCode}"></c:out></a></td>
                 <td><c:out value="${recoveryVO.groupName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryResultCnt}"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${recoveryVO.recoveryResultPrice}"/></td>
                 <td class='text-center'>
                 <c:if test="${recoveryVO.recoveryState=='02' && (strAuth!='03' || strAuthId=='AD001')}">
                 <button type="button" id="receivebtn" class="btn btn-xs btn-success" onClick="fcRecovery_receive('${recoveryVO.recoveryCode}','${recoveryVO.groupId}');">수신</button>
                 </c:if>
                 </td>
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
<script>
//alert('${strAuth}');
stateCheck();
</script>