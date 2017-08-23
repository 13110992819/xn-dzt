package com.cdkj.dzt.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.ICraftAO;
import com.cdkj.dzt.bo.ICraftBO;
import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.dto.req.XN620040Req;
import com.cdkj.dzt.dto.req.XN620042Req;
import com.cdkj.dzt.dto.res.XN620053Res;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EInteractCategory;
import com.cdkj.dzt.enums.EInteractType;
import com.cdkj.dzt.enums.EOrderStatus;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Service
public class CraftAOImpl implements ICraftAO {

    @Autowired
    private ICraftBO craftBO;

    @Autowired
    private IModelBO modelBO;

    @Autowired
    private IInteractBO interactBO;

    @Autowired
    private IOrderBO orderBO;

    @Override
    public String addCraft(XN620040Req req) {
        modelBO.getModel(req.getModelCode());
        Craft data = new Craft();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.CRAFT
            .getCode());
        data.setCode(code);
        data.setType(req.getType());
        data.setName(req.getName());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setStatus(EStatus.DRAFT.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setModelCode(req.getModelCode());
        craftBO.saveCraft(data);
        return code;
    }

    @Override
    public void dropCraft(String code) {
        Craft data = craftBO.getCraft(code);
        if (!EStatus.DRAFT.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "工艺以上过架,不可删除");
        }
        craftBO.dropCraft(data);
    }

    @Override
    public void editCraft(XN620042Req req) {
        Craft data = craftBO.getCraft(req.getCode());
        modelBO.getModel(req.getModelCode());
        if (EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "工艺正在上架,不可修改");
        }
        data.setType(req.getType());
        data.setName(req.getName());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setModelCode(req.getModelCode());
        craftBO.refreshCraft(data);
    }

    @Override
    public Paginable<Craft> queryCraftPage(int start, int limit, Craft condition) {
        return craftBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Craft> queryCraftList(Craft condition) {
        return craftBO.queryCraftList(condition);
    }

    @Override
    public Map<String, List<Craft>> queryMapCraftList(Craft condition) {
        List<Craft> list = craftBO.queryCraftList(condition);
        Map<String, List<Craft>> map = new HashMap<String, List<Craft>>();
        if (CollectionUtils.isNotEmpty(list)) {
            List<Craft> craftList = null;
            for (Craft craft : list) {
                craftList = map.get(craft.getType());
                if (CollectionUtils.isEmpty(craftList)) {
                    craftList = new ArrayList<Craft>();
                    craftList.add(craft);
                    map.put(craft.getType(), craftList);
                } else {
                    craftList.add(craft);
                }
            }
        }
        return map;
    }

    @Override
    public Craft getCraft(String code) {
        return craftBO.getCraft(code);
    }

    @Override
    public XN620053Res getCraft(String code, String userId) {
        XN620053Res res = new XN620053Res();
        Craft craft = craftBO.getCraft(code);
        String isSC = EBoolean.NO.getCode();
        String isOrder = EBoolean.NO.getCode();
        if (StringUtils.isNotBlank(userId)) {
            Long num = interactBO.getTotalCount(EInteractCategory.CRAFT,
                EInteractType.SC, code, userId);
            if (num > 0) {
                isSC = EBoolean.YES.getCode();
            }
            Long orderNum = orderBO.getTotalCount(userId, EOrderStatus.RECEIVE);
            if (orderNum > 0) {
                isOrder = EBoolean.YES.getCode();
            }
        }
        res.setCraft(craft);
        res.setIsSC(isSC);
        res.setIsOrder(isOrder);
        return res;
    }

    @Override
    public void putOn(String code, String location, String orderNo,
            String updater, String remark) {
        Craft craft = craftBO.getCraft(code);
        if (EStatus.PUT_ON.getCode().equals(craft.getStatus())) {
            throw new BizException("xn0000", "工艺已上架,无需重复上架");
        }
        craftBO.putOn(craft, location, orderNo, updater, remark);
    }

    @Override
    public void putOff(String code, String updater, String remark) {
        Craft craft = craftBO.getCraft(code);
        if (!EStatus.PUT_ON.getCode().equals(craft.getStatus())) {
            throw new BizException("xn0000", "工艺未上架,不可下架");
        }
        craftBO.putOff(craft, updater, remark);
    }
}
