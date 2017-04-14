package com.cdkj.dzt.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.ISmsOutBO;
import com.cdkj.dzt.dto.req.XN805905Req;
import com.cdkj.dzt.http.BizConnecter;
import com.cdkj.dzt.http.JsonUtils;

/** 
 * @author: xieyj 
 * @since: 2016年5月25日 上午8:15:46 
 * @history:
 */
@Component
public class SmsOutBOImpl implements ISmsOutBO {
    static Logger logger = Logger.getLogger(SmsOutBOImpl.class);

    @Override
    public void sentContent(String tokenId, String ownerId, String content) {
        try {
            XN805905Req req = new XN805905Req();
            req.setTokenId(tokenId);
            req.setOwnerId(ownerId);
            req.setContent(content);
            BizConnecter.getBizData("805905", JsonUtils.object2Json(req),
                Object.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
    }
}
