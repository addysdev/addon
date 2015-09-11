<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcComunity_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        comunityConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/smart/comunitypagelist",
                    data:$("#comunityConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#comunityPageList").html(result);
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
    function fcComunity_procForm(idx,comment){

    	$('#comunityProcessForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 800,
            height : 500,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/smart/comunityprodessform?idx='+idx+'&comment='+encodeURIComponent(comment));
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#comunityProcessForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#comunityProcessForm').empty();

            }
        });
    };

</SCRIPT>
<div class="container-fluid">
    <!-- 서브타이틀 영역 : 시작 -->
	<div class="sub_title">
   		<p class="titleP">커뮤니티 관리</p>
	</div>
	<!-- 서브타이틀 영역 : 끝 -->
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="comunityConVO" id="comunityConForm" name="comunityConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
				<label for="searchGubun">검색조건 :</label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >핸드폰</option>
                    <option value="02" >커뮤니티 글</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="${comunityConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcComunity_listSearch()">조회</button>
	            <!-- >button type="button" class="btn" onClick="">excel</button -->
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=comunityPageList></div>
 
  <!-- 상담처리-->
  <div id="comunityProcessForm"  title="커뮤니티처리"></div>

</div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
<script>
fcComunity_listSearch();
MM_nbGroup('down','group7','menu_07','<%= request.getContextPath() %>/images/top/addys-menu_07_on.jpg',1);
</script>