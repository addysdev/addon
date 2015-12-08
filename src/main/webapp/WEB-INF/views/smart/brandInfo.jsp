<%@ include file="/WEB-INF/views/addys/base.jsp" %>
 <style>

 .thead { height:32px; overflow:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody { height:430px; .height:420px; overflow-y:scroll; overflow-x:hidden; border:1px solid #dcdcdc; border-bottom:none; border-top:none; }
 .tbody_evScore {height:530px;}
 .tbl_type {width:100%;border-bottom:1px solid #dcdcdc;text-align:center; table-layout:fixed;border-collapse:collapse;word-break:break-all;}
 .tbl_type td { padding:6px 0px; }

</style>
<SCRIPT>
   
function fcAs_Save(){
	
	var frm=document.asRegistForm;

	if(frm.asMethod[0].checked==true){
		
		frm.asState.value='01';
		
		if(frm.asResult.value==''){
			alert('처리내용을 입력하시기 바랍니다.');
			frm.asResult.focus(1);
			return;
		}
		
		if (confirm('처리상태를 환불처리로 저장 하시겠습니까?')){
			
			$.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/smart/asstateupdate",
		           data:$("#asRegistForm").serialize(),
		           success: function(result) {

						if(result=='1'){
							 alert('처리방법 저장을 성공했습니다.');
						} else{
							 alert('처리방법 저장을 실패했습니다.');
						}
						
						$('#brandInfo').dialog('close');
						$('#asSearchForm').dialog('close');
						fcAs_listSearch();
						
		           },
		           error:function(){
		        	   
		        	   alert('처리방법 저장을 실패했습니다.');
		        	   $('#brandInfo').dialog('close');
		        	   $('#asSearchForm').dialog('close');
		           }
		    });
			
		}
		
	}else if(frm.asMethod[1].checked==true){
		
		frm.asState.value='02';
		
		if(frm.asResult.value==''){
			alert('처리내용을 입력하시기 바랍니다.');
			frm.asResult.focus(1);
			return;
		}
		
		if (confirm('처리상태를 센터안내로 저장 하시겠습니까?')){
			
			$.ajax({
		        type: "POST",
		        async:false,
		           url:  "<%= request.getContextPath() %>/smart/asstateupdate",
		           data:$("#asRegistForm").serialize(),
		           success: function(result) {

		        	    if(result=='1'){
							 alert('처리방법 저장을 성공했습니다.');
						} else{
							 alert('처리방법 저장을 실패했습니다.');
						}
						
						$('#brandInfo').dialog('close');
						$('#asSearchForm').dialog('close');
						fcAs_listSearch();
						
		           },
		           error:function(){
		        	   
		        	   alert('처리방법 저장을 실패했습니다.');
		        	   $('#brandInfo').dialog('close');
		        	   $('#asSearchForm').dialog('close');
		           }
		    });
		}
		
	}else if(frm.asMethod[2].checked==true){
		
		frm.asState.value='03';
		
		if(frm.customerKey.value==''){
			alert('핸드폰번호(고객키)를 입력하시기 바랍니다.');
			frm.customerKey.focus(1);
			return;
		}
		
		var customerKey=frm.customerKey.value;
		var brandCode=frm.brandCode.value;
		var brandName=frm.brandName.value;
		var productCode=frm.productCode.value;
		var productName=frm.productName.value;
		var asPolicy=frm.asPolicy.value;
	
		if (confirm('A/S접수를 진행 하시겠습니까?')){
			
			$('#brandInfo').dialog('close');
			$('#asSearchForm').dialog('close');

			var url='<%= request.getContextPath() %>/smart/asregistform?customerKey='+customerKey+'&brandCode='+brandCode+'&brandName='+encodeURIComponent(brandName)+'&productCode='+productCode+'&productName='+encodeURIComponent(productName)+'&asPolicy='+encodeURIComponent(asPolicy);
			
			$('#asRegForm').dialog({
	            resizable : false, //사이즈 변경 불가능
	            draggable : true, //드래그 불가능
	            closeOnEscape : true, //ESC 버튼 눌렀을때 종료
	
	            width : 950,
	            height : 850,
	            modal : true, //주위를 어둡게
	
	            open:function(){
	                //팝업 가져올 url
	                $(this).load(url);
	               
	                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
	                    $("#asRegForm").dialog('close');
	
	                    });
	            }
	            ,close:function(){
	                $('#asRegForm').empty();
	            }
	        });
		
		}
	}
	
	
}

function radioSelect(){
	
	var frm=document.asRegistForm;

	if(frm.asMethod[0].checked==true){
		frm.asResult.disabled=false;
	}else if(frm.asMethod[1].checked==true){
		frm.asResult.disabled=false;
	}else if(frm.asMethod[2].checked==true){
		frm.asResult.disabled=true;
	}

}
    
</SCRIPT>
<!-- 사용자관리 -->
<div class="container-fluid">     
     <div class="form-group" >
	 <form:form commandName="brandVO" name="brandInfoForm" id="brandInfoForm" method="post" action="" >
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-left' style="background-color:#E6F3FF" >1.[${brandVo.brandName}] (브랜드)A/S정책</th>
      	</tr>
      	<tr>
          <th class='text-left' style="width:572px">
          ${brandVo.asPolicy}<br><br><br>
          </th>
        </tr>
      	<tr>
          <th class='text-left' style="background-color:#E6F3FF" >2.안내방법</th>
      	</tr>
      	<tr>
      	  <th class='text-left' style="width:572px">
          ${brandVo.guide}<br><br><br>
          </th>
        </tr>
      	<tr>
          <th class='text-left' style="background-color:#E6F3FF" >3.센터상세정보</th>
      	</tr>
      	<tr>
      	  <th class='text-left' style="width:572px">
          ${brandVo.centerGuide}<br><br><br>
          </th>
        </tr>
        <tr>
          <th class='text-left' style="background-color:#E6F3FF" >4.[${productName}] (제품)A/S정책</th>
      	</tr>
        <tr>
          <th class='text-left' style="width:572px">
          ${productVo.asPolicy}<br><br><br>
          </th>
        </tr>
	  </table>
	  </form:form>
	  
	 <form:form commandName="asVO" name="asRegistForm" id="asRegistForm" method="post" action="" >
	 <input type="hidden" id="brandCode" name="brandCode" value="${brandVo.brandCode}" >
	 <input type="hidden" id="brandName" name="brandName" value="${brandVo.brandName}" >
	 <input type="hidden" id="asPolicy" name="asPolicy" value="${brandVo.asPolicy}" >
	 <input type="hidden" id="productCode" name="productCode" value="${productCode}" >
	 <input type="hidden" id="productName" name="productName" value="${productName}" >
	 <input type="hidden" id="asState" name="asState" value="" >
	  <table class="table table-bordered" >
	 	<tr>
          <th class='text-left' style="background-color:#E6F3FF" >처리방법</th>
          <th class='text-left'>
          <input type="radio" name="asMethod" id="asMethod" value="01" checked onChange="radioSelect()"> 환불 <input type="radio" name="asMethod" id="asMethod" value="02" onChange="radioSelect()"> 센터안내 <input type="radio" name="asMethod" id="asMethod" value="03" onChange="radioSelect()"> 접수 
           &nbsp;<button id="regbtn" type="button" class="btn btn-primary" onClick="fcAs_Save()">저장</button> 
          </th>
        </tr>
        <tr>
          <th class='text-left' style="background-color:#E6F3FF" >핸드폰번호(고객키)</font></th>
          <th class='text-left'>
          <input type="text" class="form-control" id="customerKey" name="customerKey" maxlength="15" style="width:120px"  tabindex="2" value="" >
          </th>
      	</tr>
	 	<tr>
          <th class='text-left' style="background-color:#E6F3FF" >처리내용</th>
          <th class='text-left'>
          <textarea style='width:455px;height:70px;ime-mode:active;' row="6" class="form-control" id="asResult" maxlength="1000" name="asResult"  value=""  ></textarea>
          </th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
</div >
