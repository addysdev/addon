<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcSales_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        salesConForm.curPage.value = curPage;


        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/master/salespagelist",
                    data:$("#salesConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#salesPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcSales_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
  //레이어팝업 : 매출현황등록 Layer 팝업
    function fcSales_excelForm(){

    	$('#salesExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 430,
            height : 450,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/master/salesexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#salesExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#salesExcelForm').empty();
            }
        });
    };
    
</SCRIPT>
<div class="container-fluid">
    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">매출 현황관리</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="salesConVO" id="salesConForm" name="salesConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group" >
				<label for="start_salesDate end_salesDate">매출현황일자 :</label>
				<div style='width:155px' class='input-group date ' id='datetimepicker1' data-link-field="start_salesDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${salesConVO.start_salesDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="start_salesDate" name="start_salesDate" value="${salesConVO.start_salesDate}" />
	            </div>
	            <div style='width:155px' class='input-group date' id='datetimepicker2'  data-link-field="end_salesDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${salesConVO.end_salesDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="end_salesDate" name="end_salesDate" value="${salesConVO.end_salesDate}" />
	            </div>
				<c:choose>
	    		<c:when test="${strAuth == '03'}">
					<input type="hidden" id="con_groupId" name="con_groupId" value="${salesConVO.groupId}">
					</c:when>
					<c:otherwise>
						<label for="con_groupId">지점선택 :</label>
						<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${salesConVO.groupId}">
		                    <option value="">전체</option>
		                    <c:forEach var="groupVO" items="${group_comboList}" >
		                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
		                    </c:forEach>
		                </select>
					</c:otherwise>
				</c:choose>
                <button type="button" class="btn btn-primary" onClick="javascript:fcSales_listSearch()">조회</button>
                <!--  >button type="button" class="btn" onClick="">excel</button-->
        	</div>
      	</fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=salesPageList>
  </div>
  <!-- //조회결과리스트 -->
  <!-- //사용자 등록/삭제 -->
  <button type="button" class="btn btn-primary" onClick="fcSales_excelForm()">매출현황 엑셀 업로드</button>
  <!-- 재고현황 일괄등록-->
  <div id="salesExcelForm"  title="매출현황 일괄등록"></div>
  <!-- //재고현황 일괄등록 -->
   <!-- 재고상세현황 페이지-->
  <div id="salesDetailManage"  title="매출상세 현황"></div>
  <!-- //재고상세현황 페이지 -->
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
    
    fcSales_listSearch();
    MM_nbGroup('down','group3','menu_03','<%= request.getContextPath() %>/images/top/addys-menu_03_on.jpg',1);
</script>