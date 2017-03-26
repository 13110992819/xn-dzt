package com.xnjr.mall.ao;

import java.util.List;

import com.xnjr.mall.bo.base.Paginable;
import com.xnjr.mall.domain.Order;
import com.xnjr.mall.dto.req.XN808050Req;
import com.xnjr.mall.dto.req.XN808051Req;

/** 
 * @author: xieyj 
 * @since: 2015年8月27日 上午10:39:37 
 * @history:
 */
public interface IOrderAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String commitOrder(XN808050Req req);

    public String commitCartOrder(XN808051Req req);

    public List<String> commitCartOrderZH(List<String> cartCodeList, Order data);

    public String commitCartOrderCG(List<String> cartCodeList, Order data);

    public Object toPayOrder(String code, String payType);

    public Object toPayMixOrder(String code, String payType, String ip);

    public Object toPayMixOrderList(List<String> codeList, String payType,
            String ip, String applyUser);

    public void toPayOrderList(List<String> codeList, String channel);

    public void expedOrder(String code);

    public int cancelOrder(String code, String userId, String applyNote);

    public int cancelOrderOss(String code, String updater, String remark);

    public void confirmOrder(String code, String updater, String remark);

    public void deliverOrder(String code, String logisticsCompany,
            String logisticsCode, String deliverer, String deliveryDatetime,
            String pdf, String updater, String remark);

    public void deliverOrder(String code, String updater, String remark);

    public Paginable<Order> queryOrderPage(int start, int limit, Order condition);

    public List<Order> queryOrderList(Order condition);

    public Order getOrder(String code);

    public void paySuccess(String payCode);

    public void doChangeOrderStatusDaily();

}
