<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
 <head>
	<script>

	</script>
  </head>
  <body>
   <div class="container">
	  <form name ="userRegistForm" role="form">
	    <div class="form-group">
	    <th>
   		  <td>
			  아이디 : <input type="text" class="form-control" id="userId" name="firstStockAmt" tabindex="1" onKeyDown="if (event.keyCode==13 && this.value.length>=1) document.gmroiform.lastStockAmt.focus();else this.focus();">
			    기말 재고금액: <input onKeyUp="gmroiCal()" numberOnly placeholder="0" type="text" class="form-control" id="lastStockAmt" name="lastStockAmt" tabindex="2" onKeyDown="if (event.keyCode==13 && this.value.length>=1) document.gmroiform.totalSaleAmt.focus();else this.focus();">
	      </td>
          <td>
                              총 매출금액 : <input onKeyUp="gmroiCal()" numberOnly placeholder="0" type="text" class="form-control" id="totalSaleAmt"  name="totalSaleAmt" tabindex="3" onKeyDown="if (event.keyCode==13 && this.value.length>=1) document.gmroiform.profitSaleAmt.focus();else this.focus();">
	    	  매출 이익 : <input onKeyUp="gmroiCal()" numberOnly placeholder="0" type="text" class="form-control" id="profitSaleAmt" name="profitSaleAmt" tabindex="4" onKeyDown="if (event.keyCode==13 && this.value.length>=1) calReset();else this.focus();">
          </td>
			</th>
			<br>
            <td><button type="button" class="btn btn-primary" onClick="javascript:goUserSave()">저장</button></td>
	    </div>
	  </form>
	</div>
  </body>
</html>

 

