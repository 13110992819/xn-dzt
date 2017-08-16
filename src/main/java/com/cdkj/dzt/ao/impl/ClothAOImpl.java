package com.cdkj.dzt.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IClothAO;
import com.cdkj.dzt.bo.IClothBO;
import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.dto.req.XN620020Req;
import com.cdkj.dzt.dto.req.XN620022Req;
import com.cdkj.dzt.dto.res.XN620033Res;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EInteractCategory;
import com.cdkj.dzt.enums.EInteractType;
import com.cdkj.dzt.enums.EModelType;
import com.cdkj.dzt.enums.EOrderStatus;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Service
public class ClothAOImpl implements IClothAO {

    @Autowired
    private IClothBO clothBO;

    @Autowired
    private IModelBO modelBO;

    @Autowired
    private IOrderBO orderBO;

    @Autowired
    private IInteractBO interactBO;

    @Override
    public String addCloth(XN620020Req req) {
        Model model = modelBO.getModel(req.getModelCode());
        if (EModelType.H.getCode().equals(model.getType())) {
            StringValidater.validateAmount(req.getPrice());
        }
        Cloth data = new Cloth();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.CLOTH
            .getCode());
        data.setCode(code);
        data.setType(req.getType());
        data.setBrand(req.getBrand());
        data.setModelNum(req.getModelNum());
        data.setPic(req.getPic());

        data.setAdvPic(req.getAdvPic());
        data.setColor(req.getColor());
        data.setFlowers(req.getFlowers());
        data.setForm(req.getForm());
        data.setWeight(StringValidater.toInteger(req.getWeight()));
        data.setYarn(req.getYarn());

        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setStatus(EStatus.DRAFT.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setModelCode(req.getModelCode());

        clothBO.saveCloth(data);
        return code;
    }

    @Override
    public void editCloth(XN620022Req req) {
        Cloth data = clothBO.getCloth(req.getCode());
        Model model = modelBO.getModel(req.getModelCode());
        if (EModelType.H.getCode().equals(model.getType())) {
            StringValidater.validateAmount(req.getPrice());
        }
        if (EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该布料已上架,不可修改");
        }
        data.setType(req.getType());
        data.setBrand(req.getBrand());
        data.setModelNum(req.getModelNum());
        data.setPic(req.getPic());

        data.setAdvPic(req.getAdvPic());
        data.setColor(req.getColor());
        data.setFlowers(req.getFlowers());
        data.setForm(req.getForm());
        data.setWeight(StringValidater.toInteger(req.getWeight()));
        data.setYarn(req.getYarn());

        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setModelCode(req.getModelCode());
        clothBO.refreshCloth(data);
    }

    @Override
    public void dropCloth(String code) {
        Cloth data = clothBO.getCloth(code);
        if (!EStatus.DRAFT.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该布料已经上过架,不可删除修改");
        }
        clothBO.removeCloth(data);
    }

    @Override
    public void putOn(String code, String location, String orderNo,
            String updater, String remark) {
        Cloth data = clothBO.getCloth(code);
        if (EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该布料正在上架状态,无需重复上架");
        }
        clothBO.putOn(data, location, orderNo, updater, remark);
    }

    @Override
    public void putOff(String code, String updater, String remark) {
        Cloth data = clothBO.getCloth(code);
        if (!EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该布料未上架,请仔细查看");
        }
        clothBO.putOff(data, updater, remark);
    }

    @Override
    public Paginable<Cloth> queryClothPage(int start, int limit, Cloth condition) {
        return clothBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Cloth> queryClothList(Cloth condition) {
        return clothBO.queryClothList(condition);
    }

    @Override
    public Cloth getCloth(String code) {
        return clothBO.getCloth(code);
    }

    @Override
    public XN620033Res getCloth(String code, String userId) {
        XN620033Res res = new XN620033Res();
        Cloth cloth = clothBO.getCloth(code);
        String isSC = EBoolean.NO.getCode();
        String isOrder = EBoolean.NO.getCode();
        if (StringUtils.isNotBlank(userId)) {
            Long num = interactBO.getTotalCount(EInteractCategory.CLOTH,
                EInteractType.SC, code, userId);
            if (num > 0) {
                isSC = EBoolean.YES.getCode();
            }
            Long orderNum = orderBO.getTotalCount(userId, EOrderStatus.RECEIVE);
            if (orderNum > 0) {
                isOrder = EBoolean.YES.getCode();
            }
        }
        res.setCloth(cloth);
        res.setIsSC(isSC);
        res.setIsOrder(isOrder);
        return res;
    }

}
