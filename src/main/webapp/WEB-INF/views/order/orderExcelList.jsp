<%@ include file="/WEB-INF/views/addys/base.jsp" %>
<%
	String fileName = "orderExcelList.xls";
	response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	response.setHeader("Content-Description", "JSP Generated Data");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style>
.xl65
	{mso-style-parent:style0;
	mso-number-format:"\@";}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>구매전표 리스트</title>
</head>

<body>
<table width="820" cellspacing="0" cellpadding="0" border="1" style="margin:30 0 0 0;">
	<tr>
		<td width="100%">
		<table width="100%" cellpadding=0 cellspacing=0 border="1" style="border-collapse:collapse;">
			<tr height="25" bgcolor="#F8F9FA" >
				<td align="center"><strong>일자(8)</strong></td>
				<td align="center"><strong>구분(2)</strong></td>
				<td align="center"><strong>순번(4)</strong></td>
				<td align="center"><strong>거래처코드(30)</strong></td>
				<td align="center"><strong>거래처명(50)</strong></td>
				<td align="center"><strong>프로젝트코드(14)</strong></td>
				<td align="center"><strong>창고코드(5)</strong></td>
				<td align="center"><strong>담당자코드(30)</strong></td>
				<td align="center"><strong>전표일자(200)</strong></td>
				<td align="center"><strong>메모(200)</strong></td>
				<td align="center"><strong>품목코드(20)</strong></td>
				<td align="center"><strong>품목명(100)</strong></td>
				<td align="center"><strong>구격명(50)</strong></td>
				<td align="center"><strong>수량(12)</strong></td>
				<td align="center"><strong>단가(12)</strong></td>
				<td align="center"><strong>공급가액(14)</strong></td>
				<td align="center"><strong>부가세(14)</strong></td>
				<td align="center"><strong>적요(200)</strong></td>
				<td align="center"><strong>부대비용(12)</strong></td>
				<td align="center"><strong>ecount</strong></td>
			</tr>
		<!-- :: loop :: -->
		<!--리스트---------------->
		<c:if test="${!empty orderExcelList}">
	          <c:forEach items="${orderExcelList}" var="orderVO" varStatus="status">
	             <tr bgcolor="#FFFFFF" height="23">
				    <td align="center">&nbsp;20150521</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;${orderVO.companyCode}</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;${orderVO.groupId}</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;${orderVO.etc}</td>
					<td align="center">&nbsp;${orderVO.productCode}</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;${orderVO.orderResultCnt}</td>
					<td align="center">&nbsp;${orderVO.orderResultPrice}</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;</td>
					<td align="center">&nbsp;ecount</td>
				</tr>
             </c:forEach>
            </c:if>
		</table>
		</td>
	</tr>
</table>									
</body>
</html>
