package com.cdkj.dzt.ao;

import java.util.List;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Order;

/** 
 * @author: xieyj 
 * @since: 2015年8月27日 上午10:39:37 
 * @history:
 */
public interface IOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Object toPayOrder(List<String> codeList, String payType);

    public void userCancel(String code, String userId, String remark);

    public void platCancel(List<String> codeList, String updater, String remark);

    public void deliverXianchang(String code, String updater, String remark);

    public void confirm(String code, String updater, String remark);

    public Paginable<Order> queryOrderPage(int start, int limit, Order condition);

    public Paginable<Order> queryMyOrderPage(int start, int limit,
            Order condition);

    public List<Order> queryOrderList(Order condition);

    public Order getOrder(String code);

    public void paySuccess(String payGroup, String payCode, Long payAmount);

    public void doChangeOrderStatusDaily();

}
