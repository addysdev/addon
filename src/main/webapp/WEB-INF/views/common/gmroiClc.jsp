<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="<%= request.getContextPath() %>/images/favicon.ico" type='image/ico'>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/css/bootstrap.css">
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/bootstrap-3.3.4-dist/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/locales/bootstrap-datetimepicker.kr.js" charset="UTF-8"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery.number.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/addys.js"></script>
    <script type="text/javascript" src="<%= request.getContextPath() %>/js/utils.js"></script>
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/errorMsg.js"></script>
	<script>
	
	  function init(){

	    opener.selfClose();

	  }
	  
	  function firstInput(){
		  
		$('#firstStockAmt').focus(1); 
		
	  }
	 	  
	  function gmroiCal(){
		 
		  var avgStockAmt=Math.floor((isnullStr(parseInt(isnullStr(deleteCommaStr($('#firstStockAmt').val()))))+
				  isnullStr(parseInt(isnullStr(deleteCommaStr($('#lastStockAmt').val())))))/2);
		
		  var profitSaleAmt=isnullStr(parseInt(isnullStr(deleteCommaStr($('#profitSaleAmt').val()))));
		  var totalSaleAmt =isnullStr(parseInt(isnullStr(deleteCommaStr($('#totalSaleAmt').val()))));
		  var avgSaleRate=0;
		  
		  if(profitSaleAmt==0 || totalSaleAmt==0){
			  avgSaleRate=0;
		  }else{
			  avgSaleRate=(profitSaleAmt/totalSaleAmt)*10000;
			  avgSaleRate=(Math.floor(avgSaleRate)*0.01).toFixed(2);
		  }
		  
		  var stockCycleRate=0;
		  var gmroiRate=0;
		  
		  if(totalSaleAmt==0 || avgStockAmt==0){
			  stockCycleRate=0;
		  }else{
			  stockCycleRate=(totalSaleAmt/avgStockAmt)*100;
			  stockCycleRate=(Math.floor(stockCycleRate)*0.01).toFixed(2);
		  }
		  
		  if(profitSaleAmt==0 || avgStockAmt==0){
			  gmroiRate=0;
		  }else{
			  gmroiRate=(profitSaleAmt/avgStockAmt)*10000
			 
			  gmroiRate=(Math.round(gmroiRate)*0.01).toFixed(2);
		  }

		  $('#avgStockAmtclc').val=avgStockAmt;
		  document.all('avgStockAmt').innerText='평균 재고금액 : '+addCommaStr(''+avgStockAmt)+' 원';
		  $('#avgSaleRateclc').val=avgSaleRate
		  document.all('avgSaleRate').innerText='총 이익율 : '+addCommaStr(''+avgSaleRate)+' %';
		  
		  document.all('stockCycleRate').innerText='재고금액 회전율 : '+addCommaStr(''+stockCycleRate)+' 회전';
		  document.all('gmroiRate').innerText='GMROI : '+addCommaStr(''+gmroiRate)+' %';
		  
	  }
	  function calReset(){
		  
		  if(confirm('입력값을 초기화 하시겠습니까?')){
			 // $('#firstStockAmt').val=0;
			 // $('#lastStockAmt').val=0;
			 // $('#totalSaleAmt').val=0;
			 // $('#profitSaleAmt').val=0;
			  document.gmroiform.firstStockAmt.value=0;
			  document.gmroiform.lastStockAmt.value=0;
			  document.gmroiform.totalSaleAmt.value=0;
			  document.gmroiform.profitSaleAmt.value=0;
			  $('#firstStockAmt').focus(1); 
		  }
	  }
	  
	</script>
	
  </head>
  <body onload="init()">
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
            <td><button type="button" class="btn btn-primary" onClick="javascript:calReset()">reset</button></td>
          <h4><strong><font id="stockCycleRate" style="color:#428bca">재고금액 회전율 :</font></strong></h4>
          <h4><strong><font id="gmroiRate" style="color:#428bca">GMROI :</font></strong></h4>
	    </div>
	  </form>
	</div>
  </body>
</html>
<%@ include file="/WEB-INF/views/addys/footer.jsp" %>
