<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcStockDetail_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        stockDetailConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/master/stockdetailpagelist",
                    data:$("#stockDetailConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#stockDetailPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcStockDetail_listSearch('1');
            return false;
        } else{
            return true;
        }
    }
    
</SCRIPT>
<!-- 사용자관리 -->
	<div class="container">
        <h4><span>재고상세현황관리</span></h4>
        <!-- 조회조건 -->
        <div class="search">
            <form:form commandName="stockDetailConVO" id="stockDetailConForm" name="stockDetailConForm" method="post" action="" >
            <input type="hidden" name="curPage"             id="curPage"            value="1" />
            <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
            <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
            <input type="hidden" name="stockDate"          id="stockDate"         value="${stockDetailConVO.stockDate}"  />
            <input type="hidden" name="groupId"          id="groupId"         value="${stockDetailConVO.groupId}"  />
            <fieldset>
                <table summary="재고사세현황조회">
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
                            <select class="form-control" title="검색조건" id="searchGubun" name="searchGubun" >
                                <option value="" >전체</option>
                                <option value="01" >품목코드</option>
                                <option value="02" >품목명</option>
                            </select>
                        </td>
                        <td>    
                            <input type="text" class="form-control" id="searchValue" name="searchValue"  value="" onkeypress="javascript:return checkKey(event);"/>
                        </td>
                         <td><button type="button" class="btn btn-primary" onClick="javascript:fcStockDetail_listSearch()">search</button></td>
                    </div>
                    </tr>
                    </tbody>
                </table>
            </fieldset>
            </form:form>
        </div >
        <!-- //조회 -->
  <!-- 조회결과리스트 -->
  <div id=stockDetailPageList>
  </div>
  <!-- //조회결과리스트 -->
</div>
<script>
	fcStockDetail_listSearch();
</script>