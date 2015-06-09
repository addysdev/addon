<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
	$(function() {
	    // 기간 설정 타입 1 
	    // start Date 설정시 end Date의 min Date 지정
	    $( "#start_recoveryDate" ).datepicker({
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
	            $( "#end_recoveryDate" ).datepicker( "option", "minDate", selectedDate );
	        }
	    }); 
	     // end Date 설정시 start Date max Date 지정
	    $( "#end_recoveryDate" ).datepicker({
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
	            $( "#start_recoveryDate" ).datepicker( "option", "maxDate", selectedDate );
	        }
	    });
	
	    // 기간 설정 타입 2 
	    // start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
	    $("#start_recoveryDate").datepicker({
	        dateFormat: "yy-mm-dd",
	        defaultDate: "+1w",
	        numberOfMonths: 1,
	        changeMonth: true,
	        showMonthAfterYear: true ,
	        changeYear: true,
	        onClose: function( selectedDate ) {
	            if ($( "#start_recoveryDate" ).val() < selectedDate)
	            {
	                $( "#end_recoveryDate" ).val(selectedDate);
	            }
	        }
	    }); 
	    // end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
	    $( "#end_recoveryDate" ).datepicker({
	        dateFormat: "yy-mm-dd",
	        defaultDate: "+1w",
	        numberOfMonths: 1,
	        changeMonth: true,
	        showMonthAfterYear: true ,
	        changeYear: true,
	        onClose: function( selectedDate ) {
	            if ($("#start_recoveryDate" ).val() > selectedDate)
	            {
	                $("#start_recoveryDate" ).val(selectedDate);
	            }
	        }
	    });


	});
	function showCalendar(div){

	   if(div == "1"){
	   	   $('#start_recoveryDate').datepicker("show");
	   } else if(div == "2"){
		   $('#end_recoveryDate').datepicker("show");
	   } 
	}
    // 리스트 조회
    function fcCollect_listSearch(curPage){

    	 curPage = (curPage==null) ? 1:curPage;
         collectConForm.curPage.value = curPage;
         
         if(!dateCheck(document.collectConForm.start_recoveryDate,document.collectConForm.end_recoveryDate,'')){return;}

         commonDim(true);
         $.ajax({
             type: "POST",
                url:  "<%= request.getContextPath() %>/recovery/collectpagelist",
                     data:$("#collectConForm").serialize(),
                success: function(result) {
                    commonDim(false);
                    $("#collectPageList").html(result);
                },
                error:function() {
                    commonDim(false);
                }
         });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcCollect_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    // 회수 등록  Layup
    function fcRecovery_registForm() {
    	
    	var url='<%= request.getContextPath() %>/recovery/recoveryregistform';

    	$('#recoveryRegForm').dialog({
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
                    $("#recoveryRegForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#recoveryRegForm').empty();
            }
        });
    };
  
</SCRIPT>
<div class="container-fluid">
	
    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">회수 작업리스트 <c:if test="${strAuth != '03'}"><button type="button" class="btn btn-success" onClick="fcRecovery_registForm()">회수등록</button></c:if></p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->	

	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="collectConVO" id="collectConForm" name="collectConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <input type="hidden" name="con_groupId"          id="con_groupId"         value="${collectConVO.groupId}"  />
        <input type="hidden" name="authId"          id="authId"         value="${strAuth}"  />
        <fieldset>
        	<div class="form-group">
        		<label for="start_recoveryDate end_recoveryDate">회수요청일자 :</label>
        		<!-- 조회시작일자-->
			    <input  class="form-control" style='width:135px' name="start_recoveryDate" id="start_recoveryDate" value="${collectConVO.start_recoveryDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('1')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
	            &nbsp;~&nbsp;
                <!-- 조회죵료일자-->
			    <input  class="form-control" style='width:135px' name="end_recoveryDate" id="end_recoveryDate" value="${collectConVO.end_recoveryDate}" type="text" maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('2')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
				<label for="con_collectState">작업상태 :</label>
				<select class="form-control" title="작업상태" id="con_collectState" name="con_collectState" value="">
                	<option value="">전체</option>
                    <c:forEach var="codeVO" items="${code_comboList}" >
                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
                    </c:forEach>
           		</select>
           		<label for="searchGubun">회수코드 :</label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${collectConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcCollect_listSearch()">조회</button>		
	            <!-- >button type="button" class="btn" onClick="">excel</button -->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=collectPageList></div>

   <!--회수 상세처리화면-->
  <div id="recoveryManage"  title="회수 상세리스트"></div>
 
  <!-- //회수 등록화면 -->
  <div id="recoveryRegForm"  title="회수대상 등록"></div>

  <!--회수 상세처리화면-->
  <div id="recoveryDetailView"  title="회수 상세처리화면"></div>

  <div id="memoManage"  title="메모관리"></div>
  <!-- //메모 상세화면 -->
  
  <div id="etcManage"  title="비고"></div>
  <!-- //비고 상세화면 -->
  
   <div id="recoveryProductList"  title="회수대상 품목조회"></div>
  <!-- //검수 상세처리화면 -->
  
   <!-- 보유재고 일괄등록-->
  <div id="reProductExcelForm"  title="회수품목 일괄등록"></div>
  
</div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
<script type="text/javascript">

    fcCollect_listSearch();
    MM_nbGroup('down','group2','menu_02','<%= request.getContextPath() %>/images/top/addys-menu_02_on.jpg',1);
</script>