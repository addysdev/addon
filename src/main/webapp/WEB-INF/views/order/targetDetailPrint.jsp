<%@ page import="java.util.*,
				java.io.*,
				java.util.List,
				java.util.HashMap,
				com.offact.addys.vo.order.TargetVO" %>
<%

List<TargetVO> targetLsit = (List)request.getAttribute("targetExcelList");

%>
<script>
window.print();
</script>
		        <html>
		        <head>
		        <title>상품주문서</title>
		        <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
		        <style type='text/css'> 
		        <!--
		        td {
		        font-family: '돋움', '굴림', 'Seoul';
		        font-size: 11px;
		        	line-height: 30px;
		        }
				.style1 {
			    	font-size: 30px;
					font-weight: bold;
					font-family: '돋움체', '굴림체', Seoul;
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
				<%
							
				int num=0;
				int totalnum=targetLsit.size();
				int etcnum=0;
				int maxlist=23;
				int resultlist=totalnum;
				
				String[] r_data=null;
				
				int pagenum = Math.floorDiv(totalnum, maxlist);
				
				for(int x=0; x<=pagenum; x++){
				
				 %>
				<table width='722' height='900' border='0' align='center' cellpadding='1' cellspacing='1' bgcolor='#000000'>
				<tr bgcolor='#FFFFFF'> 
				<td height='55' colspan='12' align='center'><span class='style1'>상 품 주 문 서</span></td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				 <td width='30' rowspan='8' align='center' style='background-color:#E4E4E4'>수<br>신</td>
				 <td width='60' align='center' >&nbsp;회사명</td>
				 <td colspan='5' align='center'>&nbsp;${targetVO.deliveryName}</td>
				 <td width='30' rowspan='8' align='center' style='background-color:#E4E4E4'>발<br>신</td>
				 <td width='60' align='center'>&nbsp;회사명</td>
				 <td colspan='3' align='center'>&nbsp;${targetVO.orderName}</td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				<td rowspan='4' align='center' >담당자</td>
				<td colspan='5' align='left'>&nbsp;성명:${targetVO.deliveryCharge}</td>
				<td rowspan='4' align='center' >담당자</td>
				<td colspan='3' align='left'>&nbsp;성명:${targetVO.orderCharge}</td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				<td colspan='5' align='left'>&nbsp;핸드폰:${targetVO.mobilePhone}</td>
				<td colspan='3' align='left'>&nbsp;핸드폰:${targetVO.orderMobilePhone}</td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				<td colspan='5' align='left'>&nbsp;TEL:${targetVO.telNumber}&nbsp;/&nbsp;FAX:${targetVO.faxNumber}</td>
				<td colspan='3' align='left'>&nbsp;TEL:${targetVO.orderTelNumber}&nbsp;/&nbsp;FAX:${targetVO.orderFaxNumber}</td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				<td colspan='5' align='left'>&nbsp;email :${targetVO.email}</td>
				<td colspan='3' align='left'>&nbsp;email :${targetVO.orderEmail}</td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				<td align='center' >발주일자</td>
				<td width='50' align='center'><div align='right'>${orderDates1}년 </div></td>
				<td width='30' align='center'>&nbsp;${orderDates2}</td>
				<td width='30' align='center'>월</td>
				<td width='30' align='center'>&nbsp;${orderDates3}</td>
				<td width='30' align='center'>일</td>
				<td rowspan='2' align='center' >배송주소</td>
				<td rowspan='2' colspan='3' align='left'>&nbsp${targetVO.orderAddress}</td>
				</tr>
                <tr bgcolor='#FFFFFF'>
				<td align='center' >납품일자</td>
				<td align='center'><div align='right'>${deliveryDates1}년 </div></td>
				<td align='center'>&nbsp;${deliveryDates2}</td>
				<td align='center'>월</td>
				<td align='center'>&nbsp;${deliveryDates3}</td>
				<td align='center'>일</td>
				</tr>
                <tr bgcolor='#FFFFFF'>
				<td align='center'>&nbsp;납품방법</td>
				<td colspan='5' align='center'>&nbsp;${targetVO.deliveryMethod}</td>
				<td align='center'>&nbsp;결제방법</td>
				<td colspan='3' align='center'>&nbsp;${targetVO.payMethod}</td>
				</tr>

				<tr bgcolor='#FFFFFF'>
				<td colspan="2" align='center' >메모</td>
				<td colspan='10' align='left' >${targetVO.memo}</td>
				</tr>
				<tr bgcolor='#FFFFFF'>
				<td colspan='12' align='center' height='27'><div align='left'>&nbsp;1.아래와 같이 발주합니다.</div></td>
				</tr>
				<tr bgcolor='#FFFFFF'>
					<td width='30' align='center' height='27'>번 호</td>
					<td colspan='9' align='center'>상 품 명</td>
					<td width='40' align='center'>수량</td>
					<td width='180' align='center'>비 고</td>
				</tr>	
				<%
				

				
				if(resultlist<=maxlist){
					
					etcnum=maxlist-resultlist;
					
					for(int i=0;i<resultlist;i++){
						num++;
						TargetVO targetDetaiList = new TargetVO();
						targetDetaiList=targetLsit.get(num-1);
		
					%>
						<tr bgcolor='#FFFFFF'>
						<td align='center' height='27'><%=num %></td>
						<td colspan='9' align='left'>&nbsp;<%=targetDetaiList.getProductName() %></td>
						<td align='center'><%=targetDetaiList.getOrderCnt() %></td>
						<td align='left'><%=targetDetaiList.getEtc() %></td>
						</tr>	
					<%
					}
					
					for(int y=0;y<etcnum;y++){
					%>		
						<tr bgcolor='#FFFFFF'>
						<td align='center' height='27'></td>
						<td colspan='9' align='center'></td>
						<td align='center'></td>
						<td align='center'></td>
						</tr>	
					<% 	
					}
					
				}else if(resultlist>maxlist){
					
					for(int z=0;z<maxlist;z++){
						num++;
						TargetVO targetDetaiList = new TargetVO();
						targetDetaiList=targetLsit.get(num-1);
		
					%>
						<tr bgcolor='#FFFFFF'>
						<td align='center' height='27'><%=num %></td>
						<td colspan='9' align='left'>&nbsp;<%=targetDetaiList.getProductName() %></td>
						<td align='center'><%=targetDetaiList.getOrderCnt() %></td>
						<td align='left'><%=targetDetaiList.getEtc() %></td>
						</tr>	
				    <%
					}
					
					resultlist=resultlist-maxlist;
					
				}
				
				%>
				</table>
				<br><br>
			<%
			}
			%>
				</div>

			</body>
		</html>
