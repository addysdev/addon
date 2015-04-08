/**
 * 
 */
package com.offact.addys.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.common.CmmService;
import com.offact.addys.vo.RefferLogVO;
import com.offact.addys.vo.BoardVO;
import com.offact.addys.vo.UserVO;
import com.offact.addys.vo.common.CallHistorySearchVO;
import com.offact.addys.vo.common.ReserveSearchVO;
import com.offact.addys.vo.common.OutCallSearchVO;
import com.offact.addys.vo.common.TransferSearchVO;
import com.offact.addys.vo.common.UserSkillVO;
import com.offact.addys.vo.cs.QnaVO;
import com.offact.addys.vo.common.AddInfoVO;

/**
 * @author 4530
 *
 */
@Service
public class CmmServiceImpl implements CmmService {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private SqlSessionCommonDao commonDao;

	@Override
	// 상담 이력 조회(딜 상담이력 및 통화이력)
	public List<CallHistorySearchVO> getCallHistorySearch(CallHistorySearchVO callHistorySearchVO) throws BizException {
		List<CallHistorySearchVO> callHistList = commonDao.selectList("CallHistorySearch.getCallHistorySearch", callHistorySearchVO);
		
		return callHistList;
	}
	
	@Override
	// 상담 이력 상세 조회(딜 상담이력 및 통화이력)
	public CallHistorySearchVO getCallHistoryView(CallHistorySearchVO callHistorySearchVO) throws BizException {
		CallHistorySearchVO callHist = commonDao.selectOne("CallHistorySearch.getCallHistoryView", callHistorySearchVO);
		
		return callHist;
	}
	
	@Override
	// 게시판 업무용 상담 이력 상세 조회(딜 상담이력 및 통화이력)
	public CallHistorySearchVO getQnaBoardCallHistoryView(CallHistorySearchVO callHistorySearchVO) throws BizException {
		CallHistorySearchVO callHist = commonDao.selectOne("CallHistorySearch.getQnaBoardCallHistoryView", callHistorySearchVO);
		
		return callHist;
	}
	 /**
	 * 예약콜 건수 조회
	 * @return
	 * @throws BizException
	 */
	@Override
	public int getReserveTotalCount(ReserveSearchVO reserveSearchVO) throws BizException {
		return commonDao.selectOne("ReserveSearch.getReserveTotalCount", reserveSearchVO);
	}
	
	@Override
	// 예약콜 이력 조회
	public List<ReserveSearchVO> getReserveSearch(ReserveSearchVO reserveSearchVO) throws BizException {
		List<ReserveSearchVO> reserveList = commonDao.selectList("ReserveSearch.getReserveSearch", reserveSearchVO);
		
		return reserveList;
	}
	
	@Override
	// 예약콜 이력 상세 조회
	public ReserveSearchVO getReserveDetailSearch(ReserveSearchVO reserveSearchVO) throws BizException {
		ReserveSearchVO vo = commonDao.selectOne("ReserveSearch.getReserveSearch", reserveSearchVO);
		
		return vo;
	}
	
	@Override
	// 예약콜 근접 건수 조회
	public int getReserveCallImitCount(ReserveSearchVO reserveSearchVO) throws BizException {
		int nCount = commonDao.selectOne("ReserveSearch.getReserveCallImitCount", reserveSearchVO);
		
		return nCount;
	}
	/**
	 * 아웃콜 건수 조회
	 * @return
	 * @throws BizException
	 */
	@Override
	public int getOutCallTotalCount(OutCallSearchVO outCallSearchVO) throws BizException {
		return commonDao.selectOne("OutCallSearch.getOutCallTotalCount", outCallSearchVO);
	}
		
	@Override
	// 아웃콜 이력 조회
	public List<OutCallSearchVO> getOutCallSearch(OutCallSearchVO outCallSearchVO) throws BizException {
		List<OutCallSearchVO> outCallList = commonDao.selectList("OutCallSearch.getOutCallSearch", outCallSearchVO);
		
		return outCallList;
	}
	
	@Override
	// 아웃콜 이력 상세 조회
	public OutCallSearchVO getOutCallDetailSearch(OutCallSearchVO outCallSearchVO) throws BizException {
		OutCallSearchVO vo = commonDao.selectOne("OutCallSearch.getOutCallSearch", outCallSearchVO);
		
		return vo;
	}
	
	/**
	 * 받은이관 건수 조회
	 * @return
	 * @throws BizException
	 */
	@Override
	public int getReciveTransferTotalCount(TransferSearchVO transferSearchVO) throws BizException {
		return commonDao.selectOne("TransferSearch.getReciveTransferTotalCount", transferSearchVO);
	}
	
	
	@Override
	// 받은이관 이력 조회
	public List<TransferSearchVO> getReciveTransferSearch(TransferSearchVO transferSearchVO) throws BizException {
		List<TransferSearchVO> outTransferList = commonDao.selectList("TransferSearch.getReciveTransferSearch", transferSearchVO);
		
		return outTransferList;
	}
	
	@Override
	// 받은이관 이력 상세 조회
	public TransferSearchVO getReciveTransferDetailSearch(TransferSearchVO transferSearchVO) throws BizException {
		TransferSearchVO vo = commonDao.selectOne("TransferSearch.getReciveTransferSearch", transferSearchVO);
		
		return vo;
	}
	
	/**
	 * 보낸이관 건수 조회
	 * @return
	 * @throws BizException
	 */
	@Override
	public int getSendTransferTotalCount(TransferSearchVO transferSearchVO) throws BizException {
		return commonDao.selectOne("TransferSearch.getSendTransferTotalCount", transferSearchVO);
	}
	
	@Override
	// 보낸이관 이력 조회
	public List<TransferSearchVO> getSendTransferSearch(TransferSearchVO transferSearchVO) throws BizException {
		List<TransferSearchVO> outTransferList = commonDao.selectList("TransferSearch.getSendTransferSearch", transferSearchVO);
		
		return outTransferList;
	}
	
	@Override
	// 이관 이력 조회(상담이력 상세조회의 이관(보내고->받은 이력이 다 나와야함)
	public List<TransferSearchVO> getDetailTransferSearch(TransferSearchVO transferSearchVO) throws BizException {
		List<TransferSearchVO> outTransferList = commonDao.selectList("TransferSearch.getDetailTransferSearch", transferSearchVO);
		
		return outTransferList;
	}
	
	@Override
	// 상담 녹취Key 업데이트
	public void counselRecordUpdate(CallHistorySearchVO inUpdateVO) throws BizException {		
		commonDao.update("CallHistorySearch.updateCounselRecord", inUpdateVO);		
	}
	
	/**
	 * 콜 Skill 조회
	 * @return
	 * @throws BizException
	 */
	@Override
	public List<UserSkillVO> getSkillList(UserSkillVO userSkillVO) throws BizException{
		return commonDao.selectList("Transferconference.getSkillList", userSkillVO);
	}
	
	@Override
	public int getSkillListCnt(UserSkillVO userSkillVO) throws BizException {
		return commonDao.selectOne("Transferconference.getSkillListCount", userSkillVO);
	}

	/**
	 * 상담원 접근 이력 등록
	 * @return
	 * @throws BizException
	 */
	@Override
	public RefferLogVO insertRefferLog(RefferLogVO refferLogVO) throws BizException {
		// TODO Auto-generated method stub
		commonDao.insert("RefferLog.insertRefferLog", refferLogVO);
		
		return refferLogVO;
	}

	/**
	 * 콜 User 조회
	 * @return
	 * @throws BizException
	 */
	@Override
	public List<UserSkillVO> getUserList(UserSkillVO userSkillVO) throws BizException{
		return commonDao.selectList("Transferconference.getUserList", userSkillVO);
	}
	
	@Override
	public int getUserListCnt(UserSkillVO userSkillVO) throws BizException {
		return commonDao.selectOne("Transferconference.getUserListCount", userSkillVO);
	}
	
	/**
	 * QNA게시판 조회
	 * @return
	 * @throws BizException
	 */
	@Override
	public List<QnaVO> getBoardQnaList(QnaVO inQnaVO) throws BizException{
		
		return commonDao.selectList("Qna.getBoardQnaList", inQnaVO);
	}
	
	/**
	 * QNA게시판 전체 갯수
	 * @return
	 * @throws BizException
	 */
	@Override
	public int getBoardQnaCount(QnaVO inQnaVO) throws BizException {
		return commonDao.selectOne("Qna.getBoardQnaCount", inQnaVO);
	}
		
	/**
	 * QNA게시판 완료되지않은 전체건수 조회
	 * @return
	 * @throws BizException
	 */
	@Override
	public int getNotComplitBoardQnaCount(QnaVO inQnaVO) throws BizException {
		return commonDao.selectOne("Qna.getNotComplitBoardQnaCount", inQnaVO);
	}
	
	/**
	 * 추가정보 업무정보 부분 조회
	 * @return
	 * @throws BizException
	 */
	@Override
	public AddInfoVO getAddInfoBusinessPart(AddInfoVO inAddInfoVo) throws BizException{
		return commonDao.selectOne("AddInfo.getAddInfoBusinessPart", inAddInfoVo);		
	}
	
	/**
	 * 추가정보 업무정보 상담사 근무상태 조회
	 * @return
	 * @throws BizException
	 */
	@Override
	public String getAddInfoUserWorkYnPart(AddInfoVO inAddInfoVo) throws BizException{
		return commonDao.selectOne("AddInfo.getAddInfoUserWorkYnPart", inAddInfoVo);		
	}
	
	/**
	 * 추가정보 업무정보 상담사 근무상태 변경
	 * @return
	 * @throws BizException
	 */
	@Override
	public void setAddInfoUserWorkYnPart(AddInfoVO inAddInfoVo) throws BizException{
		commonDao.update("AddInfo.setAddInfoUserWorkYnPart", inAddInfoVo);		
	}
	
	/**
	 * QNA게시판 완료되지않은 전체건수 강제회수
	 * @return
	 * @throws BizException
	 */
	@Override
	public void setUpdateCancelQnaBoard(QnaVO inQnaVO) throws BizException {
		commonDao.update("Qna.updateCancelQnaBoard", inQnaVO);
	}
	
	/**
	 * QNA게시판 데이터 자동 할당(분배요청)
	 * @return
	 * @throws BizException
	 */
	@Override
	public void setUpdateAutoAssignQnaBoard(QnaVO inQnaVO) throws BizException {
		commonDao.update("Qna.updateAutoAssignQnaBoard", inQnaVO);
	}

	/**
	 * QNA게시판 데이터 자동 할당(분배요청) 건수 조회
	 * @return
	 * @throws BizException
	 */
	@Override
	public int getAutoAssignQnaBoardCount() throws BizException {
		return commonDao.selectOne("Qna.getAutoAssignQnaBoardCount");
	}
	
	/**
	 * 로그인 접속이력 저장
	 * @return
	 * @throws BizException
	 */
	@Override
	public void setInsertUserState(UserVO userVO) throws BizException {
		commonDao.insert("User.setInsertUserState", userVO);
	}
	/**
	 * 로그인 상태 업데이트
	 * @return
	 * @throws BizException
	 */
	@Override
	public void setUpdateUserState(UserVO userVO) throws BizException {
		commonDao.update("User.setUpdateUserState", userVO);
	}
	/**
	 * 로그인 상태 업데이트 통합처리
	 * @return
	 * @throws BizException
	 */
	@Override
	public void setUserState(UserVO userVO) throws BizException {
		commonDao.insert("User.setInsertUserState", userVO);
		commonDao.update("User.setUpdateUserState", userVO);
	}
}
