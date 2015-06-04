<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcOrder_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        orderConForm.curPage.value = curPage;

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
				<div style='width:155px' class='input-group date ' id='datetimepicker1' data-link-field="start_orderDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${orderConVO.start_orderDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="start_orderDate" name="start_orderDate" value="${orderConVO.start_orderDate}" />
	            </div>
	            <div style='width:155px' class='input-group date' id='datetimepicker2'  data-link-field="end_orderDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${orderConVO.end_orderDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="end_orderDate" name="end_orderDate" value="${orderConVO.end_orderDate}" />
	            </div>
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
  <div id="orderDetailView"  title="검수 처리화면"></div>
  <!-- //검수 상세처리화면 -->
  
  <div id="memoManage"  title="메모관리"></div>
    <!-- //보류 상세화면 -->
    <div id="etcManage"  title="비고"></div>
</div>
<div id="deferDialog"  title="보류사유를 입력하세요"></div>
<div id="deferReasonList"  title="보류사유"></div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
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
    
    fcOrder_listSearch();
    MM_nbGroup('down','group1','menu_01','<%= request.getContextPath() %>/images/top/addys-menu_01_on.jpg',1);
</script>