package com.xnjr.mall.domain;

import java.util.Date;

import com.xnjr.mall.dao.base.ABaseDO;

/**
* 股份分红记录
* @author: xieyj 
* @since: 2016年12月19日 15:15:55
* @history:
*/
public class StockBack extends ABaseDO {

    private static final long serialVersionUID = 1L;

    // 编号
    private Long id;

    // 从哪个池中拿的钱
    private String poolCode;

    // 从哪个池中拿的钱
    private String poolName;

    // 因为哪个分红权
    private String stockCode;

    // 成本金额
    private Long costAmount;

    // 成本币种
    private String costCurrency;

    // 返还给谁的
    private String backUser;

    // 返还金额
    private Long backAmount;

    // 返还币种
    private String backCurrency;

    // 返还时间
    private Date backDatetime;

    // 所属公司编号
    private String companyCode;

    // 所属系统编号
    private String systemCode;

    // **************************************

    // 返还时间起
    private Date backDatetimeStart;

    // 返还时间止
    private Date backDatetimeEnd;

    public Date getBackDatetimeStart() {
        return backDatetimeStart;
    }

    public void setBackDatetimeStart(Date backDatetimeStart) {
        this.backDatetimeStart = backDatetimeStart;
    }

    public Date getBackDatetimeEnd() {
        return backDatetimeEnd;
    }

    public void setBackDatetimeEnd(Date backDatetimeEnd) {
        this.backDatetimeEnd = backDatetimeEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(Long backAmount) {
        this.backAmount = backAmount;
    }

    public String getBackCurrency() {
        return backCurrency;
    }

    public void setBackCurrency(String backCurrency) {
        this.backCurrency = backCurrency;
    }

    public Date getBackDatetime() {
        return backDatetime;
    }

    public void setBackDatetime(Date backDatetime) {
        this.backDatetime = backDatetime;
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

    public Long getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(Long costAmount) {
        this.costAmount = costAmount;
    }

    public String getCostCurrency() {
        return costCurrency;
    }

    public void setCostCurrency(String costCurrency) {
        this.costCurrency = costCurrency;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

}
