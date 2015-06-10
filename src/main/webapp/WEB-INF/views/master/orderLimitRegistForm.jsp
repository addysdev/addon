<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
$(function() {
    // 기간 설정 타입 1 
    // start Date 설정시 end Date의 min Date 지정
    $( "#limitStartDate" ).datepicker({
        dateFormat: "yy-mm-dd",
        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
        monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        defaultDate: "+1w",
        numberOfMonths: 1,
        changeMonth: true,
        showMonthAfterYear: true ,
        changeYear: true,
        maxDate : "+0D",
        onClose: function( selectedDate ) {
            $( "#limitEndDate" ).datepicker( "option", "minDate", selectedDate );
        }
    }); 
     // end Date 설정시 start Date max Date 지정
    $( "#limitEndDate" ).datepicker({
        dateFormat: "yy-mm-dd",
        dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
        monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
        defaultDate: "+1w",
        numberOfMonths: 1,
        changeMonth: true,
        showMonthAfterYear: true ,
        changeYear: true,
        onClose: function( selectedDate ) {
            $( "#limitStartDate" ).datepicker( "option", "maxDate", selectedDate );
        }
    });

    // 기간 설정 타입 2 
    // start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
    $("#limitStartDate").datepicker({
        dateFormat: "yy-mm-dd",
        defaultDate: "+1w",
        numberOfMonths: 1,
        changeMonth: true,
        showMonthAfterYear: true ,
        changeYear: true,
        onClose: function( selectedDate ) {
            if ($( "#limitStartDate" ).val() < selectedDate)
            {
                $( "#limitEndDate" ).val(selectedDate);
            }
        }
    }); 
    // end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
    $( "#limitEndDate" ).datepicker({
        dateFormat: "yy-mm-dd",
        defaultDate: "+1w",
        numberOfMonths: 1,
        changeMonth: true,
        showMonthAfterYear: true ,
        changeYear: true,
        onClose: function( selectedDate ) {
            if ($("#limitStartDate" ).val() > selectedDate)
            {
                $("#limitEndDate" ).val(selectedDate);
            }
        }
    });


	});
	function showCalendar2(div){
	
	   if(div == "1"){
	   	   $('#limitStartDate').datepicker("show");
	   } else if(div == "2"){
		   $('#limitEndDate').datepicker("show");
	   }  
	}
// 업체조회 리스트 Layup
function fCompany_list() {
	
	//$('#targetEtcView').attr('title',productName);
	var url='<%= request.getContextPath() %>/manage/companysearch';

	$('#orderLimitCompanyList').dialog({
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
                $("#orderLimitCompanyList").dialog('close');

                });
        }
        ,close:function(){
            $('#orderLimitCompanyList').empty();
        }
    });
};
function fcOrderLimit_regist(){
	
	var frm=document.limitCompanyListForm;
	var rfrm=document.orderLimitForm;
	
	var arrCheckGroupId = "";
	var arrSelectCompanyCode = "";

	if(rfrm.regroupid!=undefined){

        $('input:checkbox[name="regroupid"]').each(function() {
            if ($(this).is(":checked")) {
           	 arrCheckGroupId += $(this).val() + "^";
            }   
        });
        
	}else{
		
		alert('선택하신 제한 대상지점이 없습니다.');
		return;
	}
	
	if(frm.selectCompany!=undefined){
		
		if(frm.selectCompany.length>1){
			
			for (i=0;i<frm.selectCompany.length;i++){
				arrSelectCompanyCode += frm.selectCompany[i].value + "^";
			}
		}else{		
			arrSelectCompanyCode += frm.selectCompany.value + "^";
		}
	}else{
		
		alert('선택하신 업체코드가 없습니다.');
		return;
	}
	
	//alert(arrCheckGroupId);
	//alert(arrSelectProductId);
	//return;
	if (confirm('발주제한 등록을  하시겠습니까?')){ 
	
		   var paramString = $("#orderLimitForm").serialize()+ "&arrCheckGroupId="+arrCheckGroupId+'&arrSelectCompanyCode='+arrSelectCompanyCode;

	  		$.ajax({
		       type: "POST",
		       async:false,
		          url:  "<%= request.getContextPath() %>/master/orderlimtregist",
		          data:paramString,
		          success: function(result) {
	
		        	resultMsg(result);

					$('#orderLimitRegForm').dialog('close');
					fcOrderLimit_listSearch();
						
		          },
		          error:function(){
		          
		          alert('발주제한 호출오류!');
                  $('#orderLimitRegForm').dialog('close');
                  fcOrderLimit_listSearch();
		          }
		    });
	}
}

function fcCompany_Select(companycode,companyname){
	
	var frm=document.limitCompanyListForm;
	
	if(frm.selectCompany!=undefined){
		
		if(frm.selectCompany.length>1){
			
			for (i=0;i<frm.selectCompany.length;i++){
				if(frm.selectCompany[i].value==companycode){
					alert('이미 선택하신 업체코드 입니다.');
					return;
				}
			}
		}else{
			
			if(frm.selectCompany.value==companycode){
				alert('이미 선택하신 업체코드 입니다.');
				return;
			}
		}
	}
	
	var rowCnt = contentId.rows.length;
	var newRow = contentId.insertRow( rowCnt++ );
	newRow.onmouseover=function(){contentId.clickedRowIndex=this.rowIndex};
	var newCell = newRow.insertCell();
	newCell.innerHTML ='<tr><input type="hidden" id="selectCompany" name="selectCompany" value='+companycode+'><td class="text-center">['+companycode+']'+companyname+'&nbsp;<button type="button" class="btn btn-xs btn-info" onClick="delFile2(this)" >삭제</button></td></tr>';
	
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
	
	document.all('totalAttachCnt').innerText='선택업체 건수 :'+addCommaStr(''+totalcnt)+' 건';
	
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

function delFile2(obj){ 
    var tr = obj // A 
             .parentNode // TD 
             .parentNode; // TR 
    var table = tr.parentNode; 
    var index = tr.rowIndex; 
    table.deleteRow(index-1); 
    
    totalAttachCnt('del');
} 

//레이어팝업 : 보유재고 Layer 팝업
function fcCompany_excelForm(){

	$('#orderlimitExcelForm').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 430,
        height : 300,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('<%= request.getContextPath() %>/master/orderlimitexcelform');
            //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#orderlimitExcelForm").dialog('close');

                });
        }
        ,close:function(){
            $('#orderlimitExcelForm').empty();
        }
    });
};
// 리스트 조회
function fcOrderLimit_registlist(){

     $.ajax({
         type: "POST",
            url:  "<%= request.getContextPath() %>/master/orderlimitregislist",
                 data:$("#orderLimitForm").serialize(),
            success: function(result) {
                commonDim(false);
                $("#orderLimitRegisList").html(result);
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
	<h5><strong><font style="color:#428bca">발주제한 관리&nbsp; 
   		<button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fCompany_list()" >발주제한 업체 추가</button>
   		<button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fcCompany_excelForm()" >발주제한 업체 엑셀추가</button>
   		<button id="memoinfobtn" type="button" class="btn btn-xs btn-primary" onClick="fcOrderLimit_regist()" >발주제한 요청</button>
    	</font></strong></h5>
		  <form:form commandName="orderLimitVO" id="orderLimitForm" name="orderLimitForm" method="post" action="" >
		  <br>
		  <table class="table table-bordered" >
		 	<tr>
	          <th class='text-center' style="background-color:#E6F3FF;width:120px" >발주제한 기간</th>
		      <th class='text-left'>
	          <div class="form-inline">
	              <!-- 조회시작일자-->
				    <input  class="form-control" style='width:135px' name="limitStartDate" id="limitStartDate" value="${limitStartDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
				    <!-- 달력이미지 시작 -->
				    <span class="icon_calendar"><img border="0" onclick="showCalendar2('1')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
				    <!-- 달력이미지 끝 -->
		            &nbsp;~&nbsp;
	                <!-- 조회죵료일자-->
				    <input  class="form-control" style='width:135px' name="limitEndDate" id="limitEndDate" value="${limitEndDate}" type="text" maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
				    <!-- 달력이미지 시작 -->
				    <span class="icon_calendar"><img border="0" onclick="showCalendar2('2')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
				    <!-- 달력이미지 끝 -->
			  </div>
	          </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >발주제한 대상지점<br>전체선택:<input type="checkbox"  id="regroupidCheckAll"  name="regroupidCheckAll" checked onchange="fcGroup_checkAll();" title="전체선택" /></th>
	          <th>
	 			<c:forEach var="groupVO" items="${group_comboList}" >
	 				<input type="checkbox" id="regroupid" name="regroupid"  title="선택" checked value="${groupVO.groupId}"/>${groupVO.groupName}&nbsp;
			    </c:forEach>
	         </th>
	      	</tr>
		  </table>
		  <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span > <span id="totalAttachCnt">선택업체 건수: <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="0" /> 건</span></span></p>       
		  </form:form>


   <div id="orderLimitRegisList"  title="제한대상 업체리스트"></div>
  <!-- //제한대상업체리스트 -->
  
  </div>  
</body>
<script type="text/javascript">
  
	fcOrderLimit_registlist();
</script>