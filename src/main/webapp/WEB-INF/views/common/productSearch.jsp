<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcProduct_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        productConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/master/productsearchlist",
                    data:$("#productConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#productSearchList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcProduct_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
 
</SCRIPT>
<body>
  <div class="container-fluid">
	<h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-book"></span> 품목조회</font></strong></h4>
	  <!-- 조회조건 -->
	  <form:form class="form-inline" role="form" commandName="productConVO" id="productConForm" name="productConForm" method="post" action="" >
        <input type="hidden" name="curPage"             id="curPage"            value="1" />
        <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
        <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
        <fieldset>
        	<div class="form-group">
				<label for="searchGubun"><h6><strong><font style="color:#FF9900">  <span class="glyphicon glyphicon-search"></span> 검색조건 :</font></strong></h6></label>
				<select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" value="">
                	<option value="03" >품목명</option>
                    <option value="01" >품목코드</option>
                    <option value="04" >구매처명</option>
           		</select>
				<label class="sr-only" for="searchValue"> 조회값 </label>
				<input type="text" class="form-control" id="searchValue" name="searchValue"  value="" onkeypress="javascript:return checkKey(event);"/>
				<button type="button" class="btn btn-primary" onClick="javascript:fcProduct_listSearch()">search</button>
            </div>
	    </fieldset>
	  </form:form>
	  <!-- //조회 -->
  <!-- 조회결과리스트 -->
  <div id=productSearchList></div>
</div>
</body>
<script>
fcProduct_listSearch();
</script>