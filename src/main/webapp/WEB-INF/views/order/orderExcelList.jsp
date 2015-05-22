<%@ include file="/WEB-INF/views/addys/base.jsp" %>
  <%@ page import="java.util.*,
				java.io.*,
				java.util.List,
				java.util.HashMap,
				org.apache.poi.xssf.usermodel.XSSFCellStyle,
				org.apache.poi.xssf.usermodel.XSSFFont,
				org.apache.poi.xssf.usermodel.XSSFCell,
				org.apache.poi.xssf.usermodel.XSSFRow,
				org.apache.poi.xssf.usermodel.XSSFSheet,
				org.apache.poi.xssf.usermodel.XSSFWorkbook,
				org.apache.poi.xssf.usermodel.XSSFColor,
				org.apache.poi.ss.util.CellRangeAddress,
				org.apache.poi.ss.usermodel.Font,
				org.apache.poi.ss.usermodel.IndexedColors,
				com.offact.addys.vo.order.OrderVO" %>
<%
	String fileName = "orderExcelList.xlsx";

    String strClient = request.getHeader("User-Agent");
	List<OrderVO> orderLsit = (List)request.getAttribute("orderExcelList");
    
    if (strClient.indexOf("MSIE 5.5") > -1) {
    	//response.setContentType("application/vnd.ms-excel");
    	response.setHeader("Content-Disposition", "filename=" + fileName + ";");
    } else {
    	response.setContentType("application/vnd.ms-excel");
    	response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ";");
    }

    //response.setContentType("application/vnd.ms-excel");
	//response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
	//response.setHeader("Content-Description", "JSP Generated Data");
	
	OutputStream fileOut = null;

	//워크북 생성
	XSSFWorkbook objWorkBook = new XSSFWorkbook();
	//워크시트 생성
	XSSFSheet objSheet = objWorkBook.createSheet();
	//시트 이름
	objWorkBook.setSheetName(0 , "구매" );
	//행생성
	XSSFRow objRow = objSheet.createRow((short)0);
	//셀 생성
	XSSFCell objCell = null;
	//------------------------

	objRow = objSheet.createRow((short)0);
	
	objCell = objRow.createCell((short)0);
	objCell.setCellValue("일자(8)");
	objCell = objRow.createCell((short)1);
	objCell.setCellValue("구분(2)");
	objCell = objRow.createCell((short)2);
	objCell.setCellValue("순번(4)");
	objCell = objRow.createCell((short)3);
	objCell.setCellValue("거래처코드(30)");
	objCell = objRow.createCell((short)4);
	objCell.setCellValue("거래처명(50)");
	objCell = objRow.createCell((short)5);
	objCell.setCellValue("프로젝트코드(14)");
	objCell = objRow.createCell((short)6);
	objCell.setCellValue("창고코드(5)");
	objCell = objRow.createCell((short)7);
	objCell.setCellValue("담당자코드(30)");
	objCell = objRow.createCell((short)8);
	objCell.setCellValue("전표일자(200)");
	objCell = objRow.createCell((short)9);
	objCell.setCellValue("메모(200)");
	objCell = objRow.createCell((short)10);
	objCell.setCellValue("품목코드(20)");
	objCell = objRow.createCell((short)11);
	objCell.setCellValue("품목명(100)");
	objCell = objRow.createCell((short)12);
	objCell.setCellValue("구격명(50)");
	objCell = objRow.createCell((short)13);
	objCell.setCellValue("수량(12)");
	objCell = objRow.createCell((short)14);
	objCell.setCellValue("단가(12)");
	objCell = objRow.createCell((short)15);
	objCell.setCellValue("공급가액(14)");
	objCell = objRow.createCell((short)16);
	objCell.setCellValue("부가세(14)");
	objCell = objRow.createCell((short)17);
	objCell.setCellValue("적요(200)");
	objCell = objRow.createCell((short)18);
	objCell.setCellValue("부대비용(12)");
	objCell = objRow.createCell((short)19);
	objCell.setCellValue("ecount");
	

	//----------------------------------------------------------------------------------------
	//길이 설정
	objSheet.setColumnWidth((short)0,(short)3000);
	objSheet.setColumnWidth((short)1,(short)3000);
	objSheet.setColumnWidth((short)2,(short)3000);
	objSheet.setColumnWidth((short)3,(short)3500);
	objSheet.setColumnWidth((short)4,(short)3000);
	objSheet.setColumnWidth((short)5,(short)4000);
	objSheet.setColumnWidth((short)6,(short)3000);
	objSheet.setColumnWidth((short)7,(short)3500);
	objSheet.setColumnWidth((short)8,(short)3500);
	objSheet.setColumnWidth((short)9,(short)3000);
	objSheet.setColumnWidth((short)10,(short)3000);
	objSheet.setColumnWidth((short)11,(short)3000);
	objSheet.setColumnWidth((short)12,(short)3000);
	objSheet.setColumnWidth((short)13,(short)3000);
	objSheet.setColumnWidth((short)14,(short)3000);
	objSheet.setColumnWidth((short)15,(short)3000);
	objSheet.setColumnWidth((short)16,(short)3000);
	objSheet.setColumnWidth((short)17,(short)3000);
	objSheet.setColumnWidth((short)18,(short)3000);
	objSheet.setColumnWidth((short)19,(short)3000);
	
	//--------------------------------------------------------------------------------------
	for (int i=0; i<orderLsit.size() ; i++) {
		
		OrderVO orderVO =new OrderVO();
		orderVO=orderLsit.get(i);

		objRow = objSheet.createRow((short)i+1);
		
		objCell = objRow.createCell((short)0);
		objCell.setCellValue("20150522");
		objCell = objRow.createCell((short)1);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)2);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)3);
		objCell.setCellValue(orderVO.getCompanyCode());
		objCell = objRow.createCell((short)4);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)5);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)6);
		objCell.setCellValue(orderVO.getGroupId());
		objCell = objRow.createCell((short)7);
		objCell.setCellValue(orderVO.getBuyUserName());
		objCell = objRow.createCell((short)8);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)9);
		objCell.setCellValue(orderVO.getEtc());
		objCell = objRow.createCell((short)10);
		objCell.setCellValue(orderVO.getProductCode());
		objCell = objRow.createCell((short)11);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)12);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)13);
		objCell.setCellValue(orderVO.getOrderResultCnt());
		objCell = objRow.createCell((short)14);
		objCell.setCellValue(orderVO.getOrderResultPrice());
		objCell = objRow.createCell((short)15);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)16);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)17);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)18);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)19);
		objCell.setCellValue("ecount");
	}

	
	out.clear(); 

	out=pageContext.pushBody();
	
	
	fileOut = response.getOutputStream(); 
	objWorkBook.write(fileOut);
	fileOut.close();

%>
