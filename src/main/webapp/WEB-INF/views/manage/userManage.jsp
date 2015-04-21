<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcUserListManage_listSearch(curPage){
    	//alert('<%= request.getContextPath() %>/manage/userlist');
        userListManageConForm.con_userId.value = "";
        curPage = (curPage==null) ? 1:curPage;
        userListManageConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/manage/userlist",
                    data:$("#userListManageConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#userListManageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
            fcUserListManage_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
    //레이어팝업 : 사용자등록
    function goUserRegist(){
    	
        $('#userRegist').dialog({
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
                    $("#userRegist").dialog('close');

                    });
            }
            ,close:function(){
                $('#userRegist').empty();
            }
        });
    };
    
</SCRIPT>
<!-- 사용자관리 -->
	<div class="container">
        <h4><span>계정관리</span></h4>
        <!-- 조회조건 -->
        <div class="search">
            <form:form commandName="userCon" id="userListManageConForm" name="userListManageConForm" method="post" action="" >
            <input type="hidden" name="curPage"             id="curPage"            value="1" />
            <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
            <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
            <input type="hidden" name="con_userId"          id="con_userId"         value=""  />
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
                        <th><label for="con_groupId">지점</label></th>
                         <td>    
                            <input type="text" class="form-control" id="con_groupId" name="con_groupId"  value="${userCon.groupId}" />
                        </td>
                        <th><label for="con_useYn">사용여부</label></th>
                        <td>
                            <select class="form-control" title="사용유무" id="con_useYn" name="con_useYn" >
                                <option value="" >전체</option>
                                <option value="Y" >사용</option>
                                <option value="N" >미사용</option>
                            </select>
                        </td>
                        <th><label for="searchGubun">계정정보</label></th>
                        <td>
                            <select class="form-control" title="계정정보" id="searchGubun" name="searchGubun" >
                                <option value="01" >이름</option>
                                <option value="02" >아이디</option>
                            </select>
                        </td>
                        <td>    
                            <input type="text" class="form-control" id="searchValue" name="searchValue"  value="${userCon.searchValue}" onkeypress="javascript:return checkKey(event);"/>
                        </td>
                         <td><button type="button" class="btn btn-primary" onClick="javascript:fcUserListManage_listSearch()">search</button></td>
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
  <div id=userListManageList>
  </div>
  <!-- //조회결과리스트 -->
  <!-- //사용자 등록/삭제 -->
  <button type="button" class="btn btn-primary" onClick="goUserRegist()">regist</button>
  <button type="button" class="btn btn-danger">delete</button>
  <!-- 사용자등록-->
  <div id="userRegist"  title="사용자 등록"></div>
  <!-- //사용자 등록 -->
</div>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>