<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcGmroi_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        gmroiConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/analysis/gmroipagelist",
                    data:$("#gmroiConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#gmroiPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcGmroi_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    $(function() {
        // 기간 설정 타입 1 
        // start Date 설정시 end Date의 min Date 지정
        $( "#start_saleDate" ).datepicker({
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
                $( "#end_saleDate" ).datepicker( "option", "minDate", selectedDate );
            }
        }); 
         // end Date 설정시 start Date max Date 지정
        $( "#end_saleDate" ).datepicker({
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
                $( "#start_saleDate" ).datepicker( "option", "maxDate", selectedDate );
            }
        });

        // 기간 설정 타입 2 
        // start Date 설정시 end Date 가 start Date보다 작을 경우 end Date를 start Date와 같게 설정
        $("#start_saleDate").datepicker({
            dateFormat: "yy-mm-dd",
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            onClose: function( selectedDate ) {
                if ($( "#start_saleDate" ).val() < selectedDate)
                {
                    $( "#end_saleDate" ).val(selectedDate);
                }
            }
        }); 
        // end Date 설정시 end Date 가 start Date 보다 작을 경우 start Date를  end Date와 같게 설정
        $( "#end_saleDate" ).datepicker({
            dateFormat: "yy-mm-dd",
            defaultDate: "+1w",
            numberOfMonths: 1,
            changeMonth: true,
            showMonthAfterYear: true ,
            changeYear: true,
            onClose: function( selectedDate ) {
                if ($("#start_saleDate" ).val() > selectedDate)
                {
                    $("#start_saleDate" ).val(selectedDate);
                }
            }
        });

    	
    	});
    	function showCalendar(div){
    	
    	   if(div == "1"){
    	   	   $('#start_saleDate').datepicker("show");
    	   } else if(div == "2"){
    		   $('#end_saleDate').datepicker("show");
    	   }   
    	}
    	
   	 function goGmroiExcel(){

       	var frm = document.gmroiConForm;
       	frm.action = "<%=request.getContextPath()%>/analysis/gmroiexcellist";	
       	frm.method = "POST";
       	frm.submit();
 	 
 	 }
</SCRIPT>
<div class="container-fluid">

    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">GMROI 분석</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="gmroiConVO" id="gmroiConForm" name="gmroiConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <input type="hidden" name="orderByName"         id="orderByName"         value=""  />
        <input type="hidden" name="orderBySort"         id="orderBySort"         value=""  />
        <fieldset>
        	<div class="form-group">
        	   <label for="start_saleDate end_saleDate">GMROI 기준일자 :</label>
				<!-- 조회시작일자-->
			    <input  class="form-control" style='width:135px' name="start_saleDate" id="start_saleDate" value="${gmroiConVO.start_saleDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('1')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
	            &nbsp;~&nbsp;
                <!-- 조회죵료일자-->
			    <input  class="form-control" style='width:135px' name="end_saleDate" id="end_saleDate" value="${gmroiConVO.end_saleDate}" type="text" maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('2')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
			    <label for="start_gmroi end_gmroi"> GMROI 기준값 : </label>
				<input type="text" style="width:70px" maxlength="4" numberOnly class="form-control text-right" id="start_gmroi" name="start_gmroi"  value="${gmroiConVO.start_gmroi}" />~
				<input type="text" style="width:70px" maxlength="4" numberOnly class="form-control text-right"  id="end_gmroi" name="end_gmroi"  value="${gmroiConVO.end_gmroi}" />
			    
				<br>
				<c:choose>
	    		<c:when test="${strAuth == '03'}">
					<input type="hidden" id="con_groupId" name="con_groupId" value="${gmroiConVO.groupId}">
					</c:when>
					<c:otherwise>
						<label for="con_groupId">지점선택 :</label>
						<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${gmroiConVO.groupId}">
		                    <option value="">전체</option>
		                    <c:forEach var="groupVO" items="${group_comboList}" >
		                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
		                    </c:forEach>
		                </select>
					</c:otherwise>
				</c:choose>
        	    
        	    <label for="con_group1Name"> 그룹1 </label>
				<input type="text" style="width:70px" class="form-control" id="con_group1Name" name="con_group1Name"  value="${gmroiConVO.con_group1Name}" onkeypress="javascript:return checkKey(event);" />
				<label for="con_group2Name"> 그룹2 </label>
				<input type="text" style="width:70px" class="form-control" id="con_group2Name" name="con_group2Name"  value="${gmroiConVO.con_group2Name}" onkeypress="javascript:return checkKey(event);" />
				<label for="con_group3Name"> 그룹3 </label>
				<input type="text" style="width:70px" class="form-control" id="con_group3Name" name="con_group3Name"  value="${gmroiConVO.con_group3Name}" onkeypress="javascript:return checkKey(event);" />
				
				<label for="searchGubun"><h6><strong>검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >품목명</option>
                    <option value="02" >품목코드</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${gmroiConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcGmroi_listSearch()">조회</button>
	            <button type="button" class="btn" onClick="goGmroiExcel()">excel</button>
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=gmroiPageList></div>
  
</div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
<script>
fcGmroi_listSearch();
MM_nbGroup('down','group6','menu_06','<%= request.getContextPath() %>/images/top/addys-menu_06_on.jpg',1);
</script>