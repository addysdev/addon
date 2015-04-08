<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcAdUserListManage_listSearch(curPage){
    	
        adUserListManageConForm.con_userId.value = "";
        curPage = (curPage==null) ? 1:curPage;
        adUserListManageConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/addys/userlist",
                    data:$("#adUserListManageConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#adUserListManageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
            fcAdUserListManage_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
    //레이어팝업 : 사용자등록
    function goAddysUserRegist(){
    	
        $('#addysUserRegist').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('');
                //$("#addysUserRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#addysUserRegist").dialog('close');

                    });
            }
            ,close:function(){
                $('#addysUserRegist').empty();
            }
        });
    };	
</SCRIPT>
<!-- 사용자관리 -->
	<div class="container">
        <h4><span>계정관리</span></h4>
        <!-- 조회조건 -->
        <div class="search">
            <form:form commandName="userCon" id="adUserListManageConForm" name="adUserListManageConForm" method="post" action="" >
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
                        <th><label for="con_userGroupCode">지점</label></th>
                        <td>
                            <select class="form-control" title="그룹" id="con_userGroupCode" name="con_userDeptCode" >
                               <option value="" selected>전체</option>
                                <c:forEach var="userGroup" items="${searchCondition1}" >
                                    <c:choose>
                                        <c:when test="${userGroup.code == userCon.con_userGroupCode}">
                                            <option value="${userGroup.code}"  selected>${userGroup.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${userGroup.code}">${userGroup.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
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
                        <th><label for="con_companyGbnCode">업체구분</label></th>
                        <td>
                            <select class="form-control" title="업체구분" id="con_companyGbnCode" name="con_companyGbnCode" >
                                <option value="">전체</option>
                                <c:forEach var="companyGbn" items="${searchCondition2}" >
                                    <c:choose>
                                        <c:when test="${companyGbn.code == userCon.con_companyGbnCode}">
                                            <option value="${companyGbn.code}"  selected>${companyGbn.name}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${companyGbn.code}">${companyGbn.name}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </td>
                         <td><button type="button" class="btn btn-primary" onClick="javascript:fcAdUserListManage_listSearch()">search</button></td>
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
  <div id=adUserListManageList>
  </div>
  <!-- //조회결과리스트 -->
  <!-- //사용자 등록/삭제 -->
  <button type="button" class="btn btn-primary" onClick="goAddysUserRegist()">regist</button>
  <button type="button" class="btn btn-danger">delete</button>
  <!-- 사용자등록-->
  <div id="addysUserRegist"  title="사용자 등록"></div>
  <!-- //사용자 등록 -->
</div>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>