<%@ page import="java.util.*,
				java.io.*,
				java.util.List,
				java.util.HashMap,
				com.offact.addys.vo.order.TargetVO" %>
<%

List<TargetVO> targetLsit = (List)request.getAttribute("targetExcelList");
TargetVO targetVO = (TargetVO)request.getAttribute("targetVO");


%>
<script>
window.print();
</script>
                <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		        <html>
		        <head>
		        <title>(주)애디스다이렉트</title>
		        <meta http-equiv="X-UA-Compatible" content="IE=edge">
		        <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
		        <style type='text/css'> 
		        <!--
				table {
				    border-collapse: collapse;
				}

				table, th, td {
				    border: 1px solid black;
				}
				.style1 {
			    	font-size: 30px;
					font-weight: bold;
					font-family: '돋움체', '굴림체', Seoul;
		        }
				body {color : #000000; background : #FFFFFF; font-family : 굴림,"Times New Roman"; font-size : 12pt;}
				
				@page{  size:auto; margin : 10mm;  }
				
				h1 {page-break-before: always;}

				-->
				</style>
				
				</head>

				<body>
				<div align='center'>
				<%
				
		    	String [] getToMails=targetVO.getEmail().split(";");
				
				int num=0;
				int totalnum=targetLsit.size();
				int etcnum=0;
				int maxlist=20;
				int resultlist=totalnum;
				int removecnt=0;
				int numcnt=0;
				
				String[] r_data=null;
				
				int pagenum = Math.floorDiv(totalnum, maxlist);
				
				int pageCal = totalnum%maxlist;
				
				if(pageCal==0){
					pagenum=pagenum-1;
				}
				
				for(int x=0; x<=pagenum; x++){
					if(x!=0){
				 %>		
					<h1></h1>
				<%
					}
				%>
				<table width='722' height='900' align='center' >
				<tr > 
				<td height='55' colspan='12' align='center'><span class='style1'>상 품 주 문 서</span></td>
				</tr>
				<tr >
				 <td width='30' rowspan='8' align='center' style='background-color:#E4E4E4'>수<br>신</td>
				 <td width='90' align='center' >&nbsp;회사명</td>
				 <td colspan='5' align='center'>&nbsp;${targetVO.deliveryName}</td>
				 <td width='30' rowspan='8' align='center' style='background-color:#E4E4E4'>발<br>신</td>
				 <td width='90' align='center'>&nbsp;회사명</td>
				 <td colspan='3' align='center'>&nbsp;${targetVO.orderName}</td>
				</tr>
				<tr >
				<td rowspan='4' align='center' >담당자</td>
				<td colspan='5' align='left'>&nbsp;성명:${targetVO.deliveryCharge}</td>
				<td rowspan='4' align='center' >담당자</td>
				<td colspan='3' align='left'>&nbsp;성명:${targetVO.orderCharge}</td>
				</tr>
				<tr >
				<td colspan='5' align='left'>&nbsp;핸드폰:${targetVO.mobilePhone}</td>
				<td colspan='3' align='left'>&nbsp;핸드폰:${targetVO.orderMobilePhone}</td>
				</tr>
				<tr >
				<td colspan='5' align='left'>&nbsp;TEL:${targetVO.telNumber}&nbsp;/&nbsp;FAX:${targetVO.faxNumber}</td>
				<td colspan='3' align='left'>&nbsp;TEL:${targetVO.orderTelNumber}&nbsp;/&nbsp;FAX:${targetVO.orderFaxNumber}</td>
				</tr>
				<tr >
				<td colspan='5' align='left'>&nbsp;email :<%=getToMails[0]%></td>
				<td colspan='3' align='left'>&nbsp;email :${targetVO.orderEmail}</td>
				</tr>
				<tr >
				<td align='center' >발주일자</td>
				<td width='35' align='center'><div align='right'>${orderDates1}년 </div></td>
				<td width='25' align='center'>&nbsp;${orderDates2}</td>
				<td width='25' align='center'>월</td>
				<td width='25' align='center'>&nbsp;${orderDates3}</td>
				<td width='25' align='center'>일</td>
				<td rowspan='2' align='center' >배송주소</td>
				<td rowspan='2' colspan='3' align='left'>&nbsp;${targetVO.orderAddress}</td>
				</tr>
                <tr >
				<td align='center' >납품일자</td>
				<td align='center'><div align='right'>${deliveryDates1}년 </div></td>
				<td align='center'>&nbsp;${deliveryDates2}</td>
				<td align='center'>월</td>
				<td align='center'>&nbsp;${deliveryDates3}</td>
				<td align='center'>일</td>
				</tr>
                <tr >
				<td align='center'>&nbsp;납품방법</td>
				<td colspan='5' align='center'>&nbsp;${targetVO.deliveryMethod}</td>
				<td align='center'>&nbsp;결제방법</td>
				<td colspan='3' align='center'>&nbsp;${targetVO.payMethod}</td>
				</tr>

				<tr >
				<td colspan="2" align='center' >메모</td>
				<td colspan='10' align='left' >${targetVO.memo}</td>
				</tr>
				<tr >
				<td colspan='12' align='center' height='27'><div align='left'>&nbsp;1.아래와 같이 발주합니다.</div></td>
				</tr>
				<tr >
					<td width='40' align='center' height='27'>번 호</td>
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
						
						numcnt++;
						
					%>
						<tr >
						<td align='center' height='27'><%=numcnt %></td>
						<td colspan='9' align='left'>&nbsp;<%=targetDetaiList.getProductName() %></td>
						<td align='center'><%=targetDetaiList.getOrderCnt() %></td>
						<td align='left'><%=targetDetaiList.getEtc() %></td>
						</tr>	
					<%
					}
					
					for(int y=0;y<etcnum;y++){
					%>		
						<tr >
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
						
						numcnt++;
					%>
						<tr >
						<td align='center' height='27'><%=numcnt %></td>
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
			<%
			}
			%>
			</div>
		</body>
	</html>
