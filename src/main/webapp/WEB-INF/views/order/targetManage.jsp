<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcProductMaster_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        productMasterConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/order/targetpagelist",
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
        	fcProductManage_listInquiry('1');
            return false;
        } else{
            return true;
        }
    }
    
  //레이어팝업 : 사용자수정 Layer 팝업
    function fcProductMaster_detailSearch(productCode){

    	$('#productMasterDetail').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/master/productdetailform',{
    				'productCode' : productCode
    			});
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#productMasterDetail").dialog('close');

                    });
            }
            ,close:function(){
                $('#productMasterDetail').empty();
            }
        });
    };
   
</SCRIPT>
<div class="container">
	<h4><span>[발주대상리스트]</span></h4>
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="productConVO" id="productMasterConForm" name="productMasterConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
        	    <label for="con_groupId"> 지점선택 : </label>
				<select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${stockConVO.groupId}">
                    <option value="AD001" >물류정상</option>
                    <option value="BD009" >반디울산</option>
                    <option value="YP008" >영풍청량리</option>
                </select>
				<label for="searchGubun"> 발주상태 : </label>
				<select class="form-control" title="발주상태" id="searchGubun" name="searchGubun" value="">
                	<option value="01" >대기</option>
                    <option value="02" >보류</option>
           		</select>
				<button type="button" class="btn btn-primary" onClick="javascript:fcProductMaster_listSearch()">search</button>
	            <button type="button" class="btn" onClick="">excel</button>
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <br>
  <!-- 조회결과리스트 -->
  <div id=productMasterPageList></div>

</div>
<br>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>