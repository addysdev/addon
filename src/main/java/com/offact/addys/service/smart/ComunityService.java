/**
 *
 */
package com.offact.addys.service.smart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.common.CommentVO;
import com.offact.addys.vo.smart.ComunityVO;

/**
 * @author
 */
public interface ComunityService {
    /**
     * 커뮤니티 목록 조회
     * 
     * @return
     * @throws BizException
     */
    public List<ComunityVO> getComunityList(ComunityVO comunity) throws BizException;

    /**
     * 커뮤니티 전체 갯수
     * 
     * @return
     * @throws BizException
     */
    public int getComunityCnt(ComunityVO comunity) throws BizException;

    /**
     * 커뮤니티상세조회
     * 
     * @return
     * @throws BizException
     */
    public List<ComunityVO> getComunityReply(ComunityVO comunity) throws BizException;
    
    /**
     * 커뮤니티 처리
     * 
     * @return
     * @throws BizException
     */
    public int regiReplyInsert(ComunityVO comunity) throws BizException;

}
