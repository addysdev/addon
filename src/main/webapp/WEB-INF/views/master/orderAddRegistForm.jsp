<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
$(function() {
    // 기간 설정 타입 1 
    // start Date 설정시 end Date의 min Date 지정
    $( "#addStartDate" ).datepicker({
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
            $( "#addEndDate" ).datepicker( "option", "minDate", selectedDate );
        }
    }); 
     // end Date 설정시 start Date max Date 지정
    $( "#addEndDate" ).datepicker({
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
            $( "#addStartDate" ).datepicker( "option", "maxDate", selectedDate );
        }
    });

    // 기간 설정 타입 2 
    // start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
    $("#addStartDate").datepicker({
        dateFormat: "yy-mm-dd",
        defaultDate: "+1w",
        numberOfMonths: 1,
        changeMonth: true,
        showMonthAfterYear: true ,
        changeYear: true,
        onClose: function( selectedDate ) {
            if ($( "#addStartDate" ).val() < selectedDate)
            {
                $( "#addEndDate" ).val(selectedDate);
            }
        }
    }); 
    // end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
    $( "#addEndDate" ).datepicker({
        dateFormat: "yy-mm-dd",
        defaultDate: "+1w",
        numberOfMonths: 1,
        changeMonth: true,
        showMonthAfterYear: true ,
        changeYear: true,
        onClose: function( selectedDate ) {
            if ($("#addStartDate" ).val() > selectedDate)
            {
                $("#addEndDate" ).val(selectedDate);
            }
        }
    });


	});
	function showCalendar2(div){
	
	   if(div == "1"){
	   	   $('#addStartDate').datepicker("show");
	   } else if(div == "2"){
		   $('#addEndDate').datepicker("show");
	   }  
	}
// 업체조회 리스트 Layup
function fCompany_list() {
	
	//$('#targetEtcView').attr('title',productName);
	var url='<%= request.getContextPath() %>/manage/companysearch';

	$('#orderAddCompanyList').dialog({
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
                $("#orderAddCompanyList").dialog('close');

                });
        }
        ,close:function(){
            $('#orderAddCompanyList').empty();
        }
    });
};
function fcOrderAdd_regist(){
	
	var frm=document.addCompanyListForm;
	var rfrm=document.orderAddForm;
	
	var arrCheckGroupId = "";
	var arrSelectCompanyCode = "";

	if(rfrm.regroupid!=undefined){

        $('input:checkbox[name="regroupid"]').each(function() {
            if ($(this).is(":checked")) {
           	 arrCheckGroupId += $(this).val() + "^";
            }   
        });
        
	}else{
		
		alert('선택하신 추가 대상지점이 없습니다.');
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
	if (confirm('발주추가 등록을  하시겠습니까?')){ 
	
		   var paramString = $("#orderAddForm").serialize()+ "&arrCheckGroupId="+arrCheckGroupId+'&arrSelectCompanyCode='+arrSelectCompanyCode;

	  		$.ajax({
		       type: "POST",
		       async:false,
		          url:  "<%= request.getContextPath() %>/master/orderaddregist",
		          data:paramString,
		          success: function(result) {
	
		        	resultMsg(result);

					$('#orderAddRegForm').dialog('close');
					fcOrderAdd_listSearch();
						
		          },
		          error:function(){
		          
		          alert('발주추가 호출오류!');
                  $('#orderAddRegForm').dialog('close');
                  fcOrderAdd_listSearch();
		          }
		    });
	}
}

function fcCompany_Select(companycode,companyname){
	
	var frm=document.addCompanyListForm;
	
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
	var frm = document.addCompanyListForm;
	
	if(flag=='add'){
		//alert(frm.selectProduct.length);
		if(frm.selectCompany.length!=undefined ){
			totalcnt=frm.selectCompany.length;
		}else{
			totalcnt=1;	
		}
	}else{
		if(frm.selectCompany!=undefined ){
			totalcnt=frm.selectCompany.length;
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

	$('#orderAddExcelForm').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 430,
        height : 300,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
            $(this).load('<%= request.getContextPath() %>/master/orderaddexcelform');
            //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#orderAddExcelForm").dialog('close');

                });
        }
        ,close:function(){
            $('#orderAddExcelForm').empty();
        }
    });
};
// 리스트 조회
function fcOrderAdd_registlist(){

     $.ajax({
         type: "POST",
            url:  "<%= request.getContextPath() %>/master/orderaddregislist",
                 data:$("#orderAddForm").serialize(),
            success: function(result) {
                commonDim(false);
               
                $("#orderAddRegisList").html(result);
            },
            error:function() {
            	
            }
     });
}

//체크박스 전체선택
function fcGroup_checkAll(){
	
	$("input:checkbox[id='regroupid']").prop("checked", $("#regroupidCheckAll").is(":checked"));

}

function fcCompany_allAttach(){

	if (confirm('전체 업체를 발주추가 업체로 추가하시겠습니까?')){ 
	
	    commonDim(true);
	    
	    $.ajax({
	        type: "POST",
	           url:  "<%= request.getContextPath() %>/master/orderaddalllist",
	                data:$("#orderAddForm").serialize(),
	           success: function(result) {
	               commonDim(false);
	               $("#orderAddRegisList").html(result);
	           },
	           error:function() {
	           	
	           }
	    });
	}

}
//--> 

</SCRIPT>
<!-- 사용자관리 -->
<body>
  <div class="container-fluid">
	<h5><strong><font style="color:#428bca">발주추가 관리&nbsp; 
   		<button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fCompany_list()" >추가 업체</button>
   		<button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fcCompany_excelForm()" >추가 업체 엑셀추가</button>
   		<button id="memoinfobtn" type="button" class="btn btn-xs btn-info" onClick="fcCompany_allAttach()" >추가 업체 전체추가</button>
   		<button id="memoinfobtn" type="button" class="btn btn-xs btn-primary" onClick="fcOrderAdd_regist()" >발주추가 요청</button>
    	</font></strong></h5>
		  <form:form commandName="orderAddVO" id="orderAddForm" name="orderAddForm" method="post" action="" >
		  <br>
		  <table class="table table-bordered" >
		 	<tr>
	          <th class='text-center' style="background-color:#E6F3FF;width:120px" >발주추가 기간</th>
		      <th class='text-left'>
	          <div class="form-inline">
	              <!-- 조회시작일자-->
				    <input  class="form-control" style='width:135px' name="addStartDate" id="addStartDate" value="${addStartDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
				    <!-- 달력이미지 시작 -->
				    <span class="icon_calendar"><img border="0" onclick="showCalendar2('1')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
				    <!-- 달력이미지 끝 -->
		            &nbsp;~&nbsp;
	                <!-- 조회죵료일자-->
				    <input  class="form-control" style='width:135px' name="addEndDate" id="addEndDate" value="${addEndDate}" type="text" maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
				    <!-- 달력이미지 시작 -->
				    <span class="icon_calendar"><img border="0" onclick="showCalendar2('2')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
				    <!-- 달력이미지 끝 -->
			  </div>
	          </th>
	      	</tr>
	      	<tr>
	          <th class='text-center' style="background-color:#E6F3FF" >발주추가 대상지점<br>전체선택:<input type="checkbox"  id="regroupidCheckAll"  name="regroupidCheckAll" checked onchange="fcGroup_checkAll();" title="전체선택" /></th>
	          <th>
	 			<c:forEach var="groupVO" items="${group_comboList}" >
	 				<input type="checkbox" id="regroupid" name="regroupid"  title="선택" checked value="${groupVO.groupId}"/>${groupVO.groupName}&nbsp;
			    </c:forEach>
	         </th>
	      	</tr>
		  </table>
		  <p><span style="color:#FF9900"> <span class="glyphicon glyphicon-asterisk"></span > <span id="totalAttachCnt">선택업체 건수: <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="0" /> 건</span></span></p>       
		  </form:form>


   <div id="orderAddRegisList"  title="추가대상 업체리스트"></div>
  <!-- //추가대상업체리스트 -->
  
  </div>  
</body>
<script type="text/javascript">
  
	fcOrderAdd_registlist();
</script>