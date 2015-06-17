<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcHoldStock_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        holdStockConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/analysis/holdstockpagelist",
                    data:$("#holdStockConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#holdStockPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcHoldStock_listSearch('1');
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
</SCRIPT>
<div class="container-fluid">

    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">보유재고 분석/추천</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="holdStockConVO" id="holdStockConForm" name="holdStockConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
        	   <label for="start_saleDate end_saleDate">보유재고 평균(매출)기간 :</label>
				<!-- 조회시작일자-->
			    <input  class="form-control" style='width:135px' name="start_saleDate" id="start_saleDate" value="${holdStockConVO.start_saleDate}" type="text"  maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('1')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
	            &nbsp;~&nbsp;
                <!-- 조회죵료일자-->
			    <input  class="form-control" style='width:135px' name="end_saleDate" id="end_saleDate" value="${holdStockConVO.end_saleDate}" type="text" maxlength="10" dispName="날짜" onKeyUp="if(onlyNum(this.value).length==8) addDateFormat(this);" onBlur="if(onlyNum(this.value).length!=8) addDateFormat(this);" />
			    <!-- 달력이미지 시작 -->
			    <span class="icon_calendar"><img border="0" onclick="showCalendar('2')" src="<%=request.getContextPath()%>/images/sub/icon_calendar.gif"></span>
			    <!-- 달력이미지 끝 -->
			     &nbsp;
			    <label for="searchValue"> 적용(보유)일수 :</label>
				<input type="text" style="width:50px" class="form-control" id="con_applyDateCnt" name="con_applyDateCnt"  value="${holdStockConVO.applyDateCnt}" onkeypress="javascript:return checkKey(event);" />&nbsp;일
			    
				<br>
				<c:choose>
	    		<c:when test="${strAuth == '03'}">
					<input type="hidden" id="con_groupId" name="con_groupId" value="${holdStockConVO.groupId}">
					</c:when>
					<c:otherwise>
						<label for="con_groupId">지점선택 :</label>
						<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${holdStockConVO.groupId}">
		                    <option value="">전체</option>
		                    <c:forEach var="groupVO" items="${group_comboList}" >
		                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
		                    </c:forEach>
		                </select>
					</c:otherwise>
				</c:choose>
        	
				<label for="searchGubun"><h6><strong>검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >품목명</option>
                    <option value="02" >품목코드</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${holdStockConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcHoldStock_listSearch()">조회</button>
	            <button type="button" class="btn" onClick="">excel</button>
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=holdStockPageList></div>
  <!-- 추천재고 일괄업데이트 -->
   <c:if test="${strAuth!='03'}">
  <button type="button" class="btn btn-primary" onClick="fcRecomend_updates()">추천보유재고 일괄적용</button>
  </c:if>
  
</div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
<script>
fcHoldStock_listSearch();
MM_nbGroup('down','group6','menu_06','<%= request.getContextPath() %>/images/top/addys-menu_06_on.jpg',1);
</script>