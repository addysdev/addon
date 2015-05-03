<%@ include file="/WEB-INF/views/addys/top.jsp" %>
<SCRIPT>
    // 리스트 조회
    function fcStock_listSearch(curPage){

        curPage = (curPage==null) ? 1:curPage;
        stockConForm.curPage.value = curPage;

        commonDim(true);
        $.ajax({
            type: "POST",
               url:  "<%= request.getContextPath() %>/master/stockpagelist",
                    data:$("#stockConForm").serialize(),
               success: function(result) {
                   commonDim(false);
                   $("#stockPageList").html(result);
               },
               error:function() {
                   commonDim(false);
               }
        });
    }
    /// key down function (엔터키가 입력되면 검색함수 호출)
    function checkKey(event){
        if(event.keyCode == 13){
        	fcUserManage_listInquiry('1');
            return false;
        } else{
            return true;
        }
    }
    //레이어팝업 : 재고현황등록 Layer 팝업
    function fcStock_excelForm(){

    	$('#stockExcelForm').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/master/stockexcelform');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#stockExcelForm").dialog('close');

                    });
            }
            ,close:function(){
                $('#stockExcelForm').empty();
            }
        });
    };
    
</SCRIPT>
<!-- 사용자관리 -->
	<div class="container">
        <h4><span>재고현황관리</span></h4>
        <!-- 조회조건 -->
        <div class="search">
            <form:form commandName="stockConVO" id="stockConForm" name="stockConForm" method="post" action="" >
            <input type="hidden" name="curPage"             id="curPage"            value="1" />
            <input type="hidden" name="rowCount"            id="rowCount"           value="10"/>
            <input type="hidden" name="totalCount"          id="totalCount"         value=""  />
            <fieldset>
                <table summary="재고현황조회">
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
                            <select class="form-control" title="지점정보" id="con_groupId" name="con_groupId" value="${stockConVO.groupId}">
                                <option value="AD001" >물류정상</option>
                                <option value="BD009" >반디울산</option>
                                <option value="YP008" >영풍청량리</option>
                            </select>
                        </td>
                        <td>    
                            <input type="text" class="form-control" id=start_stockDate name="start_stockDate"  value="${stockConVO.start_stockDate}" onkeypress="javascript:return checkKey(event);"/>
                        </td>~
                        <td>    
                            <input type="text" class="form-control" id="end_stockDate" name="end_stockDate"  value="${stockConVO.end_stockDate}" onkeypress="javascript:return checkKey(event);"/>
                        </td>
                         <td><button type="button" class="btn btn-primary" onClick="javascript:fcStock_listSearch()">search</button></td>
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
  <div id=stockPageList>
  </div>
  <!-- //조회결과리스트 -->
  <!-- //사용자 등록/삭제 -->
  <button type="button" class="btn btn-primary" onClick="fcStock_excelForm()">재고현황 excel 업로드</button>
  <!-- 재고현황 일괄등록-->
  <div id="stockExcelForm"  title="재고현황 일괄등록"></div>
  <!-- //재고현황 일괄등록 -->
   <!-- 재고상세현황 페이지-->
  <div id="stockDetailManage"  title="재고상세 현황"></div>
  <!-- //재고상세현황 페이지 -->
</div>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>