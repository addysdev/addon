<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcWorkManage_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        workManageConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/manage/workhistorypagelist",
                    data:$("#workManageConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#workHistoryPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcWorkManage_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
   
</SCRIPT>
<div class="container-fluid">
    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">업무이력 관리</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="workConVO" id="workManageConForm" name="workManageConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
				<label for="searchGubun">검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >사용자명</option>
                    <option value="02" >사용자코드</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${workConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcCompanyManage_listSearch()">조회</button>
	            <!--  >button type="button" class="btn" onClick="">excel</button-->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=workHistoryPageList></div>

</div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
<script>
fcWorkManage_listSearch();
MM_nbGroup('down','group4','menu_04','<%= request.getContextPath() %>/images/top/addys-menu_04_on.jpg',1);
</script>