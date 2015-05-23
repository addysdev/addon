<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcCollect_listSearch(curPage){

    	 curPage = (curPage==null) ? 1:curPage;
         collectConForm.curPage.value = curPage;

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
</SCRIPT>
<div class="container">
	<h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 회수작업 리스트</font></strong>
	<c:if test="${strAuth != '03'}"><button type="button" class="btn btn-primary" onClick="fcRecovery_registForm()">회수등록</button></c:if>
	</h4>
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="collectConVO" id="collectConForm" name="collectConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
        		<label for="start_recoveryDate end_recoveryDate"><h6><strong><font style="color:#FF9900">  <span class="glyphicon glyphicon-search"></span>  회수요청일자 : </font></strong></h6></label>
				<div style='width:150px' class='input-group date ' id='datetimepicker1' data-link-field="start_recoveryDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${recoveryConVO.start_recoveryDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="start_recoveryDate" name="start_recoveryDate" value="${recoveryConVO.start_recoveryDate}" />
	            </div>
	            <div style='width:150px' class='input-group date' id='datetimepicker2'  data-link-field="end_recoveryDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${recoveryConVO.end_recoveryDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="end_recoveryDate" name="end_recoveryDate" value="${recoveryConVO.end_recoveryDate}" />
	            </div>
				<label for="searchGubun"><h6><strong><font style="color:#FF9900"> 회수상태 : </font></strong></h6></label>
				<select class="form-control" title="회수상태" id="con_orderState" name="con_recoveryState" value="">
                	<option value="">전체</option>
                    <c:forEach var="codeVO" items="${code_comboList}" >
                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
                    </c:forEach>
           		</select>
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
  <div id="collectDetailView"  title="회수 상세처리화면"></div>
  <!-- //회수 상세처리화면 -->
  
  	<div id="memoManage"  title="메모관리"></div>
    <!-- //메모 상세화면 -->
    <div id="etcManage"  title="비고"></div>
    <!-- //비고 상세화면 -->
</div>

 <div id="recoveryProductList"  title="회수대상 품목조회"></div>
  <!-- //검수 상세처리화면 -->
  
    <!-- 보유재고 일괄등록-->
  <div id="reProductExcelForm"  title="회수품목 일괄등록"></div>
  
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