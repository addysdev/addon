<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
	$(function() {
	    // 기간 설정 타입 1 
	    // start Date 설정시 end Date의 min Date 지정
	    $( "#start_limitDate" ).datepicker({
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
	            $( "#end_limitDate" ).datepicker( "option", "minDate", selectedDate );
	        }
	    }); 
	     // end Date 설정시 start Date max Date 지정
	    $( "#end_limitDate" ).datepicker({
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
	            $( "#start_limitDate" ).datepicker( "option", "maxDate", selectedDate );
	        }
	    });
	
	    // 기간 설정 타입 2 
	    // start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
	    $("#start_limitDate").datepicker({
	        dateFormat: "yy-mm-dd",
	        defaultDate: "+1w",
	        numberOfMonths: 1,
	        changeMonth: true,
	        showMonthAfterYear: true ,
	        changeYear: true,
	        onClose: function( selectedDate ) {
	            if ($( "#start_limitDate" ).val() < selectedDate)
	            {
	                $( "#end_limitDate" ).val(selectedDate);
	            }
	        }
	    }); 
	    // end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
	    $( "#end_limitDate" ).datepicker({
	        dateFormat: "yy-mm-dd",
	        defaultDate: "+1w",
	        numberOfMonths: 1,
	        changeMonth: true,
	        showMonthAfterYear: true ,
	        changeYear: true,
	        onClose: function( selectedDate ) {
	            if ($("#start_limitDate" ).val() > selectedDate)
	            {
	                $("#end_limitDate" ).val(selectedDate);
	            }
	        }
	    });


	});
	function showCalendar(div){

	   if(div == "1"){
	   	   $('#start_limitDate').datepicker("show");
	   } else if(div == "2"){
		   $('#end_limitDate').datepicker("show");
	   }  
	}
    // 리스트 조회
    function fcOrderLimit_listSearch(curPage){

    	 curPage = (curPage==null) ? 1:curPage;
    	 orderLimitConForm.curPage.value = curPage;
    	 
         if(!dateCheck(document.orderLimitConForm.start_limitDate,document.orderLimitConForm.end_limitDate,'')){return;}

         commonDim(true);
         $.ajax({
             type: "POST",
                url:  "<%= request.getContextPath() %>/master/orderlimitpagelist",
                     data:$("#orderLimitConForm").serialize(),
                success: function(result) {
                    commonDim(false);
                    $("#orderLimitPageList").html(result);
                },
                error:function() {
                    commonDim(false);
                }
         });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcOrderLimit_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    // 발주제한 등록  Layup
    function fcOrderLimit_registForm() {
    	
    	var url='<%= request.getContextPath() %>/master/orderlimitregistform';

    	$('#orderLimitRegForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 650,
            height : 750,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load(url);
               
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#orderLimitRegForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#orderLimitRegForm').empty();
            }
        });
    };
  
</SCRIPT>
<div class="container-fluid">
	
    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">발주제한 리스트 <c:if test="${strAuth != '03'}"><button type="button" class="btn btn-success" onClick="fcOrderLimit_registForm()">발주제한등록</button></c:if></p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->	

	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="orderLimitConVO" id="orderLimitConForm" name="orderLimitConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
        		<label for="start_recoveryDate end_recoveryDate">발주제한 시작일자 :</label>
        		<!-- 조회시작일자-->
			    <input  class="form-control" style='width:135px' name="start_limitDate" id="start_limitDate" value="${orderLimitConVO.start_limitDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('1')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
	            &nbsp;~&nbsp;
                <!-- 조회죵료일자-->
			    <input  class="form-control" style='width:135px' name="end_limitDate" id="end_limitDate" value="${orderLimitConVO.end_limitDate}" type="text" maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('2')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
				<label for="con_groupId">지점선택 :</label>
				<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${orderLimitConVO.groupId}">
                    <option value="">전체</option>
                    <c:forEach var="groupVO" items="${group_comboList}" >
                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
                    </c:forEach>
                </select>
           		<label for="searchGubun">검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="${collectConVO.searchGubun}">
                	<option value="01" >업체명</option>
                    <option value="02" >업체코드</option>
                </select>
                <label class="sr-only" for="searchValue"> 조회값 :</label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${collectConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcOrderLimit_listSearch()">조회</button>		
	            <!-- >button type="button" class="btn" onClick="">excel</button -->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=orderLimitPageList></div>

  
  <!-- //발주제한 등록화면 -->
  <div id="orderLimitRegForm"  title="발주제한 등록"></div>

  
   <div id="orderLimitCompanyList"  title="발주제한 업체조회"></div>
  <!-- //발주제한 업체조회화면 -->
  
   <!-- 제한업체 일괄등록-->
  <div id="orderlimitExcelForm"  title="발주제한  업체 일괄등록"></div>
  
</div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
<script type="text/javascript">

    fcOrderLimit_listSearch();
    MM_nbGroup('down','group3','menu_03','<%= request.getContextPath() %>/images/top/addys-menu_03_on.jpg',1);
</script>