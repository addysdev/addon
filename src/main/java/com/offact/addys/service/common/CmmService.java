/**
 * 상담이력 공통  관련 서비스
 */
package com.offact.addys.service.common;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.RefferLogVO;
import com.offact.addys.vo.BoardVO;
import com.offact.addys.vo.common.CallHistorySearchVO;
import com.offact.addys.vo.common.ReserveSearchVO;
import com.offact.addys.vo.common.OutCallSearchVO;
import com.offact.addys.vo.common.TransferSearchVO;
import com.offact.addys.vo.common.UserSkillVO;
import com.offact.addys.vo.cs.QnaVO;
import com.offact.addys.vo.common.AddInfoVO;
import com.offact.addys.vo.UserVO;

/**
 * @author dskwak
 *
 */
public interface CmmService {
	
	/**
	 * 상담 이력 조회(딜 상담이력 및 통화이력)
	 * @return
	 * @throws BizException
	 */
	public List<CallHistorySearchVO> getCallHistorySearch(CallHistorySearchVO callHistorySearchVO) throws BizException;
	
	/**
	 * 상담 이력 상세 조회(딜 상담이력 및 통화이력)
	 * @return
	 * @throws BizException
	 */
	public CallHistorySearchVO getCallHistoryView(CallHistorySearchVO callHistorySearchVO) throws BizException;
	
	/**
	 * 게시판업무용 상담 이력 상세 조회(딜 상담이력 및 통화이력)
	 * @return
	 * @throws BizException
	 */
	public CallHistorySearchVO getQnaBoardCallHistoryView(CallHistorySearchVO callHistorySearchVO) throws BizException;
	
	/**
	 * 예약콜 건수 조회
	 * @return
	 * @throws BizException
	 */
	public int getReserveTotalCount(ReserveSearchVO reserveSearchVO) throws BizException;
	
	/**
	 * 예약콜 이력 조회
	 * @return
	 * @throws BizException
	 */
	public List<ReserveSearchVO> getReserveSearch(ReserveSearchVO reserveSearchVO) throws BizException;
	
	/**
	 * 예약콜 이력 상세 조회
	 * @return
	 * @throws BizException
	 */
	public ReserveSearchVO getReserveDetailSearch(ReserveSearchVO reserveSearchVO) throws BizException;
	
	/**
	 * 예약콜 근접 건수 조회
	 * @return
	 * @throws BizException
	 */
	public int getReserveCallImitCount(ReserveSearchVO reserveSearchVO) throws BizException;
	
	/**
	 * 아웃콜 건수 조회
	 * @return
	 * @throws BizException
	 */
	public int getOutCallTotalCount(OutCallSearchVO outCallSearchVO) throws BizException;
	
	/**
	 * 아웃콜 이력 조회
	 * @return
	 * @throws BizException
	 */
	public List<OutCallSearchVO> getOutCallSearch(OutCallSearchVO outCallSearchVO) throws BizException;
	
	/**
	 * 아웃콜 이력 상세 조회
	 * @return
	 * @throws BizException
	 */
	public OutCallSearchVO getOutCallDetailSearch(OutCallSearchVO outCallSearchVO) throws BizException;
	
	/**
	 * 받은이관 건수 조회
	 * @return
	 * @throws BizException
	 */
	public int getReciveTransferTotalCount(TransferSearchVO transferSearchVO) throws BizException;
	
	/**
	 * 받은이관 이력 조회
	 * @return
	 * @throws BizException
	 */
	public List<TransferSearchVO> getReciveTransferSearch(TransferSearchVO transferSearchVO) throws BizException;
	
	/**
	 * 받은이관 이력 상세 조회
	 * @return
	 * @throws BizException
	 */
	public TransferSearchVO getReciveTransferDetailSearch(TransferSearchVO transferSearchVO) throws BizException;
	
	/**
	 * 보낸이관 건수 조회
	 * @return
	 * @throws BizException
	 */
	public int getSendTransferTotalCount(TransferSearchVO transferSearchVO) throws BizException;
	
	/**
	 * 보낸이관 이력 조회
	 * @return
	 * @throws BizException
	 */
	public List<TransferSearchVO> getSendTransferSearch(TransferSearchVO transferSearchVO) throws BizException;
	
	/**
	 * 이관 이력 조회(상담이력 상세조회의 이관(보내고->받은 이력이 다 나와야함)
	 * @return
	 * @throws BizException
	 */
	public List<TransferSearchVO> getDetailTransferSearch(TransferSearchVO transferSearchVO) throws BizException;
	
	/**
	 * 상담 녹취Key 업데이트
	 * @return
	 * @throws BizException
	 */
	public void counselRecordUpdate(CallHistorySearchVO callHistorySearchVO) throws BizException;
	
	/**
	 * 콜 Skill 조회
	 * @return
	 * @throws BizException
	 */
	public List<UserSkillVO> getSkillList(UserSkillVO userSkillVO) throws BizException;
	
	/**
	 * 콜 Skill 전체 갯수
	 * @return
	 * @throws BizException
	 */
	public int getSkillListCnt(UserSkillVO userSkillVO) throws BizException;
	
	/**
	 * 콜 User 조회
	 * @return
	 * @throws BizException
	 */
	public List<UserSkillVO> getUserList(UserSkillVO userSkillVO) throws BizException;
	
	/**
	 * 콜 User 전체 갯수
	 * @return
	 * @throws BizException
	 */
	public int getUserListCnt(UserSkillVO userSkillVO) throws BizException;
	
	/**
	 * 상담원 접근 이력 등록
	 * @return
	 * @throws BizException
	 */
	public RefferLogVO insertRefferLog(RefferLogVO refferLogVO) throws BizException;

	
	/**
	 * QNA게시판 조회
	 * @return
	 * @throws BizException
	 */
	public List<QnaVO> getBoardQnaList(QnaVO inQnaVO) throws BizException;
	
	/**
	 * QNA게시판 전체 갯수
	 * @return
	 * @throws BizException
	 */
	public int getBoardQnaCount(QnaVO inQnaVO) throws BizException;
	
	/**
	 * QNA게시판 완료되지않은 전체건수 조회
	 * @return
	 * @throws BizException
	 */
	public int getNotComplitBoardQnaCount(QnaVO inQnaVO) throws BizException;
	
	/**
	 * 추가정보 업무정보 부분 조회
	 * @return
	 * @throws BizException
	 */
	public AddInfoVO getAddInfoBusinessPart(AddInfoVO inAddInfoVo) throws BizException;
	
	/**
	 * 추가정보 업무정보 상담사 근무상태 조회
	 * @return
	 * @throws BizException
	 */
	public String getAddInfoUserWorkYnPart(AddInfoVO inAddInfoVo) throws BizException;
	
	/**
	 * 추가정보 업무정보 상담사 근무상태 변경
	 * @return
	 * @throws BizException
	 */
	public void setAddInfoUserWorkYnPart(AddInfoVO inAddInfoVo) throws BizException;

	/**
	 * QNA게시판 완료되지않은 전체건수 강제회수
	 * @return
	 * @throws BizException
	 */
	public void setUpdateCancelQnaBoard(QnaVO inQnaVO) throws BizException;
	
	/**
	 * QNA게시판 데이터 자동 할당(분배요청)
	 * @return
	 * @throws BizException
	 */
	public void setUpdateAutoAssignQnaBoard(QnaVO inQnaVO) throws BizException;
	
	/**
	 * QNA게시판 데이터 자동 할당(분배요청) 건수 조회
	 * @return
	 * @throws BizException
	 */
	public int getAutoAssignQnaBoardCount() throws BizException;
	
	/**
	 * 로그인 이력저장
	 * @return
	 * @throws BizException
	 */
	public void setInsertUserState(UserVO userVO) throws BizException;
	
	/**
	 * 로그인 상태업데이트
	 * @return
	 * @throws BizException
	 */
	public void setUpdateUserState(UserVO userVO) throws BizException;
	
	/**
	 * 로그인 상태업데이트
	 * @return
	 * @throws BizException
	 */
	public void setUserState(UserVO userVO) throws BizException;
	
}
