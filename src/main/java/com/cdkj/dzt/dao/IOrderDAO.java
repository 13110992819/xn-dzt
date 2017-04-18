package com.cdkj.dzt.dao;

import com.cdkj.dzt.dao.base.IBaseDAO;
import com.cdkj.dzt.domain.Order;

/**
 * @author: xieyj 
 * @since: 2016年5月24日 下午9:03:38 
 * @history:
 */
public interface IOrderDAO extends IBaseDAO<Order> {
    String NAMESPACE = IOrderDAO.class.getName().concat(".");

    public int assignedOrder(Order data);

    public int cancelOrder(Order data);

    public int updatePaySuccess(Order data);

    public int updateConfirm(Order data);

    public int updatePayGroup(Order data);

    public int ltSubmit(Order data);

    public int approveOrder(Order data);

    public int submitProudect(Order data);

    public int sendGoods(Order data);

    public int confirmReceipt(Order data);

    public int inputInfor(Order data);

}
