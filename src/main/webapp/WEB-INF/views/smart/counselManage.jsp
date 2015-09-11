<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcCounsel_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        counselConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/smart/counselpagelist",
                    data:$("#counselConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#counselPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcUserManage_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
    //레이어팝업 : 상담처리 Layer 팝업
    function fcCounsel_procForm(idx){

    	$('#counselProcessForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 400,
            height : 580,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/smart/counselprodessform?idx='+idx);
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#counselProcessForm").dialog('close');
                
                    counsetStateChekc();
                 

                    });
            }
            ,close:function(){
                $('#counselProcessForm').empty();
                
                counsetStateChekc();
            }
        });
    };

</SCRIPT>
<div class="container-fluid">
    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">상담관리</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="counselConVO" id="counselConForm" name="counselConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
				<label for="searchGubun">검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >고객명</option>
                    <option value="02" >고객ID</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${userConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcCounsel_listSearch()">조회</button>
	            <!-- >button type="button" class="btn" onClick="">excel</button -->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=counselPageList></div>
 
  <!-- 상담처리-->
  <div id="counselProcessForm"  title="상담처리"></div>

</div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
<script>
fcCounsel_listSearch();
MM_nbGroup('down','group7','menu_07','<%= request.getContextPath() %>/images/top/addys-menu_07_on.jpg',1);
</script>