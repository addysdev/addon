<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcRecovery_listSearch(curPage){

    	 curPage = (curPage==null) ? 1:curPage;
         recoveryConForm.curPage.value = curPage;

         commonDim(true);
         $.ajax({
             type: "POST",
                url:  "<%= request.getContextPath() %>/recovery/recoverypagelist",
                     data:$("#recoveryConForm").serialize(),
                success: function(result) {
                    commonDim(false);
                    $("#recoveryPageList").html(result);
                },
                error:function() {
                    commonDim(false);
                }
         });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcUserManage_listInquiry('1');
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
<div class="container">
	<h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 회수 리스트</font></strong></h4>
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="recoveryConVO" id="recoveryConForm" name="recoveryConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
        		<label for="start_recoveryDate end_recoveryDate"><h6><strong><font style="color:#FF9900">  <span class="glyphicon glyphicon-search"></span>  회수일자 : </font></strong></h6></label>
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
	            <br><br>
	            <c:choose>
	    		<c:when test="${strAuth == '03'}">
					<input type="hidden" id="con_groupId" name="con_groupId" value="${recoveryConVO.groupId}">
					</c:when>
					<c:otherwise>
						<label for="con_groupId"><font style="color:#FF9900"> 지점선택 : </font></label>
						<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${recoveryConVO.groupId}">
		                    <option value="">전체</option>
		                    <c:forEach var="groupVO" items="${group_comboList}" >
		                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
		                    </c:forEach>
		                </select>
					</c:otherwise>
				</c:choose>
				<label for="searchGubun"><h6><strong><font style="color:#FF9900"> 회수상태 : </font></strong></h6></label>
				<select class="form-control" title="회수상태" id="con_orderState" name="con_recoveryState" value="">
                	<option value="">전체</option>
                    <c:forEach var="codeVO" items="${code_comboList}" >
                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
                    </c:forEach>
           		</select>
				<button type="button" class="btn btn-primary" onClick="javascript:fcRecovery_listSearch()">search</button>
	            <!-- >button type="button" class="btn" onClick="">excel</button -->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=recoveryPageList></div>
  <c:if test="${strAuth != '03'}"><button type="button" class="btn btn-primary" onClick="fcRecovery_registForm()">회수등록</button></c:if>
  
  <div id="recoveryRegForm"  title="회수대상 등록"></div>
  <!-- //회수 등록화면 -->
    <!--회수 상세처리화면-->
  <div id="recoveryDetailView"  title="회수 상세처리화면"></div>
  <!-- //회수 상세처리화면 -->
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