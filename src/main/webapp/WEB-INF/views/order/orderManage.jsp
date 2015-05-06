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
<div class="container">
	<h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 검수 리스트</font></strong></h4>
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="orderConVO" id="orderConForm" name="orderConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
        		<label for="start_stockDate end_stockDate"><h6><strong><font style="color:#FF9900">  <span class="glyphicon glyphicon-search"></span>  발주일자 : </font></strong></h6></label>
				<div class='input-group date ' id='datetimepicker1' data-link-field="start_orderDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${orderConVO.start_orderDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="start_orderDate" name="start_orderDate" value="${orderConVO.start_orderDate}" />
	            </div>
	            <div class='input-group date' id='datetimepicker2'  data-link-field="end_stockDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${orderConVO.end_orderDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="end_orderDate" name="end_orderDate" value="${orderConVO.end_orderDate}" />
	            </div>
	            <br><br>
				<label for="con_groupId"><h6><strong><font style="color:#FF9900"> 지점선택 : </font></strong></h6></label>
				<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${orderConVO.groupId}">
                    <option value="">전체</option>
                    <c:forEach var="groupVO" items="${group_comboList}" >
                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
                    </c:forEach>
                </select>
				<label for="searchGubun"><h6><strong><font style="color:#FF9900"> 검수상태 : </font></strong></h6></label>
				<select class="form-control" title="발주상태" id="con_orderState" name="con_orderState" value="">
                	<option value="">전체</option>
                    <c:forEach var="codeVO" items="${code_comboList}" >
                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
                    </c:forEach>
           		</select>
           		<label for="searchGubun"><h6><strong><font style="color:#FF9900"> 검색조건 : </font></strong></h6></label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >매장명</option>
                    <option value="02" >발주자</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${orderConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcOrder_listSearch()">search</button>
	            <button type="button" class="btn" onClick="">excel</button>
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=orderPageList></div>

</div>
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
</script>