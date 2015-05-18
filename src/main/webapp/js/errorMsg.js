function resultMsg(result){
	
	 result=result.replace('order0000','발주 처리를 성공했습니다.');
	 
	 //주문처리
	 result=result.replace('order0001','발주 처리내역이 없습니다.\n시스템 관리자에게 문의하세요.[DB 처리오류]');
	 result=result.replace('order0002','발주처리에 실패했습니다.\n시스템 관리자에게 문의하세요.');
	 result=result.replace('order0003','메일전송에 실패했습니다.\n메일정보 확인 및 시스템 관리자에게 문의하세요\n발주처리 정보는 출력하여 재전송 부탁드립니다.');
	 result=result.replace('order0004','주문접수파일 생성시 오류가 발생했습니다.\n시스템 관리자에게 문의하세요.');
	 
	 result=result.replace('order0010','발주취소 처리를 성공했습니다.');
	 result=result.replace('order0011','발주취소 처리내역이 없습니다.\n시스템 관리자에게 문의하세요.[DB 처리오류]');
	 result=result.replace('order0012','발주취소 처리에 실패했습니다.\n시스템 관리자에게 문의하세요.');
	 
	 result=result.replace('order0020','검수등록 처리를 성공했습니다.');
	 result=result.replace('order0021','검수등록 처리내역이 없습니다.\n시스템 관리자에게 문의하세요.[DB 처리오류]');
	 result=result.replace('order0022','검수등록 처리에 실패했습니다.\n시스템 관리자에게 문의하세요.');
	 
	 //보류처리
	 result=result.replace('defer0000','보류 처리를 성공했습니다.');
	 result=result.replace('defer0001','보류 처리내역이 없습니다.\n시스템 관리자에게 문의하세요.[DB 처리오류]');
	 result=result.replace('defer0002','보류처리에 실패했습니다.\n시스템 관리자에게 문의하세요.');
	 
	//댓글처리
	 result=result.replace('pe0000','비고 comment를 정상적으로 등록 했습니다.');
	 result=result.replace('pe0001','처리내역이 없습니다.\n시스템 관리자에게 문의하세요.[DB 처리오류]');
	 result=result.replace('pe0002','비고 comment를 정상적으로 등록하지 못 했습니다..\n시스템 관리자에게 문의하세요.');
	 
	//검수처리
	 result=result.replace('check0000','검수처리를 완료했습니다.');
	 result=result.replace('check0001','처리내역이 없습니다.\n시스템 관리자에게 문의하세요.[DB 처리오류]');
	 result=result.replace('check0002','비고 comment를 정상적으로 등록하지 못 했습니다..\n시스템 관리자에게 문의하세요.');
	 
	//회수 등록처리
	 result=result.replace('recovery0000','회수 품목 요청을 완료했습니다.');
	 result=result.replace('recovery0001','처리내역이 없습니다.\n시스템 관리자에게 문의하세요.[DB 처리오류]');
	 result=result.replace('recovery0002','회수 품목 요청을 정상적으로 처리하지 못 했습니다..\n시스템 관리자에게 문의하세요.');
	 
	 alert(result);

}