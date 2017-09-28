package com.cdkj.dzt.dto.req;

import java.util.List;

/**
 * 下架面料
 * @author: asus 
 * @since: 2017年8月16日 上午9:38:41 
 * @history:
 */
public class XN620024Req {
    // 编号(必填)
    private List<String> codeList;

    // 更新人(必填)
    private String updater;

    // 备注
    private String remark;

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

}
