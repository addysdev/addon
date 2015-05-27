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
				com.offact.addys.vo.recovery.RecoveryVO" %>
<%
	String fileName = "orderExcelList.xlsx";

    String strClient = request.getHeader("User-Agent");
	List<RecoveryVO> recoveryList = (List)request.getAttribute("recoveryExcelList");
    
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
	objWorkBook.setSheetName(0 , "창고이동" );
	//행생성
	XSSFRow objRow = objSheet.createRow((short)0);
	//셀 생성
	XSSFCell objCell = null;
	//------------------------

	objRow = objSheet.createRow((short)0);
	
	objCell = objRow.createCell((short)0);
	objCell.setCellValue("일자(8)");
	objCell = objRow.createCell((short)1);
	objCell.setCellValue("순번(4)");
	objCell = objRow.createCell((short)2);
	objCell.setCellValue("프로젝트코드(14)");
	objCell = objRow.createCell((short)3);
	objCell.setCellValue("담당자코드(30)");
	objCell = objRow.createCell((short)4);
	objCell.setCellValue("보내는창고코드(5)");
	objCell = objRow.createCell((short)5);
	objCell.setCellValue("받는창고코드(5)");
	objCell = objRow.createCell((short)6);
	objCell.setCellValue("품목코드(20)");
	objCell = objRow.createCell((short)7);
	objCell.setCellValue("수량(12)");
	objCell = objRow.createCell((short)8);
	objCell.setCellValue("적요(200)");
	objCell = objRow.createCell((short)9);
	objCell.setCellValue("생산전표생성(1)");
	objCell = objRow.createCell((short)10);
	objCell.setCellValue("ecount");
	//----------------------------------------------------------------------------------------
	//길이 설정
	objSheet.setColumnWidth((short)0,(short)3000);
	objSheet.setColumnWidth((short)1,(short)3000);
	objSheet.setColumnWidth((short)2,(short)3000);
	objSheet.setColumnWidth((short)3,(short)4000);
	objSheet.setColumnWidth((short)4,(short)4000);
	objSheet.setColumnWidth((short)5,(short)4000);
	objSheet.setColumnWidth((short)6,(short)4000);
	objSheet.setColumnWidth((short)7,(short)3000);
	objSheet.setColumnWidth((short)8,(short)4000);
	objSheet.setColumnWidth((short)9,(short)4000);
	objSheet.setColumnWidth((short)10,(short)3000);
	
	//--------------------------------------------------------------------------------------
	for (int i=0; i<recoveryList.size() ; i++) {
		
		RecoveryVO recoveryVO =new RecoveryVO();
		recoveryVO=recoveryList.get(i);

		objRow = objSheet.createRow((short)i+1);
		
		objCell = objRow.createCell((short)0);
		objCell.setCellValue(recoveryVO.getUpdateDateTime());
		objCell = objRow.createCell((short)1);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)2);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)3);
		objCell.setCellValue(recoveryVO.getUpdateUserName());
		objCell = objRow.createCell((short)4);
		objCell.setCellValue(recoveryVO.getGroupId());
		objCell = objRow.createCell((short)5);
		objCell.setCellValue("AD001");
		objCell = objRow.createCell((short)6);
		objCell.setCellValue(recoveryVO.getProductCode());
		objCell = objRow.createCell((short)7);
		objCell.setCellValue(recoveryVO.getRecoveryResultCnt());
		objCell = objRow.createCell((short)8);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)9);
		objCell.setCellValue("");
		objCell = objRow.createCell((short)10);
		objCell.setCellValue("ecount");
	}

	
	out.clear(); 

	out=pageContext.pushBody();
	
	
	fileOut = response.getOutputStream(); 
	objWorkBook.write(fileOut);
	fileOut.close();

%>
