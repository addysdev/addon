<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcUserManage_listSearch(curPage){

    	 curPage = (curPage==null) ? 1:curPage;
         productMasterConForm.curPage.value = curPage;

         commonDim(true);
         $.ajax({
             type: "POST",
                url:  "<%= request.getContextPath() %>/recover/recoverpagelist",
                     data:$("#productMasterConForm").serialize(),
                success: function(result) {
                    commonDim(false);
                    $("#productMasterPageList").html(result);
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
    
    //레이어팝업 : 사용자등록 Layer 팝업
    function fcUserManage_regForm(){

    	$('#userManageRegist').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/userregform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#userManageRegist").dialog('close');

                    });
            }
            ,close:function(){
                $('#userManageRegist').empty();
            }
        });
    };
  //레이어팝업 : 사용자수정 Layer 팝업
    function fcUserManage_detailSearch(userId){

    	$('#userManageModify').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/usermodifyform',{
    				'userId' : userId
    			});
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#userManageModify").dialog('close');

                    });
            }
            ,close:function(){
                $('#userManageModify').empty();
            }
        });
    };
    
    //체크박스 전체선택
    function fcUserManage_checkAll(){
    	$("input:checkbox[id='userCheck']").prop("checked", $("#userCheckAll").is(":checked"));
    }
    
    //사용자 삭제
    function fcUserManage_delete(){
    	
    	var checkedCnt = $('input:checkbox[ name="userCheck"]:checked').length;

    	if(checkedCnt <= 0){
        	alert("삭제 대상을 선택해 주세요!");
        	return;
        }
        
        var arrDelUserId = "";
        $('input:checkbox[name="userCheck"]').each(function() {
            if ($(this).is(":checked")) {
            	arrDelUserId += $(this).val() + "^";
            }   
        });
        
        var paramString = $("#userManagePageListForm").serialize() + "&arrDelUserId="+arrDelUserId;

        commonDim(true);
        $.ajax({
            type: "POST",
            url:  "<%= request.getContextPath() %>/manage/userdeletes",
            data:paramString,
            cache : false,
            success: function(result) {
                commonDim(false);
                if(result=='1'){
					 alert('사용자 삭제를 성공했습니다.');
				} else{
					 alert('사용자 삭제를 실패했습니다.');
				}
				
				fcUserManage_listSearch();
				
            },
            error:function(error){
                commonDim(false);
                alert('사용자 삭제를 실패했습니다.');
            }
        });

    }
    //레이어팝업 : 사용자등록 Layer 팝업
    function fcUserManage_excelForm(){

    	$('#userExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/userexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#userExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#userExcelForm').empty();
            }
        });
    };
</SCRIPT>
<div class="container">
	<h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 회수 리스트</font></strong></h4>
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="productConVO" id="productMasterConForm" name="productMasterConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
        		<label for="start_stockDate end_stockDate"><h6><strong><font style="color:#FF9900">  <span class="glyphicon glyphicon-search"></span>  회수일자 : </font></strong></h6></label>
				<div class='input-group date ' id='datetimepicker1' data-link-field="start_stockDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${stockConVO.start_stockDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="start_stockDate" name="start_stockDate" value="${stockConVO.start_stockDate}" />
	            </div>
	            <div class='input-group date' id='datetimepicker2'  data-link-field="end_stockDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${stockConVO.end_stockDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="end_stockDate" name="end_stockDate" value="${stockConVO.end_stockDate}" />
	            </div>
	            <br><br>
				<label for="con_groupId"><h6><strong><font style="color:#FF9900"> 지점선택 : </font></strong></h6></label>
				<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${stockConVO.groupId}">
                    <option value="AD001" >물류정상</option>
                    <option value="BD009" >반디울산</option>
                    <option value="YP008" >영풍청량리</option>
                </select>
				<label for="searchGubun"><h6><strong><font style="color:#FF9900"> 회수상태 : </font></strong></h6></label>
				<select class="form-control" title="발주상태" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >대기</option>
                    <option value="02" >보류</option>
           		</select>
           		<label for="searchGubun"><h6><strong><font style="color:#FF9900"> 검색조건 : </font></strong></h6></label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >매장명</option>
                    <option value="02" >발주자</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${productConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_listSearch()">search</button>
	            <button type="button" class="btn" onClick="">excel</button>
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=productMasterPageList></div>

  <button type="button" class="btn btn-primary" onClick="">회수등록</button>
</div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>