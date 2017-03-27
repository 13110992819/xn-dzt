package com.xnjr.mall.domain;

import com.xnjr.mall.dao.base.ABaseDO;

public class StockPool extends ABaseDO {
    /** 
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
     */
    private static final long serialVersionUID = 4931834449674840979L;

    // 编号
    private String code;

    // 名称
    private String name;

    // 类型(01菜狗对接池；11正汇基金；12消费者基金；13商家基金)
    private String type;

    // 余额
    private Long amount;

    // 从池中已经被使用的金额
    private Long usedAmount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(Long usedAmount) {
        this.usedAmount = usedAmount;
    }

}
