<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcTarget_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        targetConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/order/targetpagelist",
                    data:$("#targetConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#targetPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcTarget_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
</SCRIPT>
<div class="container">
	<h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 발주 리스트</font></strong></h4>
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="targetConVO" id="targetConForm" name="targetConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
        	    <font style="color:#FF9900"><span class="glyphicon glyphicon-search"></span></font>
        	    <c:choose>
	    		<c:when test="${strAuth == '03'}">
					<input type="hidden" id="con_groupId" name="con_groupId" value="${targetConVO.groupId}">
					</c:when>
					<c:otherwise>
						<label for="con_groupId"><font style="color:#FF9900"> 지점선택 : </font></label>
						<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${targetConVO.groupId}">
		                    <option value="">전체</option>
		                    <c:forEach var="groupVO" items="${group_comboList}" >
		                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
		                    </c:forEach>
		                </select>
					</c:otherwise>
				</c:choose>
				<label for="con_orderState"><h6><strong><font style="color:#FF9900"> 발주상태 : </font></strong></h6></label>
				<select class="form-control" title="발주상태" id="con_orderState" name="con_orderState" value="">
                	<option value="">전체</option>
                    <c:forEach var="codeVO" items="${code_comboList}" >
                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
                    </c:forEach>
           		</select>
				<button type="button" class="btn btn-primary" onClick="javascript:fcTarget_listSearch()">조회</button>
	            <!--  >button type="button" class="btn" onClick="">excel</button-->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=targetPageList></div>
  <!-- //조회결과리스트 -->
  <!-- 발주 상세처리화면-->
  <div id="targetDetailView"  title="발주 상세처리화면"></div>
  <!-- //발주 상세처리화면 -->
    <!-- 발주 상세처리화면-->
  <div id="deferDialog"  title="보류사유를 입력하세요"></div>
  <!-- //발주 상세처리화면 -->
  
  <div id="deferReasonList"  title="보류사유"></div>
</div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
<script>
fcTarget_listSearch();
</script>