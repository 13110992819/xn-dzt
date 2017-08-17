package com.cdkj.dzt.dto.req;

import java.util.List;

/**
 * H+价格计算
 * @author: asus 
 * @since: 2017年8月17日 下午8:52:12 
 * @history:
 */
public class XN620204Req {
    // 编号List(必填)
    private List<String> codeList;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }
}
