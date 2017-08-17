/**
 * @Title IOrderAO.java 
 * @Package com.cdkj.dzt.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年4月16日 下午2:55:30 
 * @version V1.0   
 */
package com.cdkj.dzt.ao;

import java.util.List;
import java.util.Map;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.dto.req.XN620200Req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年4月16日 下午2:55:30 
 * @history:
 */
public interface IOrderAO {

    public String applyOrder(XN620200Req req);

    public String applyOrder(String applyUser, String productCode);

    public void distributeOrder(String orderCode, String ltUser,
            String updater, String remark);

    public void confirmPrice(String orderCode, String modelCode,
            Integer quantity, String updater, String remark);

    public Object payment(String orderCode, String payType);

    public void paySuccess(String payGroup, String payCode, Long amount);

    public void inputInfor(String orderCode, List<String> codeList,
            Map<String, String> map, String updater, String remark);

    public void ltSubmit(String orderCode, String updater);

    public void approveOrder(String orderCode, String result, String updater,
            String remark);

    public void submitProuduct(String orderCode, String updater, String remark);

    public void sendGoods(String orderCode, String deliverer,
            String deliveryDatetime, String logisticsCompany,
            String logisticsCode, String pdf, String updater, String remark);

    public void confirmReceipt(String orderCode, String updater, String remark);

    public void cancelOrder(String orderCode, String updater, String remark);

    public Paginable<Order> queryOrderPage(int start, int limit, Order condition);

    public Order getRichOrder(String code);

    public List<Order> queryOrderlList(Order condition);

    public Long calculatePrice(List<String> codeList);

    public void confirmPrice(String orderCode, List<String> codeList,
            Integer quantity, String updater, String remark);

}
