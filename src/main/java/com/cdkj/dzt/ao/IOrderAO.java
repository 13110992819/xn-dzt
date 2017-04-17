/**
 * @Title IOrderAO.java 
 * @Package com.cdkj.dzt.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月16日 下午2:55:30 
 * @version V1.0   
 */
package com.cdkj.dzt.ao;

import com.cdkj.dzt.dto.req.XN620200Req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年4月16日 下午2:55:30 
 * @history:
 */
public interface IOrderAO {
    public String commitOrder(XN620200Req req);
}
