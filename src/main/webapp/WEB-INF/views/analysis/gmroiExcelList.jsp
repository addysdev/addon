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
				com.offact.addys.vo.analysis.GmroiVO" %>
<%
	String fileName = "gmroiExcelList.xlsx";

    String strClient = request.getHeader("User-Agent");
	List<GmroiVO> gmroiExcelList = (List)request.getAttribute("gmroiExcelList");
    
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
	objWorkBook.setSheetName(0 , "GMROI분석" );
	//행생성
	XSSFRow objRow = objSheet.createRow((short)0);
	//셀 생성
	XSSFCell objCell = null;
	//------------------------

	objRow = objSheet.createRow((short)0);
	
	objCell = objRow.createCell((short)0);
	objCell.setCellValue("품목코드");
	objCell = objRow.createCell((short)1);
	objCell.setCellValue("품목명");
	objCell = objRow.createCell((short)2);
	objCell.setCellValue("평균재고수량");
	objCell = objRow.createCell((short)3);
	objCell.setCellValue("평균재고금액");
	objCell = objRow.createCell((short)4);
	objCell.setCellValue("총매출수량");
	objCell = objRow.createCell((short)5);
	objCell.setCellValue("총매출금액");
	objCell = objRow.createCell((short)6);
	objCell.setCellValue("총이익금액)");
	objCell = objRow.createCell((short)7);
	objCell.setCellValue("총이익율(%)");
	objCell = objRow.createCell((short)8);
	objCell.setCellValue("재고금액회전율(회전)");
	objCell = objRow.createCell((short)9);
	objCell.setCellValue("GMROI(%)");
	
	//----------------------------------------------------------------------------------------
	//길이 설정
	objSheet.setColumnWidth((short)0,(short)3000);
	objSheet.setColumnWidth((short)1,(short)15000);
	objSheet.setColumnWidth((short)2,(short)3500);
	objSheet.setColumnWidth((short)3,(short)3500);
	objSheet.setColumnWidth((short)4,(short)3500);
	objSheet.setColumnWidth((short)5,(short)3500);
	objSheet.setColumnWidth((short)6,(short)3500);
	objSheet.setColumnWidth((short)7,(short)3500);
	objSheet.setColumnWidth((short)8,(short)3500);
	objSheet.setColumnWidth((short)9,(short)3500);
	
	//--------------------------------------------------------------------------------------
	for (int i=0; i<gmroiExcelList.size() ; i++) {
		
		GmroiVO gmroiVO =new GmroiVO();
		gmroiVO=gmroiExcelList.get(i);

		objRow = objSheet.createRow((short)i+1);
		
		objCell = objRow.createCell((short)0);
		objCell.setCellValue(gmroiVO.getProductCode());
		objCell = objRow.createCell((short)1);
		objCell.setCellValue(gmroiVO.getProductName());
		objCell = objRow.createCell((short)2);
		objCell.setCellValue(gmroiVO.getAvgStockCnt());
		objCell = objRow.createCell((short)3);
		objCell.setCellValue(gmroiVO.getAvgStockAmt());
		objCell = objRow.createCell((short)4);
		objCell.setCellValue(gmroiVO.getTotalSaleCnt());
		objCell = objRow.createCell((short)5);
		objCell.setCellValue(gmroiVO.getTotalSaleAmt());
		objCell = objRow.createCell((short)6);
		objCell.setCellValue(gmroiVO.getProfitSaleAmt());
		objCell = objRow.createCell((short)7);
		objCell.setCellValue(gmroiVO.getAvgSaleRate());
		objCell = objRow.createCell((short)8);
		objCell.setCellValue(gmroiVO.getStockCycleRate());
		objCell = objRow.createCell((short)9);
		objCell.setCellValue(gmroiVO.getGmroiRate());
	}

	
	out.clear(); 

	out=pageContext.pushBody();
	
	
	fileOut = response.getOutputStream(); 
	objWorkBook.write(fileOut);
	fileOut.close();

%>
