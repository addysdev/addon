<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcRecovery_listSearch(curPage){

    	 curPage = (curPage==null) ? 1:curPage;
         recoveryConForm.curPage.value = curPage;
         
         commonDim(true);
         $.ajax({
             type: "POST",
                url:  "<%= request.getContextPath() %>/recovery/recoverylist",
                     data:$("#recoveryConForm").serialize(),
                success: function(result) {
                    commonDim(false);
                    $("#recoveryList").html(result);

                },
                error:function() {
                    commonDim(false);
                }
         });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcRecovery_listSearch('1');
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
	<div class="container-fluid">
	<h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 회수 리스트</font></strong></h4>
	
	<table class="table table-bordered" >
	 	<tr>
          <th class='text-center'  style="background-color:#E6F3FF" >작업코드</th>
          <th class='text-center'><c:out value="${recoveryConVO.collectCode}"></c:out></th>
          <th class='text-center' style="background-color:#E6F3FF">회수요청일</th>
          <th class='text-center'><c:out value="${recoveryConVO.collectDateTime}"></c:out></th>
      	  <th class='text-center' style="background-color:#E6F3FF">회수마감일</th>
          <th class='text-center'><c:out value="${recoveryConVO.recoveryClosingDate}"></c:out></th>	
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#E6F3FF">메모</th>
          <th colspan='5' class='text-center'><input type="text" class="form-control" id="memo" name="memo"  value="${recoveryVO.memo}" placeholder="메모" disabled /></th>
      	</tr>
	  </table>
	  
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="recoveryConVO" id="recoveryConForm" name="recoveryConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <input type="hidden" name="collectCode"          id="collectCode"         value="${recoveryConVO.collectCode}"  />
        <fieldset>
        	<div class="form-group">
	            <c:choose>
	    		<c:when test="${strAuth == '03'}">
					<input type="hidden" id="con_groupId" name="con_groupId" value="${recoveryConVO.groupId}">
					</c:when>
					<c:otherwise>
					    <div style="position:absolute; left:30px" >
						<label for="con_groupId"><font style="color:#FF9900"> 지점선택 : </font></label>
						<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${recoveryConVO.groupId}">
		                    <option value="">전체</option>
		                    <c:forEach var="groupVO" items="${group_comboList}" >
		                    	<option value="${groupVO.groupId}">${groupVO.groupName}</option>
		                    </c:forEach>
		                </select>
		                <label for="searchGubun"><h6><strong><font style="color:#FF9900"> 회수상태 : </font></strong></h6></label>
						<select class="form-control" title="회수상태" id="con_recoveryState" name="con_recoveryState" value="${recoveryConVO.con_recoveryState}">
		                	<option value="">전체</option>
		                    <c:forEach var="codeVO" items="${code_comboList}" >
		                    	<option value="${codeVO.codeId}">${codeVO.codeName}</option>
		                    </c:forEach>
		           		</select>
						<button type="button" class="btn btn-primary" onClick="javascript:fcRecovery_listSearch()">조회</button>
						 <!-- >button type="button" class="btn" onClick="">excel</button -->
						</div>
						<div style="position:absolute; right:30px" >
			        	 	<button id="rcancelbtn" name="rcancelbtn" type="button" class="btn btn-danger" onClick="alert('회수 취소 처리 개발중')">회수취소</button>
			        	 	<button id="rexportbutton" name="rexportbutton" type="button" class="btn btn-primary" onClick="alert('전표생성 개발중')">전표생성</button>
			        	</div>
					</c:otherwise>
				</c:choose>
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br> <br>
  <!-- 조회결과리스트 -->
  <div id=recoveryList></div>

</div>
<script>
//alert('${recoveryConVO.con_recoveryState}');
document.recoveryConForm.con_recoveryState.value='${recoveryConVO.con_recoveryState}';
fcRecovery_listSearch();
</script>
