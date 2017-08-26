package com.cdkj.dzt.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IModelAO;
import com.cdkj.dzt.bo.IClothBO;
import com.cdkj.dzt.bo.ICraftBO;
import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.dto.req.XN620000Req;
import com.cdkj.dzt.dto.req.XN620002Req;
import com.cdkj.dzt.dto.res.XN620013Res;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EInteractCategory;
import com.cdkj.dzt.enums.EInteractType;
import com.cdkj.dzt.enums.EModelType;
import com.cdkj.dzt.enums.EOrderStatus;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Service
public class ModelAOImpl implements IModelAO {

    @Autowired
    private IModelBO modelBO;

    @Autowired
    private IInteractBO interactBO;

    @Autowired
    private ICraftBO craftBO;

    @Autowired
    private IClothBO clothBO;

    @Autowired
    private IOrderBO orderBO;

    @Override
    public String addModel(XN620000Req req) {
        EModelType.getModelTypeMap().containsKey(req.getType());
        if (EModelType.H.getCode().equals(req.getType())) {
            StringValidater.validateNumber(req.getLoss());
            StringValidater.validateAmount(req.getProcessFee());
        }
        if (EModelType.CHENSHAN.getCode().equals(req.getType())) {
            StringValidater.validateAmount(req.getPrice());
        }
        Model data = new Model();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.MODEL
            .getCode());
        data.setCode(code);
        data.setType(req.getType());
        data.setName(req.getName());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setDescription(req.getDescription());
        data.setLoss(StringValidater.toInteger(req.getLoss()));
        data.setProcessFee(StringValidater.toLong(req.getProcessFee()));
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setStatus(EStatus.DRAFT.getCode());
        data.setCreateDatetime(new Date());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        modelBO.saveModel(data);
        return code;
    }

    @Override
    public void dropModel(String code) {
        Model data = modelBO.getModel(code);
        if (!EStatus.DRAFT.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "产品已上过架,不可删除");
        }
        modelBO.dropModel(data);
    }

    @Override
    public void editModel(XN620002Req req) {
        EModelType.getModelTypeMap().containsKey(req.getType());
        Model data = modelBO.getModel(req.getCode());
        if (EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "产品上线中,不可修改");
        }
        if (EModelType.H.getCode().equals(req.getType())) {
            StringValidater.validateNumber(req.getLoss());
            StringValidater.validateAmount(req.getProcessFee());
        }
        if (EModelType.CHENSHAN.getCode().equals(req.getType())) {
            StringValidater.validateAmount(req.getPrice());
        }
        data.setType(req.getType());
        data.setName(req.getName());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setDescription(req.getDescription());
        data.setLoss(StringValidater.toInteger(req.getLoss()));
        data.setProcessFee(StringValidater.toLong(req.getProcessFee()));
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        modelBO.refreshModel(data);
    }

    @Override
    public Paginable<Model> queryModelPage(int start, int limit,
            Model condition, String userId) {
        Paginable<Model> pageList = modelBO.getPaginable(start, limit,
            condition);
        List<Model> modelList = pageList.getList();
        for (Model model : modelList) {
            String isSC = EBoolean.NO.getCode();
            if (StringUtils.isNotBlank(userId)) {
                Long num = interactBO.getTotalCount(EInteractCategory.MODEL,
                    EInteractType.SC, model.getCode(), userId);
                if (num > 0) {
                    isSC = EBoolean.YES.getCode();
                }
            }
            model.setIsSC(isSC);
        }
        return pageList;
    }

    @Override
    public List<Model> queryModelList(Model condition) {
        return modelBO.queryModelList(condition);
    }

    @Override
    public Model getModel(String code) {
        return modelBO.getModel(code);
    }

    @Override
    public void putOn(String code, String location, String orderNo,
            String updater, String remark) {
        Model data = modelBO.getModel(code);
        if (EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该产品已上架");
        }
        long num = craftBO.getGroupTotalCount(code);
        if (num < 11) {
            throw new BizException("xn0000", "有部分工艺类型未上架,请仔细检查");
        }
        long number = clothBO.getTotalCount(code);
        if (number <= 0) {
            throw new BizException("xn0000", "面料未上架");
        }
        modelBO.putOn(data, location, orderNo, updater, remark);
    }

    @Override
    public void putOff(String code, String updater, String remark) {
        Model data = modelBO.getModel(code);
        if (!EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "该产品未上架");
        }
        modelBO.putOff(data, updater, remark);
    }

    @Override
    public XN620013Res getModel(String code, String userId) {
        XN620013Res res = new XN620013Res();
        Model data = modelBO.getModel(code);
        String isSC = EBoolean.NO.getCode();
        String isOrder = EBoolean.NO.getCode();
        if (StringUtils.isNotEmpty(userId)) {
            Long num = interactBO.getTotalCount(EInteractCategory.MODEL,
                EInteractType.SC, code, userId);
            if (num > 0) {
                isSC = EBoolean.YES.getCode();
            }
            Long orderNum = orderBO.getTotalCount(userId, EOrderStatus.RECEIVE);
            if (orderNum > 0) {
                isOrder = EBoolean.YES.getCode();
            }
        }
        res.setModel(data);
        res.setIsSC(isSC);
        res.setIsOrder(isOrder);
        return res;
    }
}
