package com.xnjr.mall.dto.req;

public class XN808425Req extends APageReq {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = -8602122225038194507L;

    // 从哪个池中拿的钱(选填)
    private String poolCode;

    // 因为哪个分红权(选填)
    private String stockCode;

    // 返还给谁的(选填)
    private String backUser;

    // 所属公司编号(必填)
    private String companyCode;

    // 所属系统编号(必填)
    private String systemCode;

    public String getPoolCode() {
        return poolCode;
    }

    public void setPoolCode(String poolCode) {
        this.poolCode = poolCode;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getBackUser() {
        return backUser;
    }

    public void setBackUser(String backUser) {
        this.backUser = backUser;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

}
