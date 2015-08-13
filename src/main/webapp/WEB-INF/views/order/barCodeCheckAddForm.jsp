<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="taglib"%>
<head>
<title>바코드 검수</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<style>
	/* 서브타이틀 영역 (.sub_title) */
	.sub_title { margin-bottom:20px; width:100%; height:43px; border-bottom:1px solid #ccc; }
	.sub_title .titleP { position:relative; left:0; float:left; margin-left:-7px; padding-left:31px; _padding-top:3px; height:28px; _height:25px; background:url(../images/sub/icon_title.gif) no-repeat; font-family:Malgun Gothic, "맑은 고딕", "돋움", dotum, verdana, Tahoma, sans-serif; font-size:16px; color:#2c3546; font-weight:bold; letter-spacing:-1px; }
	.seachNm { float:left; margin:1px 0 0 20px; padding:4px 0 0 20px; padding-top:5px\9; height:16px; height:15px\9; border:1px solid #cbcbcb; background:#eef0f4; }
	.sub_title .seachNm li { display:inline; padding:0 15px 0 8px; background:url(../images/sub/icon_01.gif) no-repeat 0 2px; }

    /* 공통부분  */

	body { margin:0; padding:0; } 
		
	div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,form,fieldset,p,button{margin:0;padding:0;}
	body,h1,h2,h3,h4,th,td,input, select, textarea {color:#666; font-family:"돋움", dotum, verdana, Tahoma, sans-serif; font-size:11px; font-weight:normal; padding:0px; margin:0px}
	html { scrollbar-arrow-color: #999; scrollbar-3dlight-color: #e6e6e6; scrollbar-darkshadow-color: #e9e9e9; scrollbar-face-color: #f4f4f0; scrollbar-highlight-color:#FFFFFF; scrollbar-shadow-color: #d0d0d0; scrollbar-track-color: #F2F2F2; }
	hr{display:none;}
	img,fieldset,iframe {border:0;}
	ul,ol,li{list-style:none; margin:0; padding:0;}
	img,input,select,textarea{
		vertical-align:text-top;
	}
	input, label { vertical-align:middle;}
	caption, legend { display: none; }
	em, address { margin:0; padding:0; font-style:normal }
	.vm{vertical-align:middle !important;}
	.bn{border:none !important}
	.chk,.rdo{widows:13px;height:13px;margin:0 !important;padding:0 !important;vertical-align:middle}
	.chk_label{position:relative;top:1px;*top:2px;left:0px}
	.blind{overflow:hidden;position:absolute;visibility:hidden;width:0;height:0;font-size:0;line-height:0}
	
</style>
<!-- Latest compiled and minified CSS-->
<link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap-datetimepicker.min.css">

<!-- Latest compiled JavaScript--> 
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>

<script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/locales/bootstrap-datetimepicker.kr.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.number.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/utils.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/errorMsg.js"></script>
<SCRIPT>
function fcfCode_close(){

	this.close();
}

function fcAdd_checkSetting(index){

	var frm=document.addChekcListForm;
	
	var bacode='';
	var prodcode='';
	var prodname='';
	var prodprice='';
	var checkcnt='';
	
	var bacodeCnt = frm.barCode.length;
	
	if(bacodeCnt==undefined){
		bacodeCnt=1;
	}
//alert('fcAdd_checkSetting :: '+index);
	if(bacodeCnt > 1){
		bacode=frm.barCode[index].value;
		prodcode=frm.productCode[index].value;
		prodname=frm.productName[index].value;
		prodprice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderResultPriceView[index].value))));
		checkcnt=frm.orderResultCnt[index].value;
	}else{
		bacode=frm.barCode.value;
		prodcode=frm.productCode.value;
		prodname=frm.productName.value;
		prodprice=isnullStr(parseInt(isnullStr(deleteCommaStr(frm.orderResultPriceView.value))));
		checkcnt=frm.orderResultCnt.value;
	}
	
	//01.검수추가여부 확인창 필요
	if (confirm('['+prodname+']을 검수에 추가 하시겠습니까?\n추가하실경우 바코드로 재검수는 불가능합니다.\n(바코드 재검수시 보류처리후 다시 검수하시기 바랍니다.)')){ 

		//alert(document.all('addcheckbtn'));
		//02.검수추가 버튼 비활성 
		if(bacodeCnt > 1){
			document.all('addcheckbtn')[index].disabled=true;
		}else{
			document.all('addcheckbtn').disabled=true;
		}
		
		//02.검수추가 데이타 세팅필요
		
		var totalprice=prodprice*checkcnt;
		
		opener.addCheckSet(bacode,prodcode,prodname,prodprice,checkcnt,addCommaStr(''+prodprice),addCommaStr(''+totalprice));

	}

}
// 품목 상세 페이지 리스트 Layup
function  fcEtc_detail(orderCode,productCode,productName,etc,idx,companyCode) {

	//$('#targetEtcView').attr('title',productName);
	var url='<%= request.getContextPath() %>/order/etcmanage';

	if(document.addChekcListForm.barCode.length==undefined){
		idx=0;
	}

	$('#etcManage').dialog({
        resizable : false, //사이즈 변경 불가능
        draggable : true, //드래그 불가능
        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

        width : 800,
        height : 500,
        modal : true, //주위를 어둡게

        open:function(){
            //팝업 가져올 url
          //  $(this).load(url+'?orderCode='+orderCode+'&productCode='+productCode+'&productNaem='+encodeURIComponent(productName));
            $(this).load(url+'?orderCode='+orderCode+'&category=04'+'&idx='+idx+'&productCode='+productCode+'&companyCode='+companyCode+'&productName='+encodeURIComponent(productName)+'&etc='+encodeURIComponent(etc));
           
            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                $("#etcManage").dialog('close');

                });
        }
        ,close:function(){
            $('#etcManage').empty();
        }
    });
};
//opener.close();

</SCRIPT>
</head>
<!-- 사용자관리 -->
<body>
<div class="container-fluid">
	<form:form commandName="orderVO" name="addChekcListForm" method="post" action="" >
	  <br>
	   <div class="thead">
		   <table cellspacing="0" border="0" summary="" class="table table-bordered tbl_type" style="table-layout: fixed">
		    <caption></caption>
	 		<colgroup>
	 		 <col width="120px" />
	         <col width="80px" />
	         <col width="270px" />
	         <col width="70px" /> 
	         <col width="90px" /> 
	         <col width="70px" /> 
	         <col width="*" />
	        </colgroup>
		    <thead>
		      <tr style="background-color:#E6F3FF">
		        <th class='text-center'>바코드</th>
		        <th class='text-center'>품목코드</th>
		        <th class='text-center'>상품명</th>
		        <th class='text-center'>검수수량</th>
		        <th class='text-center'>검수금액</th>
		        <th class='text-center'>비고</th>
		        <th class='text-center'>검수추가</th>
		      </tr>
		    </thead>
		  </table>
		  </div>
		  <div class="tbody">
		    <table cellspacing="0" border="0" summary="" class="table table-bordered tbl_type" style="table-layout: fixed"> 
		      <caption></caption>
		      <colgroup>
		      <col width="120px" />
	          <col width="80px" />
	          <col width="270px" />
	          <col width="70px" />
	          <col width="90px" />
	          <col width="70px" />  
	          <col width="*" />
		      </colgroup>
		       <!-- :: loop :: -->
		                <!--리스트---------------->
		      <tbody>
		       <c:if test="${!empty addDetailList}">
	            <c:forEach items="${addDetailList}" var="orderVO" varStatus="status">
	            <tr id="select_tr_${orderVO.barCode}">
	              <input type="hidden" name="productPrice"  id="productPrice" value="${orderVO.productPrice}">
	              <input type="hidden" name="productName"  id="productName" value="${orderVO.productName}">
	              <input type="hidden" name="barCode"  id="barCode" value="${orderVO.barCode}">
	              <input type="hidden" name="productCode"  id="productCode" value="${orderVO.productCode}">
	              <input type="hidden" name="orderResultCnt"  id="orderResultCnt" value="${orderVO.orderResultCnt}">
	              <input type="hidden" name="orderResultPrice"  id="orderResultPrice" value="${orderVO.productPrice}">
	              <td class='text-center'><c:out value="${orderVO.barCode}"></c:out></td>
	              <td class='text-center'><c:out value="${orderVO.productCode}"></c:out></td>
	              <td><c:out value="${orderVO.productName}"></c:out></td>
	              <td class='text-center'><c:out value="${orderVO.orderResultCnt}"></c:out></td>
	              <td class='text-right'><input type="text" class="form-control" id="orderResultPriceView" maxlength="9" numberOnly name="orderResultPriceView"  value="${orderVO.orderResultPriceView}"></td>
	              <c:choose>
	                <c:when test="${orderVO.productCode != null }">
	                <td class='text-right' id="etcAdd" name="etcAdd"><img id="etcbtn" onClick="fcEtc_detail('${orderCode}','${orderVO.productCode}','${orderVO.productName}','','${status.count}','${companyCode}')" src="<%= request.getContextPath()%>/images/common/ico_company.gif" width="16" height="16" align="absmiddle" title="비고">(<span id="etcCnt">0</span>)</td>
	                <td class='text-center'><button type="button" id="addcheckbtn" class="btn btn-xs btn-success" onClick="fcAdd_checkSetting('${status.count-1}');">검수추가</button></td>
					</c:when>
					<c:otherwise>
					<td></td>
					<td class='text-center'><button type="button" id="addcheckbtn" class="btn btn-xs btn-danger" disabled>미일치</button></td>
					</c:otherwise>
				  </c:choose>
	              
	             </tr>
	            </c:forEach>
	           </c:if>
	          <c:if test="${empty addDetailList}">
	             <tr>
	                 <td colspan='7' class='text-center'>조회된 데이터가 없습니다.</td>
	             </tr>
	         </c:if>
		    </tbody>
		   </table>
		  </div>
	<button id="deferpopclosebtn" type="button" class="btn btn-default" onClick="fcfCode_close()">닫기</button>
</form:form>
</div>
<div id="etcManage"  title="비고"></div>
</body>
