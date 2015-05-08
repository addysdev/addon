/**
 *
 */
package com.offact.addys.service.common;

import org.json.simple.JSONArray;

import com.offact.framework.exception.BizException;
import com.offact.addys.vo.common.EmailVO;
/**
 * @author 4530
 *
 */
public interface MailService {

	public boolean sendMail(EmailVO mail)  throws BizException;

}
