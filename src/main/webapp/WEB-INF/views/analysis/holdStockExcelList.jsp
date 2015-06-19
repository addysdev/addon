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
				com.offact.addys.vo.analysis.HoldStockVO" %>
<%
	String fileName = "holdStockExcelList.xlsx";

    String strClient = request.getHeader("User-Agent");
	List<HoldStockVO> holdStockLsit = (List)request.getAttribute("holdStockExcelList");
    
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
	objWorkBook.setSheetName(0 , "보유재고 분석(추천)" );
	//행생성
	XSSFRow objRow = objSheet.createRow((short)0);
	//셀 생성
	XSSFCell objCell = null;
	//------------------------

	objRow = objSheet.createRow((short)0);
	
	objCell = objRow.createCell((short)0);
	objCell.setCellValue("지점");
	objCell = objRow.createCell((short)1);
	objCell.setCellValue("품목코드");
	objCell = objRow.createCell((short)2);
	objCell.setCellValue("품목명");
	objCell = objRow.createCell((short)3);
	objCell.setCellValue("적용(보유)일수");
	objCell = objRow.createCell((short)4);
	objCell.setCellValue("보유재고");
	objCell = objRow.createCell((short)5);
	objCell.setCellValue("보유재고업데이트일시");
	objCell = objRow.createCell((short)6);
	objCell.setCellValue("평균매출수량)");
	objCell = objRow.createCell((short)7);
	objCell.setCellValue("추천적용(보유)일수");
	objCell = objRow.createCell((short)8);
	objCell.setCellValue("추천보유재고");
	objCell = objRow.createCell((short)9);
	objCell.setCellValue("증감율");
	
	//----------------------------------------------------------------------------------------
	//길이 설정
	objSheet.setColumnWidth((short)0,(short)5000);
	objSheet.setColumnWidth((short)1,(short)3000);
	objSheet.setColumnWidth((short)2,(short)15000);
	objSheet.setColumnWidth((short)3,(short)3500);
	objSheet.setColumnWidth((short)4,(short)3500);
	objSheet.setColumnWidth((short)5,(short)5000);
	objSheet.setColumnWidth((short)6,(short)3500);
	objSheet.setColumnWidth((short)7,(short)3500);
	objSheet.setColumnWidth((short)8,(short)3500);
	objSheet.setColumnWidth((short)9,(short)3500);
	
	//--------------------------------------------------------------------------------------
	for (int i=0; i<holdStockLsit.size() ; i++) {
		
		HoldStockVO holdStockVO =new HoldStockVO();
		holdStockVO=holdStockLsit.get(i);

		objRow = objSheet.createRow((short)i+1);
		
		objCell = objRow.createCell((short)0);
		objCell.setCellValue(holdStockVO.getGroupName());
		objCell = objRow.createCell((short)1);
		objCell.setCellValue(holdStockVO.getProductCode());
		objCell = objRow.createCell((short)2);
		objCell.setCellValue(holdStockVO.getProductName());
		objCell = objRow.createCell((short)3);
		objCell.setCellValue(holdStockVO.getApplyDateCnt()+" 일");
		objCell = objRow.createCell((short)4);
		objCell.setCellValue(holdStockVO.getHoldStockCnt());
		objCell = objRow.createCell((short)5);
		objCell.setCellValue(holdStockVO.getHoldStockDateTime());
		objCell = objRow.createCell((short)6);
		objCell.setCellValue(holdStockVO.getSaleAvg());
		objCell = objRow.createCell((short)7);
		objCell.setCellValue(holdStockVO.getCon_applyDateCnt()+" 일");
		objCell = objRow.createCell((short)8);
		objCell.setCellValue(holdStockVO.getRecomendCnt());
		objCell = objRow.createCell((short)9);
		objCell.setCellValue(holdStockVO.getResultRate()+" %");
	}

	
	out.clear(); 

	out=pageContext.pushBody();
	
	
	fileOut = response.getOutputStream(); 
	objWorkBook.write(fileOut);
	fileOut.close();

%>
