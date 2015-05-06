/**
 * 
 */
package com.offact.addys.service.common;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.common.UserVO;

/**
 * @author 4530
 *
 */
public interface UserService {
	/**
	 * 사용자 조회
	 * @param user
	 * @return
	 * @throws BizException
	 */
	public UserVO getUser(UserVO user) throws BizException;
	
	
	
}
