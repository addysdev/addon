<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcOrder_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        orderConForm.curPage.value = curPage;
 
        if(!dateCheck(document.orderConForm.start_orderDate,document.orderConForm.end_orderDate,'')){return;}

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/order/orderpagelist",
                    data:$("#orderConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#orderPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcOrder_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    $(function() {
        // 기간 설정 타입 1 
        // start Date 설정시 end Date의 min Date 지정
        $( "#start_orderDate" ).datepicker({
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
                $( "#end_orderDate" ).datepicker( "option", "minDate", selectedDate );
            }
        }); 
         // end Date 설정시 start Date max Date 지정
        $( "#end_orderDate" ).datepicker({
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
                $( "#start_orderDate" ).datepicker( "option", "maxDate", selectedDate );
            }
        });
 
        // 기간 설정 타입 2 
        // start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
        $("#start_orderDate").datepicker({
            dateFormat: "yy-mm-dd",
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            onClose: function( selectedDate ) {
                if ($( "#start_orderDate" ).val() < selectedDate)
                {
                    $( "#end_orderDate" ).val(selectedDate);
                }
            }
        }); 
        // end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
        $( "#end_orderDate" ).datepicker({
            dateFormat: "yy-mm-dd",
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            onClose: function( selectedDate ) {
                if ($("#start_orderDate" ).val() > selectedDate)
                {
                    $("#start_orderDate" ).val(selectedDate);
                }
            }
        });
        
        //날짜
        $( "#date" ).datepicker({
            changeMonth: true ,
            changeYear: true ,
            showMonthAfterYear: true ,
            dateFormat: "yy-mm-dd",
            dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
            monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
            monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
            defaultDate: "+1w",
            numberOfMonths: 1
        }); 


    });
    function showCalendar(div){

 	   if(div == "1"){
 	   	   $('#start_orderDate').datepicker("show");
 	   } else if(div == "2"){
 		   $('#end_orderDate').datepicker("show");
 	   }  
 	}
</SCRIPT>
<div class="container-fluid">

    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">검수 리스트</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="orderConVO" id="orderConForm" name="orderConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
        		<label for="start_orderDate end_orderDate">발주일자 :</label>
				<!-- 조회시작일자-->
			    <input  class="form-control" style='width:135px' name="start_orderDate" id="start_orderDate" value="${orderConVO.start_orderDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('1')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
	            &nbsp;~&nbsp;
                <!-- 조회죵료일자-->
			    <input  class="form-control" style='width:135px' name="end_orderDate" id="end_orderDate" value="${orderConVO.end_orderDate}" type="text" maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('2')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
	            <br><br>
	            <c:choose>
	    		<c:when test="${strAuth == '03'}">
					<input type="hidden" id="con_groupId" name="con_groupId" value="${orderConVO.groupId}">
					</c:when>
					<c:otherwise>
						<label for="con_groupId">지점선택 :</label>
						<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${orderConVO.groupId}">
		                    <option value="">전체</option>
		                    <c:forEach var="groupVO" items="${group_comboList}" >
		                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
		                    </c:forEach>
		                </select>
					</c:otherwise>
				</c:choose>
				<label for="searchGubun">검수상태 :</label>
				<select class="form-control" title="발주상태" id="con_orderState" name="con_orderState" value="">
                	<option value="">전체</option>
                    <c:forEach var="codeVO" items="${code_comboList}" >
                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
                    </c:forEach>
           		</select>
           		<label for="searchGubun">검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="02" >발주자명</option>
                    <option value="01" >발주자ID</option>
                    <option value="04" >업체명</option>
                    <option value="03" >업체코드</option>
                    <option value="06" >품목명</option>
           			<option value="05" >품목코드</option>
                </select>
				<label class="sr-only" for="searchValue"> 조회값 :</label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${orderConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcOrder_listSearch()">조회</button>
	            <!-- >button type="button" class="btn" onClick="">excel</button -->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=orderPageList></div>
  <!-- 검수 상세처리화면-->
  <div id="orderDetailView"  title="검수 처리화면" onkeyPress='javascript:EnterKey(event);' ></div>
  <!-- //검수 상세처리화면 -->
  
  <div id="memoManage"  title="메모관리"></div>
    <!-- //보류 상세화면 -->
    <div id="etcManage"  title="비고"></div>
</div>
<div id="deferDialog"  title="보류사유를 입력하세요"></div>
<div id="deferReasonList"  title="보류사유"></div>
<div id="barCodeDialog"  title="바코드 검수"></div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
<script type="text/javascript">

    fcOrder_listSearch();
   
    MM_nbGroup('down','group1','menu_01','<%= request.getContextPath() %>/images/top/addys-menu_01_on.jpg',1);
</script>