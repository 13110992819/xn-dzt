/**
 * @Title OrderBOImpl.java 
 * @Package com.xnjr.mall.bo.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年5月25日 上午8:15:46 
 * @version V1.0   
 */
package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductOrderBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.IProductOrderDAO;
import com.cdkj.dzt.domain.ProductOrder;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.exception.BizException;

/** 
 * @author: xieyj 
 * @since: 2016年5月25日 上午8:15:46 
 * @history:
 */
@Component
public class ProductOrderBOImpl extends PaginableBOImpl<ProductOrder> implements
        IProductOrderBO {

    @Autowired
    private IProductOrderDAO productOrderDAO;

    /** 
     * @see com.cdkj.dzt.bo.IProductOrderBO#saveProductOrder()
     */
    @Override
    public String saveProductOrder(String orderCode, String productCode,
            Integer quantity, Long price1, Long price2, Long price3,
            String systemCode) {
        String code = OrderNoGenerater.generateM(EGeneratePrefix.PRODUCT_ORDER
            .getCode());
        ProductOrder data = new ProductOrder();
        data.setCode(code);
        data.setOrderCode(orderCode);
        data.setProductCode(productCode);
        data.setQuantity(quantity);
        data.setPrice1(price1);
        data.setPrice2(price2);
        data.setPrice3(price3);
        data.setSystemCode(systemCode);
        productOrderDAO.insert(data);
        return code;
    }

    /** 
     * @see com.cdkj.dzt.bo.IProductOrderBO#queryProductOrderList(com.cdkj.dzt.domain.ProductOrder)
     */
    @Override
    public List<ProductOrder> queryProductOrderList(ProductOrder condition) {
        return productOrderDAO.selectList(condition);
    }

    /** 
     * @see com.cdkj.dzt.bo.IProductOrderBO#getProductOrder(java.lang.String)
     */
    @Override
    public ProductOrder getProductOrder(String code) {
        ProductOrder data = null;
        if (StringUtils.isNotBlank(code)) {
            ProductOrder condition = new ProductOrder();
            condition.setCode(code);
            data = productOrderDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "发货单型号编号不存在");
            }
        }
        return data;
    }

}
