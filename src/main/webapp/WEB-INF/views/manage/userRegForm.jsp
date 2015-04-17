<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
 <head>
     <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/jquery-ui-1.10.3.custom.css" />
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css">
	<!-- Latest compiled JavaScript -->
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-ui-1.10.3.custom.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.number.js"></script>
	
	 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

  <script src="//code.jquery.com/jquery-1.10.2.js"></script>

  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

	
	<script>
	function popTest(){
		
	    $( '#userRegist2' ).dialog()({
	        resizable : false, //사이즈 변경 불가능
	        draggable : true, //드래그 불가능
	        closeOnEscape : true, //ESC 버튼 눌렀을때 종료

	        width : 480,
	        height : 518,
	        modal : true, //주위를 어둡게

	        open:function(){
	            //팝업 가져올 url
	            $(this).load('<%= request.getContextPath() %>/gmroiclc');
	            //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
	            $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
	                $("#userRegist2").dialog('close');

	                });
	        }
	        ,close:function(){
	            $('#userRegist2').empty();
	        }
	    });
		
	};
	//레이어팝업 : 사용자등록
    function goUserRegist2(){
    	
        $('#userRegist').dialog({
            resizable : false, //사이즈 변경 불가능
            draggable : true, //드래그 불가능
            closeOnEscape : true, //ESC 버튼 눌렀을때 종료

            width : 480,
            height : 518,
            modal : true, //주위를 어둡게

            open:function(){
                //팝업 가져올 url
                $(this).load('<%= request.getContextPath() %>/gmroiclc');
                //$("#userRegist").dialog().parents(".ui-dialog").find(".ui-dialog-titlebar").hide();
                $(".ui-widget-overlay").click(function(){ //레이어팝업외 화면 클릭시 팝업 닫기
                    $("#userRegist").dialog('close');

                    });
            }
            ,close:function(){
                $('#userRegist').empty();
            }
        });
    };


	</script>
	
  </head>
  <body>
   <div class="container">
	  <h4><strong><font style="color:#428bca">ADDYS GMROI 계산기</font></strong></h4>
      <br>
	  <form name ="gmroiform" role="form">
  		<label for="">[재고금액]</label> 
	    <div class="form-group">
	    	<th>
   		  <td>
			    기초 재고금액 : <input  onKeyUp="gmroiCal()"   numberOnly placeholder="0"   type="text" class="form-control" id="firstStockAmt" name="firstStockAmt" tabindex="1" onKeyDown="if (event.keyCode==13 && this.value.length>=1) document.gmroiform.lastStockAmt.focus();else this.focus();">
			    기말 재고금액: <input onKeyUp="gmroiCal()" numberOnly placeholder="0" type="text" class="form-control" id="lastStockAmt" name="lastStockAmt" tabindex="2" onKeyDown="if (event.keyCode==13 && this.value.length>=1) document.gmroiform.totalSaleAmt.focus();else this.focus();">
	      </td>
			 </th>
		    <th>
            <h5><strong><font id="avgStockAmt" style="color:#FF9900">평균 재고금액 :</font></strong></h5>
            <input type=hidden name="avgStockAmtclc">
        </div>    
	    <label for="">[매출금액]</label> 
        <div class="form-group">  
	    	<th>
          <td>
                              총 매출금액 : <input onKeyUp="gmroiCal()" numberOnly placeholder="0" type="text" class="form-control" id="totalSaleAmt"  name="totalSaleAmt" tabindex="3" onKeyDown="if (event.keyCode==13 && this.value.length>=1) document.gmroiform.profitSaleAmt.focus();else this.focus();">
	    	  매출 이익 : <input onKeyUp="gmroiCal()" numberOnly placeholder="0" type="text" class="form-control" id="profitSaleAmt" name="profitSaleAmt" tabindex="4" onKeyDown="if (event.keyCode==13 && this.value.length>=1) calReset();else this.focus();">
          </td>
			</th>
            <h5><strong><font id="avgSaleRate" style="color:#FF9900">총 이익율 :</font></strong></h5>
            <input type=hidden name="avgSaleRateclc">
            <br>
            <td><button type="button" class="btn btn-primary" onClick="javascript:goUserRegist2()">reset</button></td>
            <td><button type="button" class="btn btn-primary" onClick="javascript:popTest()">reset2</button></td>
          <h4><strong><font id="stockCycleRate" style="color:#428bca">재고금액 회전율 :</font></strong></h4>
          <h4><strong><font id="gmroiRate" style="color:#428bca">GMROI :</font></strong></h4>
	    </div>
	  </form>
	</div>
  </body>
</html>
<div id="userRegist2" title="사용자 등록"></div>
<div id="userRegist"  title="사용자 등록"></div>


 

