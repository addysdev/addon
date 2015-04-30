<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcUserManage_listSearch(curPage){

        userManageConForm.con_userId.value = "";
        curPage = (curPage==null) ? 1:curPage;
        userManageConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/manage/salespagelist",
                    data:$("#userManageConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#userManagePageList").html(result);
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
<!-- 사용자관리 -->
	<div class="container">
        <h4><span>매출현황관리</span></h4>
        <!-- 조회조건 -->
        <div class="search">
            <form:form commandName="userConVO" id="userManageConForm" name="userManageConForm" method="post" action="" >
            <input type="hidden" name="curPage"             id="curPage"            value="1" />
            <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
            <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
            <input type="hidden" name="con_userId"          id="con_userId"         value=""  />
            <input type="hidden" name="userId"          id="userId"         value="${userConVO.userId}"  />
            <fieldset>
                <table summary="사용자조회(그룹, 계정정보, 사용여부, 업체구분)">
                    <colgroup>
                        <col width="7%" />
                        <col width="15%" />
                        <col width="7%" />
                        <col width="10%" />
                        <col width="15%" />
                        <col width="7%" />
                        <col width="20%" />
                        <col width="*" />
                    </colgroup>
                    <tbody>
                    <tr>
                    	<div class="form-group">
                        <!-- label의 for값과 input의 id값을 똑같이 사용해주세요. -->
                        <th><label for="searchGubun">검색조건</label></th>
                        <td>
                            <select class="form-control" title="계정정보" id="searchGubun" name="searchGubun" >
                                <option value="01" >매장코드</option>
                                <option value="02" >매장명</option>
                            </select>
                        </td>
                        <td>    
                            <input type="text" class="form-control" id="searchValue" name="searchValue"  value="${userConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
                        </td>
                         <td><button type="button" class="btn btn-primary" onClick="javascript:fcUserManage_listSearch()">search</button></td>
                         <td><button type="button" class="btn" onClick="">excel</button></td>
                    </div>
                    </tr>
                    </tbody>
                </table>
            </fieldset>
            </form:form>
        </div >
        <!-- //조회 -->
  <!-- 조회결과리스트 -->
  <div id=userManagePageList>
  </div>
  <!-- //조회결과리스트 -->
  <!-- //사용자 등록/삭제 -->
  <button type="button" class="btn btn-primary" onClick="fcUserManage_excelForm()">매출 excel 업로드</button>
  <!-- 사용자 일괄등록-->
  <div id="userExcelForm"  title="사용자 일괄등록"></div>
  <!-- //사용자 일괄등록 -->
  <!-- 사용자 등록-->
  <div id="userManageRegist"  title="사용자 등록"></div>
  <!-- //사용자 등록 -->
  <!-- 사용자 수정-->
  <div id="userManageModify"  title="사용자 수정"></div>
  <!-- //사용자 수정 -->
</div>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>