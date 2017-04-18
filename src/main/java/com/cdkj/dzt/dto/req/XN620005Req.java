/**
 * @Title XN620005Req.java 
 * @Package com.cdkj.dzt.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月14日 下午2:51:51 
 * @version V1.0   
 */
package com.cdkj.dzt.dto.req;


/** 
 * @author: haiqingzheng 
 * @since: 2017年4月14日 下午2:51:51 
 * @history:
 */
public class XN620005Req extends APageReq {

    private static final long serialVersionUID = 6269722921688841847L;

    // 名称（选填）
    private String name;

    // 更新人（选填）
    private String updater;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

}
