<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
// 품목조회 리스트 Layup
function fcProduct_list() {
	
	//$('#targetEtcView').attr('title',productName);
	var url='<%= request.getContextPath() %>/master/productsearch';

	$('#recoveryProductList').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 600,
        height : 600,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
          //  $(this).load(url+'?orderCode='+orderCode+'&productCode='+productCode+'&productNaem='+encodeURIComponent(productName));
            $(this).load(url);
           
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#recoveryProductList").dialog('close');

                });
        }
        ,close:function(){
            $('#recoveryProductList').empty();
        }
    });
};
function fcRcovery_regist(){

	var frm=document.recoveryProductListForm;
	var rfrm=document.reProductForm;
	
	 var arrCheckGroupId = "";
	 var arrSelectProductId = "";
	 
	 var now = new Date(); // The current date and time
	 var month = now.getMonth()+1;
	 var day=now.getDate();
	 
	 if(month<10){
		 month='0'+month;
	 }
	 
	 if(day<10){
		 day='0'+day;
	 }

	 var today=now.getFullYear()+'-'+month+'-'+day;
	// alert(today);
	 //alert(rfrm.recoveryClosingDate.value);
	 
	if(rfrm.recoveryClosingDate.value<=today) {
		
		alert('회수 마감일은 오늘날짜 이전으로는 선택 하실 수 없습니다.');
		return;
	}
	
	if(rfrm.regroupid!=undefined){

        $('input:checkbox[name="regroupid"]').each(function() {
            if ($(this).is(":checked")) {
           	 arrCheckGroupId += $(this).val() + "^";
            }   
        });
        
	}else{
		
		alert('선택하신 회수 대상지점이 없습니다.');
		return;
	}
	
	if(frm.selectProduct!=undefined){
		
		if(frm.selectProduct.length>1){
			
			for (i=0;i<frm.selectProduct.length;i++){
				arrSelectProductId += frm.selectProduct[i].value + "^";
			}
		}else{		
			arrSelectProductId += frm.selectProduct.value + "^";
		}
	}else{
		
		alert('선택하신 품목코드가 없습니다.');
		return;
	}
	
	//alert(arrCheckGroupId);
	//alert(arrSelectProductId);
	//return;
	if (confirm('회수처리를 진행 하시겠습니까?')){ 
	
		   var paramString = $("#reProductForm").serialize()+ "&arrCheckGroupId="+arrCheckGroupId+'&arrSelectProductId='+arrSelectProductId;

	  		$.ajax({
		       type: "POST",
		       async:false,
		          url:  "<%= request.getContextPath() %>/recovery/recoveryregist",
		          data:paramString,
		          success: function(result) {
	
		        	resultMsg(result);

					$('#recoveryRegForm').dialog('close');
					fcCollect_listSearch();
						
		          },
		          error:function(){
		          
		          alert('회수 등록 호출오류!');
                  $('#recoveryRegForm').dialog('close');
                  fcCollect_listSearch();
		          }
		    });
	}
}

function fcProduct_Select(productcode,productname,recoveryyn){
	
	var frm=document.recoveryProductListForm;
	
	if(recoveryyn=='Y'){
		alert('회수중인 품목은 선택하실 수 없습니다.');
		return;
	}
	
	if(frm.selectProduct!=undefined){
		
		if(frm.selectProduct.length>1){
			
			for (i=0;i<frm.selectProduct.length;i++){
				if(frm.selectProduct[i].value==productcode){
					alert('이미 선택하신 품목코드 입니다.');
					return;
				}
			}
		}else{
			
			if(frm.selectProduct.value==productcode){
				alert('이미 선택하신 품목코드 입니다.');
				return;
			}
		}
	}
	
	var rowCnt = contentId.rows.length;
	var newRow = contentId.insertRow( rowCnt++ );
	newRow.onmouseover=function(){contentId.clickedRowIndex=this.rowIndex};
	var newCell = newRow.insertCell();
	newCell.innerHTML ='<tr><input type="hidden" id="selectProduct" name="selectProduct" value='+productcode+'><td class="text-center">['+productcode+']'+productname+'&nbsp;<button type="button" class="btn btn-xs btn-info" onClick="delFile(this)" >삭제</button></td></tr>';
	
	totalAttachCnt('add');
	
}
function totalAttachCnt(flag){

	var totalcnt=0;
	var frm = document.recoveryProductListForm;
	
	if(flag=='add'){
		//alert(frm.selectProduct.length);
		if(frm.selectProduct.length!=undefined ){
			totalcnt=frm.selectProduct.length;
		}else{
			totalcnt=1;	
		}
	}else{
		if(frm.selectProduct!=undefined ){
			totalcnt=frm.selectProduct.length;
		}else{
			totalcnt=0;
		}
	}
	
	document.all('totalAttachCnt').innerText='선택품목 건수 :'+addCommaStr(''+totalcnt)+' 건';
	
}

function delFile(obj){ 
    var tr = obj // A 
             .parentNode // TD 
             .parentNode; // TR 
    var table = tr.parentNode; 
    var index = tr.rowIndex; 
    table.deleteRow(index-1); 
    
    totalAttachCnt('del');
} 
//품목조회 리스트 Layup
/*
function fcReProduct_excelForm() {
	
	//$('#targetEtcView').attr('title',productName);
	var url='<%= request.getContextPath() %>/master/productsearch';

	$('#recoveryProductList').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 600,
        height : 300,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
          //  $(this).load(url+'?orderCode='+orderCode+'&productCode='+productCode+'&productNaem='+encodeURIComponent(productName));
            $(this).load(url);
           
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#recoveryProductList").dialog('close');

                });
        }
        ,close:function(){
            $('#recoveryProductList').empty();
        }
    });
};
*/
//레이어팝업 : 보유재고 Layer 팝업
function fcReProduct_excelForm(){

	$('#reProductExcelForm').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 430,
        height : 300,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('<%= request.getContextPath() %>/recovery/reproductexcelform');
            //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#reProductExcelForm").dialog('close');

                });
        }
        ,close:function(){
            $('#reProductExcelForm').empty();
        }
    });
};
// 리스트 조회
function fcReProduct_registlist(){

     $.ajax({
         type: "POST",
            url:  "<%= request.getContextPath() %>/recovery/recoveryregislist",
                 data:$("#reProductForm").serialize(),
            success: function(result) {
                commonDim(false);
                $("#recoveryRegisList").html(result);
            },
            error:function() {
            	
            }
     });
}

//체크박스 전체선택
function fcGroup_checkAll(){
	
	$("input:checkbox[id='regroupid']").prop("checked", $("#regroupidCheckAll").is(":checked"));

}
//--> 

</SCRIPT>
<!-- 사용자관리 -->
<body>
  <div class="container-fluid">
	<h5><strong><font style="color:#428bca"><span class="glyphicon glyphicon-book"></span>회수관리&nbsp; 
   		<button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fcProduct_list()" >회수 대상품목 추가</button>
   		<button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fcReProduct_excelForm()" >회수 대상품목 엑셀추가</button>
   		<button id="memoinfobtn" type="button" class="btn btn-xs btn-primary" onClick="fcRcovery_regist()" >회수요청</button>
    	</font></strong></h5>
		  <form:form commandName="recoveryVO" id="reProductForm" name="reProductForm" method="post" action="" >
		  <input type="hidden" name="orderCode"          id="orderCode"         value="${orderCode}"  />
		  <input type="hidden" name="commentCategory"          id="commentCategory"         value="03"  />
		  <br>
		  <table class="table table-bordered" >
		 	<tr>
	          <th class='text-center' style="background-color:#E6F3FF;width:120px" >회수 마감일자</th>
	          <th>
	          	<div style='width:150px' class='input-group date ' id='datetimepicker3' data-link-field="recoveryClosingDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" disabled value="${recoveryClosingDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="recoveryClosingDate" name="recoveryClosingDate" value="${recoveryClosingDate}" />
	            </div>
	          </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >회수 대상지점<br>전체선택:<input type="checkbox"  id="regroupidCheckAll"  name="regroupidCheckAll" checked onchange="fcGroup_checkAll();" title="전체선택" /></th>
	          <th>
	 			<c:forEach var="groupVO" items="${group_comboList}" >
	 				<input type="checkbox" id="regroupid" name="regroupid"  title="선택" checked value="${groupVO.groupId}"/>${groupVO.groupName}&nbsp;
			    </c:forEach>
	         </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >메모</th>
	          <th>
	 			<input type="text" class="form-control" id="memo" name="memo"  maxlength="50"  value="" placeholder="메모" />
	         </th>
	      	</tr>
		  </table>
		  <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span > <span id="totalAttachCnt">선택품목 건수: <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="0" /> 건</span></span></p>       
		  </form:form>


   <div id="recoveryRegisList"  title="회수대상 품목조회"></div>
  <!-- //회수대상품목리스트 -->
  
  </div>  
</body>
<script type="text/javascript">

    $(function () {
        $('#datetimepicker3').datetimepicker(
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
  
    fcReProduct_registlist();
</script>