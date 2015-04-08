/**
 *
 */
package com.offact.addys.service;

import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.ADCodeManageVO;
import com.offact.addys.vo.ADCommonFormManageVO;
import com.offact.addys.vo.ADGroupCodeManageVO;
import com.offact.addys.vo.ADSmsFormManageVO;
import com.offact.addys.vo.ADTransferFormManageVO;
import com.offact.addys.vo.DealVO;
import com.offact.addys.vo.ad.ADCategoryVO;
import com.offact.addys.vo.ad.ADFuncClassVO;
import com.offact.addys.vo.ad.ADUserListManageVO;
import com.offact.addys.vo.ad.ConsultExileUserVo;
import com.offact.addys.vo.ad.PartnerInfoIsVO;
import com.offact.addys.vo.ad.SuccessExamIsVO;
import com.offact.addys.vo.common.ExcelulErrLogVO;
import com.offact.addys.vo.common.OutCallSearchVO;
import com.offact.addys.vo.common.UserSkillVO;

/**
 * @author 4530
 */
public interface ADService {
    /**
     * 코드 내용 조회
     *
     * @param code
     * @return
     * @throws BizException
     */
    public ADCodeManageVO getcodeContents(ADCodeManageVO code) throws BizException;

    /**
     * 코드 전체 갯수
     *
     * @return
     * @throws BizException
     */
    public int getCodeCnt() throws BizException;

    /**
     * 코드 MAX 번호
     *
     * @return
     * @throws BizException
     */
    public String getCodeMax() throws BizException;

    /**
     * 코드 목록
     *
     * @return
     * @throws BizException
     */
    public List<ADCodeManageVO> getCodeList(ADCodeManageVO code) throws BizException;

    /**
     * 코드 목록
     *
     * @return
     * @throws BizException
     */
    public List<ADCodeManageVO> getSearchCodeList(ADCodeManageVO code) throws BizException;

    public int getSearchCodeListCnt(ADCodeManageVO code) throws BizException;

    /**
     * 코드 등록
     *
     * @param code
     * @return
     * @throws BizException
     */
    public void insertCode(ADCodeManageVO code) throws BizException;

    public int checkCode(ADCodeManageVO code) throws BizException;

    /**
     * 코드수정 수정
     *
     * @param code
     * @return
     * @throws BizException
     */
    public void modifyCode(ADCodeManageVO code) throws BizException;

    /**
     * 코드 삭제
     *
     * @param code
     * @return
     * @throws BizException
     */
    public void deleteCode(ADCodeManageVO code) throws BizException;

    /**
     * 코드 콤보 목록
     *
     * @param code
     * @return
     * @throws BizException
     */
    public List<ADCodeManageVO> getCodeComboList(ADCodeManageVO code) throws BizException;

    /**
     * 코드 콤보 목록(부서코드)
     *
     * @param code
     * @return
     * @throws BizException
     */
    public List<ADCodeManageVO> getDeptCodeComboList(ADCodeManageVO code) throws BizException;


    /**
     * 그룹코드 목록 조회
     *
     * @return
     * @throws BizException
     */
    public List<ADGroupCodeManageVO> getGroupcodeList() throws BizException;

    /**
     * 상담이력이관정보 내용 조회
     *
     * @param transfer
     * @return
     * @throws BizException
     */
    public ADTransferFormManageVO getTransferContents(ADTransferFormManageVO transfer) throws BizException;

    /**
     * 상담이력이관정보 전체 갯수
     *
     * @return
     * @throws BizException
     */
    public int getTransferCnt() throws BizException;

    /**
     * 상담이력이관정보 MAX 번호
     *
     * @return
     * @throws BizException
     */
    public String getTransferMax() throws BizException;

    /**
     * 상담이력이관정보 목록
     *
     * @return
     * @throws BizException
     */
    public List<ADTransferFormManageVO> getTransferList(ADTransferFormManageVO transfer) throws BizException;

    /**
     * 상담이력이관정보 목록
     *
     * @return
     * @throws BizException
     */
    public List<ADTransferFormManageVO> getSearchTransferList(ADTransferFormManageVO transfer) throws BizException;

    /**
     * 상담이력이관정보 등록
     *
     * @param transfer
     * @return
     * @throws BizException
     */
    public void insertTransfer(ADTransferFormManageVO transfer) throws BizException;

    /**
     * 상담이력이관정보수정 수정
     *
     * @param transfer
     * @return
     * @throws BizException
     */
    public void modifyTransfer(ADTransferFormManageVO transfer) throws BizException;

    /**
     * 상담이력이관정보 삭제
     *
     * @param transfer
     * @return
     * @throws BizException
     */
    public void deleteTransfer(ADTransferFormManageVO transfer) throws BizException;

    /**
     * 상담이력이관정보 내용 조회
     *
     * @param transfer
     * @return
     * @throws BizException
     */
    public ADSmsFormManageVO getSmsContents(ADSmsFormManageVO sms) throws BizException;

    /**
     * 상담이력이관정보 전체 갯수
     *
     * @return
     * @throws BizException
     */
    public int getSmsCnt() throws BizException;

    /**
     * 서식관리 정보 전체 갯수
     *
     * @return
     * @throws BizException
     */
    public int getCommonFormCnt(ADCommonFormManageVO commonFormVO) throws BizException;

    /**
     * 상담이력이관정보 MAX 번호
     *
     * @return
     * @throws BizException
     */
    public String getSmsMax() throws BizException;

    /**
     * 서식관리 MAX 번호
     *
     * @return
     * @throws BizException
     */
    public String getCommonFormMax() throws BizException;

    public List<ADSmsFormManageVO> getSmsList(ADSmsFormManageVO sms) throws BizException;

    /**
     * 서식관리 정보 목록
     *
     * @return
     * @throws BizException
     */
    public List<ADCommonFormManageVO> getCommonFormList(ADCommonFormManageVO commonForm) throws BizException;

    /**
     * 상담이력이관정보 목록
     *
     * @return
     * @throws BizException
     */
    public List<ADSmsFormManageVO> getSearchSmsList(ADSmsFormManageVO sms) throws BizException;

    /**
     * 상담이력이관정보 등록
     *
     * @param transfer
     * @return
     * @throws BizException
     */
    public void insertSms(ADSmsFormManageVO sms) throws BizException;

    /**
     * 상담이력이관정보 등록
     *
     * @param transfer
     * @return
     * @throws BizException
     */
    public void insertCommonForm(ADCommonFormManageVO commonForm) throws BizException;

    /**
     * 상담이력이관정보수정 수정
     *
     * @param transfer
     * @return
     * @throws BizException
     */
    public void modifySms(ADSmsFormManageVO sms) throws BizException;

    /**
     * 상담이력이관정보수정 수정
     *
     * @param transfer
     * @return
     * @throws BizException
     */
    public void modifyCommonForm(ADCommonFormManageVO commonForm) throws BizException;

    /**
     * 상담이력이관정보 삭제
     *
     * @param transfer
     * @return
     * @throws BizException
     */
    public void deleteSms(ADSmsFormManageVO sms) throws BizException;

    /**
     * 딜정보 리스트 조회
     *
     * @param deal
     * @return
     * @throws BizException
     */
    public List<DealVO> getDealList(DealVO deal) throws BizException;

    /**
     * 딜정보 카운트 조회
     *
     * @param deal
     * @return
     * @throws BizException
     */
    public int getDealCnt(DealVO deal) throws BizException;

    /**
     * 파트너 정보 등록
     *
     * @param PartnerInfoIsVO
     * @return
     * @throws BizException
     */
    public List<PartnerInfoIsVO> regiPartnerInfo(List<PartnerInfoIsVO> partnerInfoIsVOList) throws BizException;

    /**
     * 파트너 정보 조회
     *
     * @param PartnerInfoIsVO
     * @return
     * @throws BizException
     */
    public List<PartnerInfoIsVO> selectPartnerInfo(PartnerInfoIsVO partnerInfoIsVO) throws BizException;

    /**
     * 성공사례 정보 등록
     *
     * @param SuccessExamIsVO
     * @return
     * @throws BizException
     */
    public List<SuccessExamIsVO> regiSuccessExam(List<SuccessExamIsVO> successExamIsVO) throws BizException;

    /**
     * 성공사례 정보 조회
     *
     * @param SuccessExamIsVO
     * @return
     * @throws BizException
     */
    public List<SuccessExamIsVO> selectSuccessExam(SuccessExamIsVO successExamIsVO) throws BizException;

    /**
     * 기능분류 정보 조회
     *
     * @param SuccessExamIsVO
     * @return
     * @throws BizException
     */
    public List<ADFuncClassVO> getfuncClassList(ADFuncClassVO funcClassVO) throws BizException;

    /**
     * 기능분류 정보 조회
     *
     * @param SuccessExamIsVO
     * @return
     * @throws BizException
     */
    public int getfuncClassListCnt(ADFuncClassVO funcClassVO) throws BizException;

    public List<ADFuncClassVO> getCurrFuncByAuth(ADFuncClassVO fncClassVO) throws BizException;

    public void saveFuncByGroup(String arrFuncCode, String arrFuncByGroup, String userId, String userDeptCode) throws BizException;

    public void saveUserAuth(String regiUser, String userDeptCode) throws BizException;

    public void saveUserAuth(String regiUser, String userId, String userDeptCode) throws BizException;

    public List<ConsultExileUserVo> getConsultExileInitList() throws BizException;

    public List<ConsultExileUserVo> getCurrConsultExileInitList(ConsultExileUserVo consultExileUserVo) throws BizException;

    public List<ConsultExileUserVo> getConsultCateList() throws BizException;

    public void saveConsultExileUser(String arrUserInfo, String arrTargetExile, String regiUserId) throws BizException;

    public List<UserSkillVO> getSkillList(ADUserListManageVO userConVO) throws BizException;

    public void saveConsultUserSkill(String arrUserInfo, String arrTargetUserSkill, String regiUserId) throws BizException;

    public List<UserSkillVO> getCurrUserSkillList(UserSkillVO userSkillVO) throws BizException;


    public void revokeUser(ADUserListManageVO userConVO) throws BizException;



    /**
     * 아웃콜 조회
     *
     * @return
     * @throws BizException
     */
    public List<OutCallSearchVO> getCallBackList(OutCallSearchVO outCallSearchVO) throws BizException;

    /**
     * 아웃콜 조회
     *
     * @return
     * @throws BizException
     */
    public int getCallBackListCnt(OutCallSearchVO outCallSearchVO) throws BizException;

    public void settingOutCallUser(OutCallSearchVO outCallSearchVO) throws BizException;

    public void outCallUserUpdate(OutCallSearchVO outCallSearchVO, String arrOutcallSno) throws BizException;

    public int outCallUserBatchUpdateRenounce(OutCallSearchVO outCallSearchVO) throws BizException;

    public int outCallUserBatchUpdateRevoke(OutCallSearchVO outCallSearchVO) throws BizException;

    public void IPCCOutcallAutoAssign(String deptCode, String userId, String inType) throws BizException;

    /**
     * 아웃콜 조회
     *
     * @return
     * @throws BizException
     */
    public List<OutCallSearchVO> getOutCallList(OutCallSearchVO outCallSearchVO) throws BizException;

    /**
     * 아웃콜 조회
     *
     * @return
     * @throws BizException
     */
    public int getOutCallListCnt(OutCallSearchVO outCallSearchVO) throws BizException;

    public void deletePartner(String partnerId) throws BizException;

    /**
     * OutCall Excel 등록
     *
     * @param PartnerInfoIsVO
     * @return
     * @throws BizException
     */
    public Map regiExcelUpload(List<OutCallSearchVO> outcallUploadList) throws BizException;

    public void excelErrLog(List<ExcelulErrLogVO> excelulErrLogVOList) throws BizException;

    /**
     * 카테고리 대분류조회
     *
     * @author HSH
     * @param ADCategoryVO
     * @return List<ADCategoryVO>
     * @throws BizException
     */
    public List<ADCategoryVO> getComboCategoryL() throws BizException;

    /**
     * 카테고리 소분류조회
     *
     * @author HSH
     * @param ADCategoryVO
     * @return List<ADCategoryVO>
     * @throws BizException
     */
    public List<ADCategoryVO> getComboCategoryS(ADCategoryVO adCategoryVO) throws BizException;
}
