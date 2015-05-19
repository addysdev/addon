<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<script>
window.print();
</script>
		        <html>
		        <head>
		        <title>상품주문서</title>
		        <meta http-equiv='Content-Type' content='text/html; charset=euc-kr' />
		        <style type='text/css'> 
		        <!--
		        td {
		        font-family: '굴림', '돋움', 'Seoul', '한강체';
		        font-size: 12px;
		        	line-height: 30px;
		        }
				.style1 {
			    	font-size: 30px;
					font-weight: bold;
					font-family: '굴림체', '돋움체', Seoul;
		        }
				.style5 {
					font-size: 24px;
				font-weight: bold;
		        }
				-->
				</style>
				</head>

				<body>
				<div align='center'></div>

				<div align='left'>
				<table width='612' border='0' align='center' cellpadding='0' cellspacing='0'>
				<tr> 
				<td width='516' valign='top'>
				<table width='722' height='900' border='0' align='center' cellpadding='1' cellspacing='1' bgcolor='#000000'>
				<tr bgcolor='#FFFFFF'> 
				<td height='55' colspan='12' align='center'><span class='style1'>상 품 주 문 서</span></td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				 <td rowspan='7' align='center' style='background-color:#E4E4E4'>수<br>신</td>
				 <td align='center'>&nbsp;수 신</td>
				 <td colspan='5' align='center'>&nbsp;${orderVO.deliveryCharge}</td>
				 <td rowspan='7'  align='center' style='background-color:#E4E4E4'>발<br>신</td>
				 <td align='center'>&nbsp;발 신</td>
				 <td colspan='3' align='center'>&nbsp;${orderVO.orderCharge}</td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				<td align='center'>&nbsp;참 조</td>
				<td colspan='5' align='center'>&nbsp;${orderVO.deliveryEtc}</td>
				<td align='center'>&nbsp;참 조</td>
				<td colspan='3' align='center'>&nbsp;${orderVO.orderEtc}</td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				<td rowspan='2' align='center' >연락처</td>
				<td colspan='5' align='left'>&nbsp;핸드폰 :${orderVO.mobilePhone},E-Mail :${orderVO.email}</td>
				<td rowspan='2' align='center' >연락처</td>
				<td colspan='3' align='left'>&nbsp;핸드폰 :${orderVO.orderMobilePhone},E-Mail :${orderVO.orderEmail}</td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				<td colspan='5' align='left'>&nbsp;TEL :${orderVO.telNumber},FAX :${orderVO.faxNumber}</td>
				<td colspan='5' align='left'>&nbsp;TEL :${orderVO.orderTelNumber},FAX :${orderVO.orderFaxNumber}</td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				<td align='center' >발주일자</td>
				<td width='77' align='center'><div align='right'>${orderDates1}년 </div></td>
				<td width='61' align='center'>&nbsp;${orderDates2}</td>
				<td width='50' align='center'>월</td>
				<td width='58' align='center'>&nbsp;${orderDates3}</td>
				<td width='52' align='center'>일</td>
				<td rowspan='2' align='center' >배송주소</td>
				<td rowspan='2' colspan='3'width='58' align='left'>&nbsp${orderVO.orderAddress};</td>
				</tr>
                <tr bgcolor='#FFFFFF'>
				<td align='center' >납품일자</td>
				<td width='77' align='center'><div align='right'>${deliveryDates1}년 </div></td>
				<td width='61' align='center'>&nbsp;${deliveryDates2}</td>
				<td width='50' align='center'>월</td>
				<td width='58' align='center'>&nbsp;${deliveryDates3}</td>
				<td width='52' align='center'>일</td>
				</tr>
                <tr bgcolor='#FFFFFF'>
				<td align='center'>&nbsp;납품방법</td>
				<td colspan='5' align='center'>&nbsp;${orderVO.deliveryMethod}</td>
				<td align='center'>&nbsp;결재방법</td>
				<td colspan='3' align='center'>&nbsp;${orderVO.payMethod}</td>
				</tr>

				<tr bgcolor='#FFFFFF'>
				<td colspan="2" align='center' >메모</td>
				<td colspan='10' align='center'>${orderVO.memo}</td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				<td colspan='12' align='center' height='27'><div align='left'>1.아래와 같이 발주합니다.</div></td>
				</tr>
				
                <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>	
				
				           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>           <tr bgcolor='#FFFFFF'>
				<td colspan='2' align='center' height='27'></td>
				<td align='center'></td>
				<td colspan='7' align='center'></td>
				<td width='57' align='center'></td>
				<td width='172' align='center'></td>
				</tr>
				
	
				
				
				</table>
				</div>

				</body>
				 </html>

				</html>