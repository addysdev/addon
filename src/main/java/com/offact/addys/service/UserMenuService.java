package com.offact.addys.service;

import java.util.List;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.UserMenuVO;
import com.offact.addys.vo.UserVO;

public interface UserMenuService 
{
	/**
	 * 사용자체크
	 * @param userMenuVo
	 * @return
	 * @throws BizException
	 */
	public List<UserMenuVO> getCheckUserValidate(UserMenuVO userMenuVo) throws BizException;
	
	/**
	 * 사용자 메뉴리스트
	 * @param userMenuVo
	 * @return
	 * @throws BizException
	 */
	public List<UserMenuVO> getUserMenuList(UserMenuVO userMenuVo) throws BizException;
	
	/**
	 * 메뉴권한
	 * @param userMenuVo
	 * @return
	 * @throws BizException
	 */
	public List<UserMenuVO> getAuthPerMenu(UserMenuVO userMenuVo) throws BizException;
	
	/**
	 * 메뉴별 기능권한
	 * @param userMenuVo
	 * @return
	 * @throws BizException
	 */
	public List<UserMenuVO> getAuthPerFunction(UserMenuVO userMenuVo) throws BizException;
	
	/**
	 * 수펴권한
	 * @param userMenuVo
	 * @return
	 * @throws BizException
	 */
	public List<UserMenuVO> getAuthPerSuper(UserMenuVO userMenuVo) throws BizException;
}
