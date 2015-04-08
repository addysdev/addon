<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.OutputStream"%>
<%@page import="org.apache.poi.hssf.usermodel.*"%>
<%@page import="org.apache.poi.hssf.util.*;"%>

<%
	String strTitle = "회원 일괄등록";	
	strTitle = new String(strTitle.getBytes("euc-kr"), "8859_1");//한글깨짐현상 처리
	
	response.setContentType("application/octet-stream");
	//response.setHeader("Content-Disposition", "attachment; filename="+strTitle+".xls;");
	response.setHeader("Content-Disposition", "attachment; filename=memberList.xls;");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<%

try {
	 /*--------------------------- 엑셀 만들기 -------------------------*/	 
	 
	  int i = 0;	 // 로우수

	  HSSFWorkbook workbook = new HSSFWorkbook();	  
	 //Sheet를 만듭니다.
	  HSSFSheet sheet = workbook.createSheet("회원");
	 
	  //셀 넓이
	  //sheet.setColumnWidth((short) 0, (short) 3000);
	  //sheet.setColumnWidth((short) 1, (short) 3000);
	  for(int no=0; no < 26; no++){
		  sheet.setColumnWidth((short) no, (short) 3000);  
	  }

	  HSSFCellStyle title = workbook.createCellStyle(); 	// 상단메뉴 스타일
	  HSSFCellStyle label = workbook.createCellStyle(); 	// 셀 스타일	
	  HSSFCellStyle notNull = workbook.createCellStyle(); 	// notNull 셀 스타일
	  
	  //폰트
	  HSSFFont font = workbook.createFont();
	  font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	  font.setFontHeight((short)250);
	  title.setFont(font);
	  
	  //cell Style
	  label.setAlignment (label.ALIGN_CENTER);
	  notNull.setAlignment (label.ALIGN_CENTER);
	  notNull.setFillForegroundColor(HSSFColor.YELLOW.index);
	  notNull.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	  
	  //테두리 선긋기	  
	  label.setBorderBottom(label.BORDER_THIN); 
	  label.setBorderLeft(label.BORDER_THIN);
	  label.setBorderRight(label.BORDER_THIN);
	  label.setBorderTop(label.BORDER_THIN);
	  
	  notNull.setBorderBottom(notNull.BORDER_THIN);
	  notNull.setBorderLeft(notNull.BORDER_THIN);
	  notNull.setBorderRight(notNull.BORDER_THIN);
	  notNull.setBorderTop(notNull.BORDER_THIN);
	  
	 
	  /****** 타이틀 ******/	  
	  //sheet에 행을 하나 만듭니다.
	  HSSFRow row = sheet.createRow((short)i);		  
	  // 셀생성
	  HSSFCell cell = null; 
	  
	  //row를 미리 생성하여 테두리 선 스타일 시트 처리
	  for(int j=0; j<26; j++){
		  cell = row.createCell((short)j);	
		  cell.setCellStyle (title);	  
	  }

	  /****** 헤더 ******/
	  cell = row.getCell((short)0);   //0셀 불러오기
	  cell.setCellValue("노란색 항목은 필수로 입력해주세요. ");	  
	  sheet.addMergedRegion(new Region(0,(short)0,0,(short)25));

	  
	  /****** 타이틀 ******/
	  i++;
	  row = sheet.createRow((short)i);		
	  // 셀에 데이터 추가
	  cell = row.createCell((short)0);	 
	  cell.setCellValue("회원명");	  
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING);
	  //cell.setEncoding(cell.ENCODING_UTF_16);
	  //cell.ENCODING_UTF_16;
	  
	  //HSSFRow objRow = objSheet.createRow((short)0);
	  //objCell.setEncoding(HSSFCell.ENCODING_UTF_16);
	  //objCell.setCellValue("연도");
	  //cell.ENCODING_UTF_16
	  
	  cell = row.createCell((short)1);	 
	  cell.setCellValue("주민등록번호");	
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)2);
	  cell.setCellValue("회원아이디");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)3);
	  cell.setCellValue("비밀번호");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)4);
	  cell.setCellValue("이동전화번호");
	  cell.setCellStyle (label);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)5);
	  cell.setCellValue("전화번호");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)6);
	  cell.setCellValue("Email주소");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)7);
	  cell.setCellValue("성별(M/F)");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)8);
	  cell.setCellValue("우편번호");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)9);
	  cell.setCellValue("주소");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)10);
	  cell.setCellValue("세부주소");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)11);
	  cell.setCellValue(" 이동통신사구분");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)12);
	  cell.setCellValue("직장명");
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell.setCellStyle (label);
	  cell = row.createCell((short)13);
	  cell.setCellValue("부서");
	  cell.setCellStyle (label);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)14);
	  cell.setCellValue("직위");
	  cell.setCellStyle (label);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)15);
	  cell.setCellValue("직장전화");
	  cell.setCellStyle (label);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)16);
	  cell.setCellValue("직장주소");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)17);
	  cell.setCellValue("직장세부주소");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)18);
	  cell.setCellValue("직장우편번호");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)19);
	  cell.setCellValue("기금적립코드");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  
	  
/*	  cell = row.createCell((short)21);
	  cell.setCellValue("결제은행");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)22);
	  cell.setCellValue("계좌번호");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)23);
	  cell.setCellValue("명세서수령방법");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)24);
	  cell.setCellValue("카드 결제일");
	  cell.setCellStyle (notNull);
	  cell.setCellType (cell.CELL_TYPE_STRING); 
	  cell = row.createCell((short)25);
	  cell.setCellValue("카드수령지");
	  cell.setCellStyle (notNull);	  
	  cell.setCellType (cell.CELL_TYPE_STRING); 
*/	  
	  out.clear();
      out = pageContext.pushBody();
      OutputStream xlsOut = response.getOutputStream(); //OutputStream으로 엑셀을 저장한다.
      
      workbook.write(xlsOut);
	 
	  
}catch(Exception e) {
	 out.println("make File Fail!!" + e);
}

%>

</body>
</html>
