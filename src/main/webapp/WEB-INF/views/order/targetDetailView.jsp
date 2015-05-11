<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
 

function tmt_winLaunch(theURL,winName,targetName,features) {
	
	var targetRandom=Math.random();
	eval(winName+"=window.open('"+theURL+"','"+targetRandom+"','"+features+"')");

}
/*
 * print 화면 POPUP
 */
function fcTargetDetail_print(){
	
	var h=800;
	var s=950;

    tmt_winLaunch('<%= request.getContextPath()%>/order/targetdetailprint' , 'orderPrintObj', 'orderPrintObj', 'resizable=no,status=no,location=no,menubar=no,toolbar=no,width='+s+',height ='+h+',left=0,top=0,resizable=yes,scrollbars=yes');
	
}
//발주처리
function fcOrder_process(){
	
	var frm=document.targetDetailForm;
	var emailCheckCnt = $('input:checkbox[ name="emailCheck"]:checked').length;
	var smsCheckCnt = $('input:checkbox[ name="smsCheck"]:checked').length;

	if(emailCheckCnt > 0){
		
		frm.emailKey.value='Y';
		
		if(frm.deliveryEmail.value==''){
			
			alert('발주 대상 이메일 주소가 없습니다.');
			return;
		}
		
		if(frm.deliveryEmail.value != 'kevin.jeon@offact.com'){
			if(frm.deliveryEmail.value != 'soyung.shin@offact.com'){
				if(frm.deliveryEmail.value != 'patrick.park@offact.com'){
					alert('open이전까진 offact계정이외 메일전송 서비스가 불가합니다.');
					return;
				}
			}
		}
		
		if(smsCheckCnt > 0){
			
			if(frm.deliveryMobile.value==''){
				
				alert('발주 대상 sms 번호가 없습니다.');
				return;
			}
			
			if(!confirm("발주 처리 하시겠습니까?\n상품 주문서는 이메일["+frm.deliveryEmail.value+"] 과\nSMS ["+frm.deliveryMobile.value+"] 로 전송됩니다."))
				return;
			
		}else{
			
			frm.smsKey.value='N';
			
			if(!confirm("발주 처리 하시겠습니까?\n상품 주문서는 이메일["+frm.deliveryEmail.value+"]로 전송됩니다."))
				return;
		}

    }
	
    $.ajax({
        type: "POST",
        async:false,
           url:  "<%= request.getContextPath() %>/order/orderProcess",
           data:$("#targetDetailForm").serialize(),
           success: function(result) {

				if(result=='true'){
					 alert('발주 처리를 성공했습니다.');
				} else{
					 alert('발주 처리를 실패했습니다.');
				}
				
				$('#targetDetailView').dialog('close');
				fcTarget_listSearch();
				
           },
           error:function(){
        	   
        	   alert('발주 처리를 실패했습니다.');
        	   $('#targetDetailView').dialog('close');
           }
    });
}

$(function() {
    $( "#deferdialog" ).dialog({
      modal : true, //주위를 어둡게
      autoOpen: false,
      show: {
        effect: "blind",
        duration: 1000
      },
      hide: {
        effect: "explode",
        duration: 1000
      }
    });

    $( "#deferbtn" ).click(function() {
      $( "#deferdialog" ).dialog( "open" );
    });
  });

   $(function() {
      $( "#deferpopclosebtn" ).click(function() {
        $( "#deferdialog" ).dialog( "close" );
      });
    });


function fcDefer_regist(){

    	if($("#defer_reason_div").val()==''){
    		alert('보류사유를 입력하세요!');
    		return;
    	}else{
    		
    		var checkedCnt = $('input:checkbox[ name="deferCheck"]:checked').length;

        	if(checkedCnt <= 0){
            	alert("보류 대상을 선택해 주세요!");
            	return;
            }
            
            var arrDeferProductId = "";
            $('input:checkbox[name="deferCheck"]').each(function() {
                if ($(this).is(":checked")) {
                	arrDeferProductId += $(this).val() + "^";
                }   
            });
            document.targetDetailForm.defer_reason.value=$("#defer_reason_div").val();
            var paramString = $("#targetDetailForm").serialize() + "&arrDeferProductId="+arrDeferProductId;

	  		$.ajax({
		       type: "POST",
		       async:false,
		          url:  "<%= request.getContextPath() %>/order/deferregist",
		          data:paramString,
		          success: function(result) {
	
					if(result=='1'){
							alert('보류처리를 성공했습니다.');
					} else{
							alert('보류처리를 실패했습니다.');
					}

					$('#deferdialog').dialog('close');
					$('#targetDetailView').dialog('close');
					fcTarget_listSearch();
						
		          },
		          error:function(){

		          $('#deferdialog').dialog('close');
				  $('#targetDetailView').dialog('close');
			      fcTarget_listSearch();
		          }
		    });
    	}	
	}
    
    function totalOrderAmt(){
    	
    	
    	var amtCnt = $('input:text[ name="productPrice"]').length;
    	
    	var supplyamt=0;
    	var vatamt=0;
    	var totalamt=0;

    	
    	  totalamt=supplyamt+vatamt;
    	
    	  document.all('totalOrderAmt').innerText='함계 : '+addCommaStr(''+totalamt)+' 원'+'공급가 : '+addCommaStr(''+supplyuamt)+' 원'+'부가세 : '+addCommaStr(''+vatamt)+' 원';
    }
    
</SCRIPT>
	<div class="container-fluid">
	 <div class="form-group" >
	 <form:form commandName="targetVO" id="targetDetailForm"  name="targetDetailForm" method="post" action="" >
	   <input type="hidden" name="emailKey"             id="emailKey"            value="Y" />
	   <input type="hidden" name="smsKey"               id="smsKey"            value="N" />
	   <input type="hidden" name="faxKey"               id="faxKey"            value="N" />
	   <input type="hidden" name="defer_reason"               id="defer_reason"            value="" />
	   <input type="hidden" name="groupId"               id="groupId"            value="${targetVO.groupId}" />
	   <input type="hidden" name="companyCode"               id="companyCode"            value="${targetVO.orderCode}" />
	      <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-check"></span> 발주방법 : </font></strong>
	          <input type="checkbox" id="emailCheck" name="emailCheck" value="" title="선택" checked disabled />e-mail
	          <input type="checkbox" id="smsCheck" name="smsCheck" value="" title="선택" disabled />sms
	      </h4>
	      <tr>
	      <div style="position:absolute; left:30px" > 
	      <c:if test="${targetVO.orderCode != 'X'}"><button id="deferbtn" type="button" class="btn btn-primary" >보류</button></c:if>
	      <c:if test="${targetVO.orderCode == 'X'}"><button type="button" class="btn btn-primary" onClick="fcDefer_modify('${targetVO.orderCode}')">보류수정</button></c:if>
	      <c:if test="${targetVO.orderCode == 'X'}"><button type="button" class="btn btn-danger" onClick="fcDefer_cancel('${targetVO.orderCode}')">보류폐기</button></c:if>
	      <c:if test="${targetVO.orderCode == 'X'}"><button type="button" class="btn btn-primary" onClick="fcDefer_Reason('${targetVO.orderCode}')">보류사유</button></c:if>
          </div >
          <div id="deferdialog" class="form-group" title="보류사유를 입력하세요">
			<p><input type="text" class="form-control" id="defer_reason_div" name="defer_reason_div"  value=""  placeholder="보류사유"/></p>
			<button id="defersavebtn" type="button" class="btn btn-primary" onClick="fcDefer_regist()">save</button> <button id="deferpopclosebtn" type="button" class="btn btn-danger">cancel</button>
          </div>
          <div style="position:absolute; right:30px" > 
          <button type="button" class="btn btn-primary" onClick="fcTargetDetail_print()">출력</button>
          <button type="button" class="btn btn-primary" onClick="fcOrder_process()">발주</button>
          </div>
          </tr>
          <br><br>
	  <table class="table table-bordered" >
	 	<tr>
          <th rowspan='9' class='text-center' style="background-color:#E6F3FF">수신</th>
          <th class='text-center'  style="background-color:#E6F3FF" >수신</th>
          <th class='text-center'><input type="text" class="form-control" id="companyName" name="companyName"  value="${targetVO.companyName}(${targetVO.deliveryCharge})" placeholder="수신" /></th>
          <th rowspan='9' class='text-center'  style="background-color:#E6F3FF">발신</th>
          <th class='text-center' style="background-color:#E6F3FF">발신</th>
          <th class='text-center'><input type="text" class="form-control" id="groupName" name="groupName"  value="ADDYS ${targetVO.groupName}" placeholder="발신"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF" >참조</th>
          <th class='text-center'><input type="text" class="form-control" id="deleveryEtc" name=""deleveryEtc""  value="물류팀" placeholder="참조" /></th>
          <th class='text-center' style="background-color:#E6F3FF" >참조</th>
          <th class='text-center'><input type="text" class="form-control" id="orderUserName" name="orderUserName"  value="${targetVO.orderUserName}" placeholder="참조" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">핸드폰</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryMobile" name="deliveryMobile"  value="${targetVO.deliveryMobile}"  placeholder="핸드폰"/></th>
          <th class='text-center' style="background-color:#E6F3FF">핸드폰</th>
          <th class='text-center'><input type="text" class="form-control" id="mobilePhone" name="mobilePhone"  value="${targetVO.mobilePhone}"  placeholder="핸드폰"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">e-mail</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryEmail" name="deliveryEmail"  value="${targetVO.deliveryEmail}" placeholder="e-mail" /></th>
          <th class='text-center' style="background-color:#E6F3FF">e-mail</th>
          <th class='text-center'><input type="text" class="form-control" id="email" name="email"  value="${targetVO.email}" placeholder="e-mail" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">tel</th>
          <th class='text-center'><input type="text" class="form-control" id="phone" name="phone"  value="${targetVO.deliveryTel}" placeholder="tel" /></th>
          <th class='text-center' style="background-color:#E6F3FF">tel</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryTel" name="deliveryTel"  value="${targetVO.phone}" placeholder="tel" /></th>
      	</tr>
      	<th class='text-center' style="background-color:#E6F3FF">fax</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryFax" name="deliveryFax"  value="${targetVO.deliveryFax}" placeholder="fax" /></th>
          <th class='text-center' style="background-color:#E6F3FF">fax</th>
          <th class='text-center'><input type="text" class="form-control" id="faxNumber" name="faxNumber"  value="${targetVO.faxNumber}" placeholder="fax" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">발주일자</th>
          <th class='text-center'>
          	
          		<div style='width:150px' class='input-group date ' id='datetimepicker1' data-link-field="orderDateTime" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${targetVO.orderDateTime}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="orderDateTime" name="orderDateTime" value="${targetVO.orderDateTime}" />
	            </div>
	         
          </th>
          <th rowspan='2' class='text-center' style="background-color:#E6F3FF">배송주소</th>
          <th rowspan='2' class='text-center'><textarea style='height:82px'  class="form-control" row="2" id="address" name="address" >서울특별시 영등포구 여의도동 54-6 영창빌딩 6층 물류팀</textarea></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">납품일자</th>
          <th class='text-center'>
          		
          		<div  style='width:150px' class='input-group date ' id='datetimepicker2' data-link-field="deliveryDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${targetVO.deliveryDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="deliveryDate" name="deliveryDate" value="${targetVO.deliveryDate}" />
	            </div>
             
          </th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">납품방법</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryMethod" name="deliveryMethod"  value="택배배송" placeholder="납품방버" /></th>
          <th class='text-center' style="background-color:#E6F3FF">결재방법</th>
          <th class='text-center'><input type="text" class="form-control" id="approvedMethod" name="approvedMethod"  value="입금지정일.현금" placeholder="결재방법" /></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#E6F3FF">SMS내용</th>
          <th colspan='4' class='text-center'><input type="text" class="form-control" id="sms" name="sms"  value="${targetVO.deliveryCharge}님 ADDYS ${targetVO.groupName}에서 발주서를 보냈습니다.당일처리 부탁드립니다." placeholder="SMS" /></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#E6F3FF">메모</th>
          <th colspan='4' class='text-center'><input type="text" class="form-control" id="memo" name="memo"  value="" placeholder="메모" /></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
	 
     <form:form commandName="targetListVO" name="targetDetailListForm" method="post" action="" >
      <p> <span class="glyphicon glyphicon-asterisk"></span> 
          <span id="totalOrderAmt" style="color:#FF9900">
                    합계 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${targetVO.orderPrice}" />
                    공급가 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${targetVO.productPrice}" />
                    부가세 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${targetVO.vat}" />
        </span>
      </p>       
	  <table class="table table-bordered" >
      	<tr style="background-color:#E6F3FF">
          <th rowspan='2' class='text-center' >보류</th>
          <th rowspan='2' class='text-center'>품목코드</th>
          <th rowspan='2' class='text-center'>상품명</th>
          <th colspan='4' class='text-center'>발주</th>
          <th colspan='3' class='text-center'>재고</th>
          <th rowspan='2' class='text-center'>비고</th>
      	</tr>
      	<tr style="background-color:#E6F3FF">
          <th class='text-center'>기준단가</th>
          <th class='text-center'>수량</th>
          <th class='text-center'>+</th>
          <th class='text-center'>-</th>
          <th class='text-center'>안전</th>
          <th class='text-center'>보유</th>
          <th class='text-center'>전산</th>
      	</tr>
	    	<c:if test="${!empty targetDetailList}">
             <c:forEach items="${targetDetailList}" var="targetVO" varStatus="status">
             <tr id="select_tr_${targetVO.productCode}">
                 <td><input type="checkbox" id="deferCheck" name="deferCheck" value="${targetVO.productCode}" title="선택" /></td>
                 <td class='text-center'><c:out value="${targetVO.productCode}"></c:out></td>
                 <td class='text-left'><c:out value="${targetVO.productName}"></c:out></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${targetVO.productPrice}" /></td>
                 <input type="hidden" id="productPrice" name="productPrice" value="${targetVO.productPrice}" >
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${targetVO.orderCnt}"/></td>
                 <input type="hidden" id="orderCnt" name="orderCnt" value="${targetVO.orderCnt}" >
                 <input type="hidden" id="vatRate" name="vatRate" value="${targetVO.vatRate}" >
                 <td class='text-right'><c:if test="${strAuth != '03'}"><input style="width:35px" type="text" class="form-control" id="addcnt" name="addcnt" value="0"></c:if></td>
                 <td class='text-right'><c:if test="${strAuth != '03'}"><input style="width:35px" type="text" class="form-control" id="losscnt" name="losscnt" value="0"></c:if></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${targetVO.safeStock}"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${targetVO.holdStock}"/></td>
                 <td class='text-right'><f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="${targetVO.stockCnt}"/></td>
                 <td class='text-right'><c:out value=""></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty targetDetailList}">
           <tr>
           	<td colspan='11' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	  </table>
	 </form:form>
	</div>
	<script type="text/javascript">

    $(function () {
        $('#datetimepicker1').datetimepicker(
        		{
                	language:  'kr',
                    format: 'yyyy-mm-dd',
                    weekStart: 1,
                    todayBtn:  1,
            		autoclose: 1,
            		todayHighlight: 1,
            		startView: 2,
            		minView: 2,
            		forceParse: 0
                });
        $('#datetimepicker2').datetimepicker(
        		{
                	language:  'kr',
                    format: 'yyyy-mm-dd',
                    weekStart: 1,
                    todayBtn:  1,
            		autoclose: 1,
            		todayHighlight: 1,
            		startView: 2,
            		minView: 2,
            		forceParse: 0
                });
    });
    
    totalOrderAmt();
</script>