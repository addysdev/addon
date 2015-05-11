<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>

    // 리스트 조회
    function fcStock_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        stockConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/master/stockpagelist",
                    data:$("#stockConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#stockPageList").html(result);
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
    //레이어팝업 : 재고현황등록 Layer 팝업
    function fcStock_excelForm(){

    	$('#stockExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 430,
            height : 550,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/master/stockexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#stockExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#stockExcelForm').empty();
            }
        });
    };

</SCRIPT>
<div class="container">
	 <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 재고 현황관리</font></strong></h4>
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="stockConVO" id="stockConForm" name="stockConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group" >
				<label for="start_stockDate end_stockDate"><font style="color:#FF9900">  <span class="glyphicon glyphicon-search"></span> 재고현황일자 : </font></label>
				<div style='width:150px' class='input-group date ' id='datetimepicker1' data-link-field="start_stockDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${stockConVO.start_stockDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="start_stockDate" name="start_stockDate" value="${stockConVO.start_stockDate}" />
	            </div>
	            <div style='width:150px' class='input-group date' id='datetimepicker2'  data-link-field="end_stockDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${stockConVO.end_stockDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="end_stockDate" name="end_stockDate" value="${stockConVO.end_stockDate}" />
	            </div>
				<c:choose>
	    		<c:when test="${strAuth == '03'}">
					<input type="hidden" id="con_groupId" name="con_groupId" value="${stockConVO.groupId}">
					</c:when>
					<c:otherwise>
						<label for="con_groupId"><font style="color:#FF9900"> 지점선택 : </font></label>
						<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${stockConVO.groupId}">
		                    <option value="">전체</option>
		                    <c:forEach var="groupVO" items="${group_comboList}" >
		                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
		                    </c:forEach>
		                </select>
					</c:otherwise>
				</c:choose>
                <button type="button" class="btn btn-primary" onClick="javascript:fcStock_listSearch()">search</button>
                <!--  >button type="button" class="btn" onClick="">excel</button-->
        	</div>
      	</fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=stockPageList>
  </div>
  <!-- //조회결과리스트 -->
  <!-- //사용자 등록/삭제 -->
  <button type="button" class="btn btn-primary" onClick="fcStock_excelForm()">재고현황 excel 업로드</button>
  <!-- 재고현황 일괄등록-->
  <div id="stockExcelForm"  title="재고현황 일괄등록"></div>
  <!-- //재고현황 일괄등록 -->
   <!-- 재고상세현황 페이지-->
  <div id="stockDetailManage"  title="재고상세 현황"></div>
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
</script>