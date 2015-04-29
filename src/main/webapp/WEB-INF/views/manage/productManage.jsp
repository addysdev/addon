<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcProductManage_listSearch(curPage){

        productManageConForm.con_productCode.value = "";
        curPage = (curPage==null) ? 1:curPage;
        productManageConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/manage/productpagelist",
                    data:$("#productManageConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#productManagePageList").html(result);
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
    function fcProductManage_detailSearch(productCode){

    	$('#productManageDetail').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/productdetailform',{
    				'productCode' : productCode
    			});
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#productManageDetail").dialog('close');

                    });
            }
            ,close:function(){
                $('#productManageDetail').empty();
            }
        });
    };
    
    
    //레이어팝업 : 품목등록 Layer 팝업
    function fcProduct_excelForm(){

    	$('#productExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/productexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#productExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#productExcelForm').empty();
            }
        });
    };
    //레이어팝업 : 안전재고 Layer 팝업
    function fcSafeStock_excelForm(){

    	$('#safeStockExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/safestockexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#safeStockExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#safeStockExcelForm').empty();
            }
        });
    };
    //레이어팝업 : 보유재고 Layer 팝업
    function fcHoldStock_excelForm(){

    	$('#holdStockExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/manage/holdstockexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#holdStockExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#holdStockExcelForm').empty();
            }
        });
    };
</SCRIPT>
<!-- 사용자관리 -->
	<div class="container">
        <h4><span>품목현황관리</span></h4>
        <!-- 조회조건 -->
        <div class="search">
            <form:form commandName="productConVO" id="productManageConForm" name="productManageConForm" method="post" action="" >
            <input type="hidden" name="curPage"             id="curPage"            value="1" />
            <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
            <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
            <fieldset>
                <table summary="품목조회">
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
                                <option value="01" >품목코드</option>
                                <option value="02" >품목명</option>
                            </select>
                        </td>
                        <td>    
                            <input type="text" class="form-control" id="searchValue" name="searchValue"  value="${userConVO.searchValue}" onkeypress="javascript:return checkKey(event);"/>
                        </td>
                         <td><button type="button" class="btn btn-primary" onClick="javascript:fcProductManage_listSearch()">search</button></td>
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
  <div id=productManagePageList>
  </div>
  <!-- //조회결과리스트 -->
  <!-- //사용자 등록/삭제 -->
  <button type="button" class="btn btn-primary" onClick="fcProduct_excelForm()">품목 excel 업로드</button>
  <button type="button" class="btn btn-primary" onClick="fcSafeStock_excelForm()">안전재고 excel 업로드</button>
  <button type="button" class="btn btn-primary" onClick="fcHoldStock_excelForm()">보유재고 excel 업로드</button>
  <!-- 품목 일괄등록-->
  <div id="productExcelForm"  title="품목 일괄등록"></div>
  <!-- //품목 일괄등록 -->
  <!-- 안전재고 일괄등록-->
  <div id="safeStockExcelForm"  title="안저재고 일괄등록"></div>
  <!-- //안전재고 일괄등록 -->
  <!-- 보유재고 일괄등록-->
  <div id="holdStockExcelForm"  title="보유재고 일괄등록"></div>
  <!-- //보유재고 일괄등록 -->
</div>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>