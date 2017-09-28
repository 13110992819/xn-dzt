package com.cdkj.dzt.bo.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IOrderSizeDataBO;
import com.cdkj.dzt.bo.ISYSDictBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IOrderSizeDataDAO;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.OrderSizeData;
import com.cdkj.dzt.domain.SYSDict;
import com.cdkj.dzt.enums.EMeasureKey;
import com.cdkj.dzt.exception.BizException;

@Component
public class OrderSizeDataBOImpl extends PaginableBOImpl<OrderSizeData>
        implements IOrderSizeDataBO {

    @Autowired
    private IOrderSizeDataDAO orderSizeDataDAO;

    @Autowired
    private ISYSDictBO sysDictBO;

    @Override
    public void saveOrderSizeData(OrderSizeData data) {
        orderSizeDataDAO.insert(data);
    }

    @Override
    public void removeOrderSizeData(String type, String orderCode) {
        OrderSizeData condition = new OrderSizeData();
        condition.setCkey(type);
        condition.setOrderCode(orderCode);
        orderSizeDataDAO.delete(condition);
    }

    @Override
    public void inputInforValue(Order order, Map<String, String> map) {
        Map<String, List<SYSDict>> sysMap = sysDictBO.queryMapSYSDictList(null);

        if (StringUtils.isNotBlank(map.get(EMeasureKey.LW.getCode()))) {
            this.saveLW(order, map.get(EMeasureKey.LW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SW.getCode()))) {
            this.saveSW(order, map.get(EMeasureKey.SW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.ZYW.getCode()))) {
            this.saveZYW(order, map.get(EMeasureKey.ZYW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.KYW.getCode()))) {
            this.saveKYW(order, map.get(EMeasureKey.KYW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TW.getCode()))) {
            this.saveTW(order, map.get(EMeasureKey.TW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DTW.getCode()))) {
            this.saveDTW(order, map.get(EMeasureKey.DTW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TD.getCode()))) {
            this.saveTD(order, map.get(EMeasureKey.TD.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BW.getCode()))) {
            this.saveBW(order, map.get(EMeasureKey.BW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.ZJK.getCode()))) {
            this.saveZJK(order, map.get(EMeasureKey.ZJK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.XC.getCode()))) {
            this.saveXC(order, map.get(EMeasureKey.XC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QJK.getCode()))) {
            this.saveQJK(order, map.get(EMeasureKey.QJK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HYJC.getCode()))) {
            this.saveHYJC(order, map.get(EMeasureKey.HYJC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HYG.getCode()))) {
            this.saveHYG(order, map.get(EMeasureKey.HYG.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HYGH.getCode()))) {
            this.saveHYGH(order, map.get(EMeasureKey.HYGH.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QYJC.getCode()))) {
            this.saveQYJC(order, map.get(EMeasureKey.QYJC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QYG.getCode()))) {
            this.saveQYG(order, map.get(EMeasureKey.QYG.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.KC.getCode()))) {
            this.saveKC(order, map.get(EMeasureKey.KC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.XTW.getCode()))) {
            this.saveXTW(order, map.get(EMeasureKey.XTW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QXK.getCode()))) {
            this.saveQXK(order, map.get(EMeasureKey.QXK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HBK.getCode()))) {
            this.saveHBK(order, map.get(EMeasureKey.HBK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.FW.getCode()))) {
            this.saveFW(order, map.get(EMeasureKey.FW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.XBW.getCode()))) {
            this.saveXBW(order, map.get(EMeasureKey.XBW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QYC.getCode()))) {
            this.saveQYC(order, map.get(EMeasureKey.QYC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.WW.getCode()))) {
            this.saveWW(order, map.get(EMeasureKey.WW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DJ.getCode()))) {
            this.saveTX(order, map.get(EMeasureKey.DJ.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BX.getCode()))) {
            this.saveBX(order, map.get(EMeasureKey.BX.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.ZJ.getCode()))) {
            this.saveZJ(order, map.get(EMeasureKey.ZJ.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.YJ.getCode()))) {
            this.saveYJ(order, map.get(EMeasureKey.YJ.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BZ.getCode()))) {
            this.saveBZ(order, map.get(EMeasureKey.BZ.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.FS.getCode()))) {
            this.saveFS(order, map.get(EMeasureKey.FS.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DX.getCode()))) {
            this.saveDX(order, map.get(EMeasureKey.DX.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SC.getCode()))) {
            this.saveSC(order, map.get(EMeasureKey.SC.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SB.getCode()))) {
            this.saveSB(order, map.get(EMeasureKey.SB.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DB.getCode()))) {
            this.saveDB(order, map.get(EMeasureKey.DB.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TUNX.getCode()))) {
            this.saveTUNX(order, map.get(EMeasureKey.TUNX.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.GL.getCode()))) {
            this.saveGL(order, map.get(EMeasureKey.GL.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SG.getCode()))) {
            this.saveSG(order, map.get(EMeasureKey.SG.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TZ.getCode()))) {
            this.saveTZ(order, map.get(EMeasureKey.TZ.getCode()), sysMap);
        }
    }

    private void saveLW(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.LW.getCode());
        data.setCvalue(EMeasureKey.LW.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveSW(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.SW.getCode());
        data.setCvalue(EMeasureKey.SW.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveZYW(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.ZYW.getCode());
        data.setCvalue(EMeasureKey.ZYW.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveKYW(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.KYW.getCode());
        data.setCvalue(EMeasureKey.KYW.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveTW(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.TW.getCode());
        data.setCvalue(EMeasureKey.TW.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveDTW(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.DTW.getCode());
        data.setCvalue(EMeasureKey.DTW.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveTD(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.TD.getCode());
        data.setCvalue(EMeasureKey.TD.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveBW(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.BW.getCode());
        data.setCvalue(EMeasureKey.BW.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveZJK(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.ZJK.getCode());
        data.setCvalue(EMeasureKey.ZJK.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveXC(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.XC.getCode());
        data.setCvalue(EMeasureKey.XC.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveQJK(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.QJK.getCode());
        data.setCvalue(EMeasureKey.QJK.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveHYJC(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.HYJC.getCode());
        data.setCvalue(EMeasureKey.HYJC.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveHYG(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.HYG.getCode());
        data.setCvalue(EMeasureKey.HYG.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveHYGH(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.HYGH.getCode());
        data.setCvalue(EMeasureKey.HYGH.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveQYJC(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.QYJC.getCode());
        data.setCvalue(EMeasureKey.QYJC.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveQYG(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.QYG.getCode());
        data.setCvalue(EMeasureKey.QYG.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveKC(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.KC.getCode());
        data.setCvalue(EMeasureKey.KC.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveXTW(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.XTW.getCode());
        data.setCvalue(EMeasureKey.XTW.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveQXK(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.QXK.getCode());
        data.setCvalue(EMeasureKey.QXK.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveHBK(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.HBK.getCode());
        data.setCvalue(EMeasureKey.HBK.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveFW(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.FW.getCode());
        data.setCvalue(EMeasureKey.FW.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveXBW(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.XBW.getCode());
        data.setCvalue(EMeasureKey.XBW.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveQYC(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.QYC.getCode());
        data.setCvalue(EMeasureKey.QYC.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveWW(Order order, String value) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.WW.getCode());
        data.setCvalue(EMeasureKey.WW.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveTX(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.DJ.getCode());
        data.setCvalue(EMeasureKey.DJ.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.DJ.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveBX(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.BX.getCode());
        data.setCvalue(EMeasureKey.BX.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.BX.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveZJ(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.ZJ.getCode());
        data.setCvalue(EMeasureKey.ZJ.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.ZJ.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveYJ(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.YJ.getCode());
        data.setCvalue(EMeasureKey.YJ.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.YJ.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveBZ(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.BZ.getCode());
        data.setCvalue(EMeasureKey.BZ.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.BZ.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveFS(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.FS.getCode());
        data.setCvalue(EMeasureKey.FS.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.FS.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveDX(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.DX.getCode());
        data.setCvalue(EMeasureKey.DX.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.DX.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveSC(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.SC.getCode());
        data.setCvalue(EMeasureKey.SC.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.SC.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveSB(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.SB.getCode());
        data.setCvalue(EMeasureKey.SB.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.SB.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveDB(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.DB.getCode());
        data.setCvalue(EMeasureKey.DB.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.DB.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveTUNX(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.TUNX.getCode());
        data.setCvalue(EMeasureKey.TUNX.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.TUNX.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveGL(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.GL.getCode());
        data.setCvalue(EMeasureKey.GL.getValue());
        data.setDkey(value);
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.GL.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setDvalue(sysDict.getDvalue());
            }
        }
        orderSizeDataDAO.insert(data);
    }

    private void saveTZ(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.TZ.getCode());
        data.setCvalue(EMeasureKey.TZ.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    private void saveSG(Order order, String value,
            Map<String, List<SYSDict>> sysMap) {
        OrderSizeData data = new OrderSizeData();
        data.setOrderCode(order.getCode());
        data.setCkey(EMeasureKey.SG.getCode());
        data.setCvalue(EMeasureKey.SG.getValue());
        data.setDkey(value);
        orderSizeDataDAO.insert(data);
    }

    @Override
    public void removeOrderSizeData(String code) {
        if (StringUtils.isNotBlank(code)) {
            OrderSizeData data = new OrderSizeData();
            orderSizeDataDAO.delete(data);
        }
    }

    @Override
    public void refreshOrderSizeData(OrderSizeData data) {
        // orderSizeDataDAO.update(data);
    }

    @Override
    public List<OrderSizeData> queryOrderSizeDataList(OrderSizeData condition) {
        return orderSizeDataDAO.selectList(condition);
    }

    @Override
    public OrderSizeData getOrderSizeData(String code) {
        OrderSizeData data = null;
        if (StringUtils.isNotBlank(code)) {
            OrderSizeData condition = new OrderSizeData();
            data = orderSizeDataDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<OrderSizeData> queryOrderSizeDataList(String orderCode) {
        OrderSizeData condition = new OrderSizeData();
        condition.setOrderCode(orderCode);
        return orderSizeDataDAO.selectList(condition);
    }
}
