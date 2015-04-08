package com.offact.addys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offact.common.StringUtil;
import com.offact.framework.db.SqlSessionCommonDao;
import com.offact.framework.exception.BizException;
import com.offact.addys.service.ADService;
import com.offact.addys.vo.ADCodeManageVO;
import com.offact.addys.vo.ADCommonFormManageVO;
import com.offact.addys.vo.ADGroupCodeManageVO;
import com.offact.addys.vo.ADSmsFormManageVO;
import com.offact.addys.vo.ADTransferFormManageVO;
import com.offact.addys.vo.DealVO;
import com.offact.addys.vo.ad.ADCategoryVO;
import com.offact.addys.vo.ad.ADFuncClassVO;
import com.offact.addys.vo.ad.ADUserAuthListVO;
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
@Service
public class ADServiceImpl implements ADService {

    private final Logger        logger = Logger.getLogger(getClass());

    @Autowired
    private SqlSessionCommonDao commonDao;

    @Override
    public ADCodeManageVO getcodeContents(ADCodeManageVO code) throws BizException {
        ADCodeManageVO codeVO = commonDao.selectOne("ADCodeManage.getCodeContents", code);

        return codeVO;
    }

    @Override
    public int getCodeCnt() throws BizException {
        return commonDao.selectOne("ADCodeManage.getCodeCnt");
    }

    @Override
    public String getCodeMax() throws BizException {
        return commonDao.selectOne("ADCodeManage.getCodeMax");
    }

    @Override
    public List<ADCodeManageVO> getCodeList(ADCodeManageVO code) throws BizException {
        logger.info("code1+++++" + code);
        logger.info("codeId1:+++++" + code.getCurPage());
        List<ADCodeManageVO> codeList = commonDao.selectList("ADCodeManage.getCodeList", code);

        return codeList;
    }

    @Override
    public List<ADCodeManageVO> getCodeComboList(ADCodeManageVO code) throws BizException {
        List<ADCodeManageVO> codeList = commonDao.selectList("ADCodeManage.getCodeComboList", code);

        return codeList;
    }

    @Override
    public List<ADCodeManageVO> getDeptCodeComboList(ADCodeManageVO code) throws BizException {
        List<ADCodeManageVO> codeList = commonDao.selectList("ADCodeManage.getDeptCodeComboList", code);

        return codeList;
    }


    @Override
    public List<ADCodeManageVO> getSearchCodeList(ADCodeManageVO code) throws BizException {
        List<ADCodeManageVO> codeList = commonDao.selectList("ADCodeManage.getSearchCodeList", code);
        return codeList;
    }

    @Override
    public int getSearchCodeListCnt(ADCodeManageVO code) throws BizException {
        return commonDao.selectOne("ADCodeManage.getSearchCodeListCnt", code);
    }

    @Override
    public void insertCode(ADCodeManageVO code) throws BizException {

        commonDao.insert("ADCodeManage.insertCode", code);

    }

    @Override
    public int checkCode(ADCodeManageVO code) throws BizException {
        return commonDao.selectOne("ADCodeManage.checkCode", code);
    }

    @Override
    public void modifyCode(ADCodeManageVO code) throws BizException {
        commonDao.update("ADCodeManage.modifyCode", code);
    }

    @Override
    public void deleteCode(ADCodeManageVO code) throws BizException {
        commonDao.update("ADCodeManage.deleteCode", code);

    }

    @Override
    public List<ADGroupCodeManageVO> getGroupcodeList() throws BizException {

        List<ADGroupCodeManageVO> groupcodeList = commonDao.selectList("ADGroupCodeManage.getGroupcodeList");

        return groupcodeList;
    }

    @Override
    public ADTransferFormManageVO getTransferContents(ADTransferFormManageVO transfer) throws BizException {
        ADTransferFormManageVO transferVO = commonDao.selectOne("ADTransferFormManage.getTransferContents", transfer);

        return transferVO;
    }

    @Override
    public int getTransferCnt() throws BizException {
        return commonDao.selectOne("ADTransferFormManage.getTransferCnt");
    }

    @Override
    public String getTransferMax() throws BizException {
        return commonDao.selectOne("ADTransferFormManage.getTransferMax");
    }

    @Override
    public List<ADTransferFormManageVO> getTransferList(ADTransferFormManageVO transfer) throws BizException {
        List<ADTransferFormManageVO> transferList = commonDao.selectList("ADTransferFormManage.getTransferList", transfer);

        return transferList;
    }

    @Override
    public List<ADTransferFormManageVO> getSearchTransferList(ADTransferFormManageVO transfer) throws BizException {
        logger.info("목록을 조회합니다.");
        List<ADTransferFormManageVO> transferList = commonDao.selectList("ADTransferFormManage.getSearchTransferList", transfer);
        return transferList;
    }

    @Override
    public void insertTransfer(ADTransferFormManageVO transfer) throws BizException {

        commonDao.insert("ADTransferFormManage.insertTransfer", transfer);
    }

    @Override
    public void modifyTransfer(ADTransferFormManageVO transfer) throws BizException {
        commonDao.update("ADTransferFormManage.modifyTransfer", transfer);
    }

    @Override
    public void deleteTransfer(ADTransferFormManageVO transfer) throws BizException {
        commonDao.update("ADTransferFormManage.deleteTransfer", transfer);
    }

    @Override
    public ADSmsFormManageVO getSmsContents(ADSmsFormManageVO sms) throws BizException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getSmsCnt() throws BizException {
        return commonDao.selectOne("ADSmsFormManage.getSmsCnt");
    }

    @Override
    public int getCommonFormCnt(ADCommonFormManageVO commonFormVO) throws BizException {
        return commonDao.selectOne("ADCommonFormManage.getCommonFormCnt",commonFormVO);
    }

    @Override
    public String getSmsMax() throws BizException {
        return commonDao.selectOne("ADSmsFormManage.getSmsMax");
    }

    @Override
    public String getCommonFormMax() throws BizException {
        return commonDao.selectOne("ADCommonFormManage.getFormMax");
    }

    @Override
    public List<ADSmsFormManageVO> getSmsList(ADSmsFormManageVO sms) throws BizException {
        List<ADSmsFormManageVO> smsList = commonDao.selectList("ADSmsFormManage.getSmsList", sms);
        return smsList;
    }

    @Override
    public List<ADCommonFormManageVO> getCommonFormList(ADCommonFormManageVO commonForm) throws BizException {
        List<ADCommonFormManageVO> commonList = commonDao.selectList("ADCommonFormManage.getCommonFormList", commonForm);
        return commonList;
    }

    @Override
    public List<ADSmsFormManageVO> getSearchSmsList(ADSmsFormManageVO sms) throws BizException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insertSms(ADSmsFormManageVO sms) throws BizException {
        commonDao.insert("ADSmsFormManage.insertSms", sms);
    }

    @Override
    public void insertCommonForm(ADCommonFormManageVO commonForm) throws BizException {
        commonDao.insert("ADCommonFormManage.insertCommonForm", commonForm);
    }

    @Override
    public void modifySms(ADSmsFormManageVO sms) throws BizException {
        commonDao.update("ADSmsFormManage.modifySms", sms);

    }

    @Override
    public void modifyCommonForm(ADCommonFormManageVO commonForm) throws BizException {
        commonDao.update("ADCommonFormManage.modifyCommonForm", commonForm);

    }

    @Override
    public void deleteSms(ADSmsFormManageVO sms) throws BizException {
    }

    /**
     * 딜정보 리스트 조회
     */
    @Override
    public List<DealVO> getDealList(DealVO deal) throws BizException {
        return commonDao.selectList("Deal.getDealList", deal);
    }

    /**
     * 딜정보 카운트 조회
     */
    @Override
    public int getDealCnt(DealVO deal) throws BizException {
        return commonDao.selectOne("Deal.getDealCnt", deal);
    }

    /**
     * 파트너 정보등록
     */
    @Override
    public List<PartnerInfoIsVO> regiPartnerInfo(List<PartnerInfoIsVO> partnerInfoIsVOList) throws BizException {
        // return commonDao.selectOne("Deal.getDealCnt", deal);

        List<PartnerInfoIsVO> rtnPartnerInfoList = new ArrayList<PartnerInfoIsVO>();

        // commonDao.delete("ADPartnerInfo.deletePartner");

        Boolean errFg = false;

        for (int i = 0; i < partnerInfoIsVOList.size(); i++) {
            errFg = false;
            try {
                int idx = i + 2;

                // 필수값 체크

                partnerInfoIsVOList.get(i).setErrMsg("");
                // partnerId
                if (partnerInfoIsVOList.get(i).getPartnerId().trim().equals("")) {
                    errFg = true;
                    partnerInfoIsVOList.get(i).setErrMsg("(" + idx + ")레코드 아이디가 없습니다.");
                }

                // partnerNm
                if (partnerInfoIsVOList.get(i).getPartnerNm().trim().equals("")) {
                    errFg = true;
                    partnerInfoIsVOList.get(i).setErrMsg(partnerInfoIsVOList.get(i).getErrMsg().equals("") ? "(" + idx + ")파트너 이름이 없습니다." : partnerInfoIsVOList.get(i).getErrMsg() + "<br/>(" + idx + ")파트너 이름이 없습니다.");
                }

                // PartnerInfoIsVO validationPartnerInfoIsVO = commonDao.selectOne("validationPartner", partnerInfoIsVOList.get(i));
                //
                // // errMsg reset
                // partnerInfoIsVOList.get(i).setErrMsg("");
                //
                //
                // // 스킬코드 검증
                // if(!validationPartnerInfoIsVO.getTbSkillOk().equals("TB_SKILL_OK") ){
                // errFg = true;
                // partnerInfoIsVOList.get(i).setErrMsg("(" + partnerInfoIsVOList.get(i).getSkillCode() + ")스킬 코드가 유효하지 않습니다.");
                // }
                //
                // if(!partnerInfoIsVOList.get(i).getIsPersonId().equals("")){
                // if(!validationPartnerInfoIsVO.getTbUserOk().equals("TB_USER_OK")){
                // errFg = true;
                // if(partnerInfoIsVOList.get(i).getErrMsg().equals("")){
                // partnerInfoIsVOList.get(i).setErrMsg("(" + partnerInfoIsVOList.get(i).getIsPersonId() + ")상담원 정보가 유효하지 않습니다.");
                // }else{
                // partnerInfoIsVOList.get(i).setErrMsg(partnerInfoIsVOList.get(i).getErrMsg() + "<br/>(" + partnerInfoIsVOList.get(i).getIsPersonId() + ")상담원 정보가 유효하지 않습니다.");
                // }
                // }
                // }
                //
                if (errFg) {
                    rtnPartnerInfoList.add(partnerInfoIsVOList.get(i));
                } else {
                    commonDao.insert("ADPartnerInfo.insertPartner", partnerInfoIsVOList.get(i));
                }

            } catch (Exception e) {
                e.printStackTrace();
                String errMsg = e.getMessage();
                errMsg = errMsg.substring((errMsg.lastIndexOf("Exception")));
                partnerInfoIsVOList.get(i).setErrMsg(errMsg);
                rtnPartnerInfoList.add(partnerInfoIsVOList.get(i));
                // throw new BizException("-3000",e.getMessage());
            }
        }

        return rtnPartnerInfoList;
    }

    @Override
    public List<PartnerInfoIsVO> selectPartnerInfo(PartnerInfoIsVO partnerInfoIsVO) throws BizException {
        return commonDao.selectList("ADPartnerInfo.selectPartner", partnerInfoIsVO);
    }

    @Override
    public void deletePartner(String partnerId) throws BizException {

        if (!partnerId.equals("")) {
            partnerId = partnerId.substring(0, partnerId.lastIndexOf("^"));
        }

        PartnerInfoIsVO partnerInfoIsVO = new PartnerInfoIsVO();
        String[] arrPartnerId = partnerId.split("\\^");
        for (int i = 0; i < arrPartnerId.length; i++) {
            partnerInfoIsVO.setPartnerId(arrPartnerId[i]);
            commonDao.delete("ADPartnerInfo.deletePartner", partnerInfoIsVO);
        }

    }

    /**
     * 성공사례 정보등록
     */
    @Override
    public List<SuccessExamIsVO> regiSuccessExam(List<SuccessExamIsVO> successExamIsVOList) throws BizException {
        List<SuccessExamIsVO> rtnPartnerInfoList = new ArrayList<SuccessExamIsVO>();

        commonDao.delete("ADSuccessExam.deleteSuccessExam");

        for (int i = 0; i < successExamIsVOList.size(); i++) {
            try {
                commonDao.insert("ADSuccessExam.insertSuccessExam", successExamIsVOList.get(i));
            } catch (Exception e) {
                // e.printStackTrace();
                String errMsg = e.getMessage();
                errMsg = errMsg.substring((errMsg.lastIndexOf("Exception")));
                successExamIsVOList.get(i).setErrMsg(errMsg);
                rtnPartnerInfoList.add(successExamIsVOList.get(i));
            }
        }
        return rtnPartnerInfoList;
    }

    @Override
    public List<SuccessExamIsVO> selectSuccessExam(SuccessExamIsVO successExamIsVO) throws BizException {
        return commonDao.selectList("ADSuccessExam.selectSuccessExam", successExamIsVO);
    }

    /**
     * 기능분류 정보 조회
     *
     * @param SuccessExamIsVO
     * @return
     * @throws BizException
     */
    @Override
    public List<ADFuncClassVO> getfuncClassList(ADFuncClassVO funcClassVO) throws BizException {
        return commonDao.selectList("GroupAuth.getfuncClassList", funcClassVO);
    }

    @Override
    public int getfuncClassListCnt(ADFuncClassVO funcClassVO) throws BizException {
        return commonDao.selectOne("GroupAuth.getfuncClassListCnt", funcClassVO);
    }

    @Override
    public List<ADFuncClassVO> getCurrFuncByAuth(ADFuncClassVO fncClassVO) throws BizException {
        return commonDao.selectList("GroupAuth.getCurrFuncByAuth", fncClassVO);
    }

    @Override
    public void saveFuncByGroup(String funcCode, String funcByGroup, String regiUserId, String userDeptCode) throws BizException {
        funcCode = funcCode.substring(0, funcCode.lastIndexOf("^"));

        if (!funcByGroup.equals("")) {
            funcByGroup = funcByGroup.substring(0, funcByGroup.lastIndexOf("^"));
        }

        String[] arrFuncCode = funcCode.split("\\^");
        String[] arrFuncByGroup = funcByGroup.trim().split("\\^");


        for (int i = 0; i < arrFuncCode.length; i++) {
            Map<Object, Object> deleteMap = new HashMap<>();

            String[] tempFuncCode = arrFuncCode[i].split("\\|");

            deleteMap.put("funcCode", tempFuncCode[0]);
            deleteMap.put("userDeptCode", tempFuncCode[1]);
            deleteMap.put("regiUserId", regiUserId);

            commonDao.update("GroupAuth.deleteFuncByGroup", deleteMap);

            for (int j = 0; j < arrFuncByGroup.length; j++) {

                if (!arrFuncByGroup[j].equals("")) {
                    Map<Object, Object> insertMap = new HashMap<>();

                    insertMap.put("funcCode", tempFuncCode[0]);
                    insertMap.put("userDeptCode", tempFuncCode[1]);
                    insertMap.put("funcByGroup", arrFuncByGroup[j]);
                    insertMap.put("regiUserId", regiUserId);

                    commonDao.insert("GroupAuth.insertFuncByGroup", insertMap);

                }

            }
        }


        ADUserAuthListVO userAuthVO = commonDao.selectOne("GroupAuth.getFromToDate");


        for (int i = 0; i < arrFuncCode.length; i++) {
            String[] tempFuncCode = arrFuncCode[i].split("\\|");
            userAuthVO.setFuncAuthCode(tempFuncCode[0]);
            userAuthVO.setFrstRegrId(regiUserId);
            userAuthVO.setLastCortId(regiUserId);
            userAuthVO.setUserDeptCode(userDeptCode);
            commonDao.update("GroupAuth.updateUserAuthValidToDt", userAuthVO);
            commonDao.insert("GroupAuth.insertUserAuthValidToDt", userAuthVO);


        }

    }

    @Override
    public void saveUserAuth(String regiUser, String userDeptCode) throws BizException {

        ADUserAuthListVO userAuthVO = commonDao.selectOne("GroupAuth.getFromToDate");

        userAuthVO.setFrstRegrId(regiUser);
        userAuthVO.setLastCortId(regiUser);
        userAuthVO.setUserDeptCode(userDeptCode);
        commonDao.update("GroupAuth.updateUserAuthValidToDt", userAuthVO);
        commonDao.insert("GroupAuth.insertUserAuthValidToDt", userAuthVO);

    }

    @Override
    public void saveUserAuth(String regiUser, String userId, String userDeptCode) throws BizException {

        ADUserAuthListVO userAuthVO = commonDao.selectOne("GroupAuth.getFromToDate");

        userAuthVO.setUserId(userId);

        userAuthVO.setFrstRegrId(regiUser);
        userAuthVO.setLastCortId(regiUser);
        userAuthVO.setUserDeptCode(userDeptCode);

        commonDao.update("GroupAuth.updateUserAuthValidToDt", userAuthVO);
        commonDao.insert("GroupAuth.insertUserAuthValidToDt", userAuthVO);

    }

    @Override
    public List<ConsultExileUserVo> getConsultExileInitList() throws BizException {
        return commonDao.selectList("ConsultExile.getConsultExileInitList");
    }

    @Override
    public List<ConsultExileUserVo> getCurrConsultExileInitList(ConsultExileUserVo consultExileUserVo) throws BizException {
        return commonDao.selectList("ConsultExile.getCurrConsultExileInitList", consultExileUserVo);
    }

    @Override
    public List<ConsultExileUserVo> getConsultCateList() throws BizException {
        return commonDao.selectList("ConsultExile.getConsultCateList");
    }

    @Override
    public void saveConsultExileUser(String userInfo, String targetExile, String regiUserId) throws BizException {

        userInfo = userInfo.substring(0, userInfo.lastIndexOf("^"));

        if (!targetExile.equals("")) {
            targetExile = targetExile.substring(0, targetExile.lastIndexOf("^"));
        }

        String[] arrUserInfo = userInfo.split("\\^");
        String[] arrTargetExile = targetExile.trim().split("\\^");

        for (int i = 0; i < arrUserInfo.length; i++) {
            Map<Object, Object> userInfoMap = new HashMap<>();

            String[] tmpUserInfo = arrUserInfo[i].split("\\|\\|");
            userInfoMap.put("userDeptCode", tmpUserInfo[0]);
            userInfoMap.put("userId", tmpUserInfo[1]);
            userInfoMap.put("regiUserId", regiUserId);

            commonDao.delete("ConsultExile.deleteConsultExileUser", userInfoMap);

            for (int j = 0; j < arrTargetExile.length; j++) {

                if (!arrTargetExile[j].equals("")) {
                    Map<String, String> insertMap = new HashMap<>();

                    ConsultExileUserVo consultExileUserVo = new ConsultExileUserVo();

                    String[] tmpTargetExile = arrTargetExile[j].split("\\|\\|");

                    consultExileUserVo.setUserDeptCode(tmpUserInfo[0]);
                    consultExileUserVo.setUserId(tmpUserInfo[1]);
                    consultExileUserVo.setConsultCateSno(tmpTargetExile[0]);
                    consultExileUserVo.setConsultExileCode(tmpTargetExile[1]);
                    consultExileUserVo.setVipYn(tmpTargetExile[2]);
                    consultExileUserVo.setFrstRegrId(regiUserId);
                    consultExileUserVo.setLastCortId(regiUserId);

                    commonDao.clearCache();
                    commonDao.insert("ConsultExile.insertConsultExileUser", consultExileUserVo);

                    insertMap.clear();
                }

            }

        }
    }

    @Override
    public void saveConsultUserSkill(String userInfo, String targetUserSkill, String regiUserId) throws BizException {

        userInfo = userInfo.substring(0, userInfo.lastIndexOf("^"));

        if (!targetUserSkill.equals("")) {
            targetUserSkill = targetUserSkill.substring(0, targetUserSkill.lastIndexOf("^"));
        }

        String[] arrUserInfo = userInfo.split("\\^");
        String[] arrTargetUserSkill = targetUserSkill.trim().split("\\^");

        for (int i = 0; i < arrUserInfo.length; i++) {
            Map<Object, Object> userInfoMap = new HashMap<>();

            String[] tmpUserInfo = arrUserInfo[i].split("\\|\\|");
            userInfoMap.put("userDeptCode", tmpUserInfo[0]);
            userInfoMap.put("userId", tmpUserInfo[1]);

            commonDao.delete("UserSkill.deleteUserSkill", userInfoMap);

            for (int j = 0; j < arrTargetUserSkill.length; j++) {

                if (!arrTargetUserSkill[j].equals("")) {
                    Map<String, String> insertMap = new HashMap<>();

                    UserSkillVO userSkillVO = new UserSkillVO();

                    userSkillVO.setUserDeptCode(tmpUserInfo[0]);
                    userSkillVO.setUserId(tmpUserInfo[1]);
                    userSkillVO.setSkillCode(arrTargetUserSkill[j]);

                    userSkillVO.setFrstRegrId(regiUserId);
                    userSkillVO.setLastCortId(regiUserId);

                    commonDao.clearCache();
                    commonDao.insert("UserSkill.insertUserSkill", userSkillVO);

                    insertMap.clear();
                }

            }

        }
    }

    @Override
    public List<UserSkillVO> getSkillList(ADUserListManageVO userConVO) throws BizException {
        return commonDao.selectList("UserSkill.getSkillList", userConVO);
    }

    @Override
    public List<UserSkillVO> getCurrUserSkillList(UserSkillVO userSkillVO) throws BizException {
        return commonDao.selectList("UserSkill.getCurrUserSkillList", userSkillVO);
    }



    @Override
    public void revokeUser(ADUserListManageVO userConVO) throws BizException {
            commonDao.update("Qna.revokeUser", userConVO);
    }





    // 아웃콜 조회
    @Override
    public List<OutCallSearchVO> getCallBackList(OutCallSearchVO outCallSearchVO) throws BizException {
        List<OutCallSearchVO> callBackList = commonDao.selectList("OutCallSearch.getCallBackList", outCallSearchVO);

        return callBackList;
    }

    @Override
    public int getCallBackListCnt(OutCallSearchVO outCallSearchVO) throws BizException {
        return commonDao.selectOne("OutCallSearch.getCallBackListCnt", outCallSearchVO);
    }

    @Override
    public void settingOutCallUser(OutCallSearchVO outCallSearchVO) throws BizException {
        commonDao.insert("OutCallSearch.settingOutCallUser", outCallSearchVO);
    }

    @Override
    public void outCallUserUpdate(OutCallSearchVO outCallSearchVO, String outcallSno) throws BizException {

        if (!outcallSno.equals("")) {
            outcallSno = outcallSno.substring(0, outcallSno.lastIndexOf("^"));
        }

        String[] arrOutcallSno = outcallSno.split("\\^");
        for (int i = 0; i < arrOutcallSno.length; i++) {
            outCallSearchVO.setOutcallSno(arrOutcallSno[i]);
            commonDao.insert("OutCallSearch.outCallUserUpdate", outCallSearchVO);
        }

    }

    @Override
    public int outCallUserBatchUpdateRenounce(OutCallSearchVO outCallSearchVO) throws BizException {
        int rtnCnt = 0;

        for (int i = 0; i < outCallSearchVO.getOutCallCkeck().length; i++) {
            String[] arrParam = outCallSearchVO.getOutCallCkeck()[i].split("\\|");
            outCallSearchVO.setOutboundGbnCode(arrParam[0]);
            outCallSearchVO.setRegTime(arrParam[1]);
            outCallSearchVO.setEventNm(arrParam[2]);
            rtnCnt += commonDao.insert("OutCallSearch.outCallUserUpdateBatchRenounce", outCallSearchVO);
        }
        return rtnCnt;
    }

    @Override
    public int outCallUserBatchUpdateRevoke(OutCallSearchVO outCallSearchVO) throws BizException {
        int rtnCnt = 0;
        for (int i = 0; i < outCallSearchVO.getOutCallCkeck().length; i++) {
            String[] arrParam = outCallSearchVO.getOutCallCkeck()[i].split("\\|");
            outCallSearchVO.setOutboundGbnCode(arrParam[0]);
            outCallSearchVO.setRegTime(arrParam[1]);
            outCallSearchVO.setEventNm(arrParam[2]);
            rtnCnt += commonDao.insert("OutCallSearch.outCallUserBatchUpdateRevoke", outCallSearchVO);
        }
        return rtnCnt;
    }

    @Override
    public void IPCCOutcallAutoAssign(String deptCode, String userId, String inType) throws BizException {
        Map paramMap = new HashMap<>();
        paramMap.put("userId", userId);
        paramMap.put("deptCode", deptCode);
        paramMap.put("inType", inType);

        commonDao.selectOne("OutCallSearch.IPCCOutcallAutoAssign", paramMap);

    }

    @Override
    public List<OutCallSearchVO> getOutCallList(OutCallSearchVO outCallSearchVO) throws BizException {
        List<OutCallSearchVO> callBackList = commonDao.selectList("OutCallSearch.getOutCallList", outCallSearchVO);

        return callBackList;
    }

    @Override
    public int getOutCallListCnt(OutCallSearchVO outCallSearchVO) throws BizException {
        return commonDao.selectOne("OutCallSearch.getOutCallListCnt", outCallSearchVO);
    }

    /**
     * OutCall Excel 등록
     */
    @Override
    public Map<Object, Object> regiExcelUpload(List<OutCallSearchVO> outcallUploadList) throws BizException {

        Map<Object, Object> rtnMap = new HashMap<>();

        List<OutCallSearchVO> rtnErrorOutCallVOList = new ArrayList<OutCallSearchVO>();

        List<OutCallSearchVO> rtnSuccessOutCallVOList = new ArrayList<OutCallSearchVO>();

        Boolean errFg = false;

        OutCallSearchVO outCallSearchVO;
        OutCallSearchVO validationOutCallSearchVO;

        int idx = 0;

        for (int i = 0; i < outcallUploadList.size(); i++) {
            errFg = false;
            try {
                idx = i + 2;
                outCallSearchVO = outcallUploadList.get(i);
                outCallSearchVO.setErrMsg("");
                // 스킬코드, 상담원 ID 검증
                validationOutCallSearchVO = commonDao.selectOne("OutCallSearch.validationUploadFile", outCallSearchVO);

                String lineStr = "";
                if (!validationOutCallSearchVO.getTbSkillOk().equals("TB_SKILL_OK")) {
                    errFg = true;
                    if (!outCallSearchVO.getErrMsg().equals("")) {
                        lineStr = "\n\r";
                    } else {
                        lineStr = "";
                    }
                    outCallSearchVO.setErrMsg(outCallSearchVO.getErrMsg() + lineStr + "(" + idx + ")스킬정보가 유효 하지 않습니다.");
                    System.out.println("outCallSearchVO.getErrMsg() : " + outCallSearchVO.getErrMsg());
                }

                if (!outCallSearchVO.getCallbackUserId().equals("")) {
                    if (!validationOutCallSearchVO.getTbUserOk().equals("TB_USER_OK")) {
                        if (!outCallSearchVO.getErrMsg().equals("")) {
                            lineStr = "\n";
                        } else {
                            lineStr = "";
                        }
                        errFg = true;
                        outCallSearchVO.setErrMsg(outCallSearchVO.getErrMsg() + lineStr + "(" + idx + ")상담원정보가 유효 하지 않습니다.");
                    }
                }

//                if (outCallSearchVO.getUserDeptCode().equals("IS") && outCallSearchVO.getOutboundGbnCode().equals("08")) {
//                    if (!validationOutCallSearchVO.getTbPartnerInfoIsOk().equals("TB_PARTNER_INFO_IS_OK")) {
//                        if (!outCallSearchVO.getErrMsg().equals("")) {
//                            lineStr = "\n\r";
//                        } else {
//                            lineStr = "";
//                        }
//                        errFg = true;
//                        outCallSearchVO.setErrMsg(outCallSearchVO.getErrMsg() + lineStr + "(" + idx + ")파트너ID가 유효 하지 않습니다.");
//                    }
//                }

                if (!errFg) {
                    rtnSuccessOutCallVOList.add(outCallSearchVO);
                    commonDao.insert("OutCallSearch.insertExcelOutcall", outCallSearchVO);
                } else {
                    rtnErrorOutCallVOList.add(outCallSearchVO);
                }

            } catch (Exception e) {
                e.printStackTrace();
                String errMsg = e.getMessage();
                errMsg = errMsg.substring((errMsg.lastIndexOf("Exception")));
                outcallUploadList.get(i).setErrMsg(outcallUploadList.get(i).getErrMsg() + "\n\r(" + idx + ")" + errMsg);
                rtnErrorOutCallVOList.add(outcallUploadList.get(i));
            }
        }

        rtnMap.put("rtnErrorOutCallVOList", rtnErrorOutCallVOList);
        rtnMap.put("rtnSuccessOutCallVOList", rtnSuccessOutCallVOList);

        return rtnMap;

    }

    /**
     * excelErrLog 등록
     */
    @Override
    public void excelErrLog(List<ExcelulErrLogVO> excelulErrLogVOList) throws BizException {
        for (int i = 0; i < excelulErrLogVOList.size(); i++) {
            commonDao.insert("ErrLog.insertExcelErrLog", excelulErrLogVOList.get(i));
        }
    }

    /*
     * 카테고리대분류조회
     * @auther : HSH
     */
    @Override
    public List<ADCategoryVO> getComboCategoryL() throws BizException {
        List<ADCategoryVO> categoryList = commonDao.selectList("ADCategory.getComboCategoryL");
        return categoryList;
    }

    /*
     * 카테고리소분류조회
     * @auther : HSH
     */
    @Override
    public List<ADCategoryVO> getComboCategoryS(ADCategoryVO adCategoryVO) throws BizException {
        logger.debug("getComboCategoryS : ADCategoryVO = " + adCategoryVO);
        if (StringUtil.nvl(adCategoryVO.getCategoryLCode()).equals("")) {
            logger.debug("getComboCategoryS ADCategoryVO: " + adCategoryVO);
            return null;
        }
        List<ADCategoryVO> categoryList = commonDao.selectList("ADCategory.getComboCategoryS", adCategoryVO);
        return categoryList;
    }

}
