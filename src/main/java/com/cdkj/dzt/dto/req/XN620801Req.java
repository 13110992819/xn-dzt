package com.cdkj.dzt.dto.req;

import java.util.List;
import java.util.Map;

/**
 * @author: asus 
 * @since: 2017年8月17日 下午9:59:32 
 * @history:
 */
public class XN620801Req {
    private String modelSpecsCode;

    private String clothCode;

    // 编号List
    private List<String> codeList;

    private Map<String, String> map;

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String getModelSpecsCode() {
        return modelSpecsCode;
    }

    public void setModelSpecsCode(String modelSpecsCode) {
        this.modelSpecsCode = modelSpecsCode;
    }

    public String getClothCode() {
        return clothCode;
    }

    public void setClothCode(String clothCode) {
        this.clothCode = clothCode;
    }

}
