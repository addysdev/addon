<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<SCRIPT>
   

</SCRIPT>
	<div class="container-fluid">
	 <div class="form-group" >
	 <form:form commandName="targetVO" name="companyDetailForm" method="post" action="" >
      <h4><strong><font style="color:#428bca"> <span class="glyphicon glyphicon-check"></span> 발주방법 : </font></strong>
          <input type="checkbox" id="emailCheck" name="emailCheck" value="" title="선택" />e-mail
          <input type="checkbox" id="smsCheck" name="smsCheck" value="" title="선택" />sms
      </h4>
      <button type="button" class="btn btn-primary" onClick="fcUserManage_excelForm()">보류</button>
      <button type="button" class="btn btn-primary" onClick="fcUserManage_regForm()">보류수정</button>
      <button type="button" class="btn btn-danger" onClick="fcUserManage_delete()">보류폐기</button>
      <button type="button" class="btn btn-primary" onClick="fcUserManage_regForm()">보류사유</button>
      
      &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
      
      <button type="button" class="btn btn-primary" onClick="fcUserManage_excelForm()">출력</button>
      <button type="button" class="btn btn-primary" onClick="fcUserManage_regForm()">발주</button>
      <br><br>
	  <table class="table table-bordered" >
	 	<tr>
          <th rowspan='9' class='text-center' style="background-color:#FCEABE">수신</th>
          <th class='text-center'  style="background-color:#FCEABE" >수신</th>
          <th class='text-center'><input type="text" class="form-control" id="companyName" name="companyName"  value="${targetVO.companyName}(${targetVO.deliveryCharge})" placeholder="수신" /></th>
          <th rowspan='9' class='text-center'  style="background-color:#FCEABE">발신</th>
          <th class='text-center' style="background-color:#FCEABE">발신</th>
          <th class='text-center'><input type="text" class="form-control" id="groupName" name="groupName"  value="ADDYS ${targetVO.groupName}" placeholder="발신"/></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#FCEABE" >참조</th>
          <th class='text-center'><input type="text" class="form-control" id="deleveryEtc" name=""deleveryEtc""  value="물류팀" placeholder="참조" /></th>
          <th class='text-center' style="background-color:#FCEABE" >참조</th>
          <th class='text-center'><input type="text" class="form-control" id="orderUserName" name="orderUserName"  value="${targetVO.orderUserName}" placeholder="참조" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#FCEABE">핸드폰</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryMobile" name="deliveryMobile"  value="${targetVO.deliveryMobile}"  placeholder="핸드폰"/></th>
          <th class='text-center' style="background-color:#FCEABE">핸드폰</th>
          <th class='text-center'><input type="text" class="form-control" id="mobilePhone" name="mobilePhone"  value="${targetVO.mobilePhone}"  placeholder="핸드폰"/></th>
      	</tr>
      	<tr>
          <th style="background-color:#FCEABE">e-mail</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryEmail" name="deliveryEmail"  value="${targetVO.deliveryEmail}" placeholder="e-mail" /></th>
          <th class='text-center' style="background-color:#FCEABE">e-mail</th>
          <th class='text-center'><input type="text" class="form-control" id="email" name="email"  value="${targetVO.email}" placeholder="e-mail" /></th>
      	</tr>
      	<tr>
          <th style="background-color:#FCEABE">tel</th>
          <th class='text-center'><input type="text" class="form-control" id="phone" name="phone"  value="${targetVO.phone}" placeholder="tel" /></th>
          <th class='text-center' style="background-color:#FCEABE">tel</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryTel" name="deliveryTel"  value="${targetVO.deliveryTel}" placeholder="tel" /></th>
      	</tr>
      	<th style="background-color:#FCEABE">fax</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryFax" name="deliveryFax"  value="${targetVO.deliveryFax}" placeholder="fax" /></th>
          <th class='text-center' style="background-color:#FCEABE">fax</th>
          <th class='text-center'><input type="text" class="form-control" id="faxNumber" name="faxNumber"  value="${targetVO.faxNumber}" placeholder="fax" /></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#FCEABE">발주일자</th>
          <th class='text-center'>
          		<!--
          		<div class='input-group date ' id='datetimepicker1' data-link-field="orderDateTime" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${targetVO.orderDateTime}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="orderDateTime" name="orderDateTime" value="${targetVO.orderDateTime}" />
	            </div>
	              -->
          </th>
          <th rowspan='2' class='text-center' style="background-color:#FCEABE">배송주소</th>
          <th rowspan='2' class='text-center'></th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#FCEABE">납품일자</th>
          <th class='text-center'>
          		<!--
          		<div class='input-group date ' id='datetimepicker2' data-link-field="deliveryDate" data-link-format="yyyy-mm-dd">
	                <input type='text' class="form-control" value="${targetVO.deliveryDate}" />
	                <span class="input-group-addon">
	                    <span class="glyphicon glyphicon-calendar"></span>
	                </span>
	                <input type="hidden" id="deliveryDate" name="deliveryDate" value="${targetVO.deliveryDate}" />
	            </div>
                -->
          </th>
      	</tr>
      	<tr>
          <th class='text-center' style="background-color:#FCEABE">납품방법</th>
          <th class='text-center'><input type="text" class="form-control" id="deliveryMethod" name="deliveryMethod"  value="택배배송" placeholder="납품방버" /></th>
          <th class='text-center' style="background-color:#FCEABE">결재방법</th>
          <th class='text-center'></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#FCEABE">SMS내용</th>
          <th colspan='4' class='text-center'><input type="text" class="form-control" id="sms" name="sms"  value="ADDYS에서 발주서를 보냈습니다.당일처리 부탁드립니다." placeholder="SMS" /></th>
      	</tr>
      	<tr>
          <th colspan='2' class='text-center' style="background-color:#FCEABE">메모</th>
          <th colspan='4' class='text-center'><input type="text" class="form-control" id="memo" name="memo"  value="" placeholder="메모" /></th>
      	</tr>
	  </table>
	  </form:form>
	 </div>
	 
     <form:form commandName="targetVO" name="targetDetailListForm" method="post" action="" >
      <p><span style="color:#FF9900">
        <span class="glyphicon glyphicon-asterisk"></span> 
                    합계 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="" />
                    공급가 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="" />
                    부가세 : <f:formatNumber type="currency" currencySymbol="" pattern="#,##0" value="" />
        </span>
      </p>       
	  <table class="table table-bordered" >
      	<tr style="background-color:#FCEABE">
          <th rowspan='2' class='text-center' >보류</th>
          <th rowspan='2' class='text-center'>품목코드</th>
          <th rowspan='2' class='text-center'>상품명</th>
          <th colspan='3' class='text-center'>발주</th>
          <th colspan='3' class='text-center'>재고</th>
          <th rowspan='2' class='text-center'>비고</th>
      	</tr>
      	<tr style="background-color:#FCEABE">
          <th class='text-center'>기준단가</th>
          <th class='text-center'>수량</th>
          <th class='text-center'>증감</th>
          <th class='text-center'>안전</th>
          <th class='text-center'>보유</th>
          <th class='text-center'>전산</th>
      	</tr>
	    	<c:if test="${!empty targetDetailList}">
             <c:forEach items="${targetDetailList}" var="targetVO" varStatus="status">
             <tr id="select_tr_${targetVO.productCode}">
                 <td><input type="checkbox" id="userCheck" name="userCheck" value="${targetVO.productCode}" title="선택" /></td>
                 <td class='text-center'><c:out value="${targetVO.productCode}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.productName}"></c:out></td>
                 <td class='text-center'><c:out value=""></c:out></td>
                 <td class='text-center'><c:out value=""></c:out></td>
                 <td class='text-center'><c:out value=""></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.safeStock}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.holdStock}"></c:out></td>
                 <td class='text-center'><c:out value="${targetVO.stockCnt}"></c:out></td>
                 <td class='text-center'><c:out value=""></c:out></td>
              </tr>
             </c:forEach>
            </c:if>
           <c:if test="${empty targetDetailList}">
           <tr>
           	<td colspan='10' class='text-center'>조회된 데이터가 없습니다.</td>
           </tr>
          </c:if>
	  </table>
	 </form:form>
	</div>