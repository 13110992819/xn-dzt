package com.cdkj.dzt.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.IModelAO;
import com.cdkj.dzt.bo.IClothBO;
import com.cdkj.dzt.bo.ICraftBO;
import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.IModelSpecsBO;
import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.IProductCategoryBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.domain.Model;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.ProductCategory;
import com.cdkj.dzt.dto.req.XN620000Req;
import com.cdkj.dzt.dto.req.XN620002Req;
import com.cdkj.dzt.dto.res.XN620013Res;
import com.cdkj.dzt.dto.res.XN620014Res;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.EDictType;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EInteractCategory;
import com.cdkj.dzt.enums.EInteractType;
import com.cdkj.dzt.enums.EModelType;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Service
public class ModelAOImpl implements IModelAO {

    @Autowired
    private IModelBO modelBO;

    @Autowired
    private IProductCategoryBO productCategoryBO;

    @Autowired
    private IModelSpecsBO modelSpecsBO;

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
        Model data = new Model();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.MODEL
            .getCode());
        data.setCode(code);
        data.setType(req.getType());
        data.setKind(req.getKind());
        data.setName(req.getName());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setDescription(req.getDescription());
        data.setLoss(StringValidater.toDouble(req.getLoss()));
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
        data.setType(req.getType());
        data.setKind(req.getKind());
        data.setName(req.getName());
        data.setPic(req.getPic());
        data.setAdvPic(req.getAdvPic());
        data.setDescription(req.getDescription());
        data.setLoss(StringValidater.toDouble(req.getLoss()));
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
        if (StringUtils.isNotEmpty(userId)) {
            Long num = interactBO.getTotalCount(EInteractCategory.MODEL,
                EInteractType.SC, code, userId);
            if (num > 0) {
                isSC = EBoolean.YES.getCode();
            }

            Order order = new Order();
            order.setApplyUser(userId);
        }
        res.setModel(data);
        res.setIsSC(isSC);
        return res;
    }

    @Override
    public List<Model> queryModelBfrontList(Model condition) {
        List<Model> list = modelBO.queryModelList(condition);
        for (Model model : list) {
            List<ModelSpecs> modelSpecsList = this.getModelB(model.getCode());
            model.setModelSpecsList(modelSpecsList);
        }
        return list;
    }

    @Override
    public List<ModelSpecs> getModelB(String code) {
        List<ModelSpecs> modelSpecsList = modelSpecsBO
            .queryModelSpecsList(code);
        for (ModelSpecs modelSpecs : modelSpecsList) {
            XN620014Res res = new XN620014Res();
            List<ProductCategory> productCategoryList = productCategoryBO
                .queryProductCategoryList(EDictType.FIRST.getCode(), null,
                    modelSpecs.getCode());
            List<Cloth> clothList = clothBO.queryClothList(code);
            for (ProductCategory productCategory : productCategoryList) {
                List<Craft> craftList = craftBO.queryCraftList(productCategory
                    .getDkey());
                productCategory.setCraftList(craftList);
                List<ProductCategory> PCList = productCategoryBO
                    .queryProductCategoryList(EDictType.SECOND.getCode(),
                        productCategory.getDkey(), modelSpecs.getCode());
                if (CollectionUtils.isNotEmpty(PCList)) {
                    for (ProductCategory productCate : PCList) {
                        List<Craft> craftList2 = craftBO
                            .queryCraftList(productCate.getDkey());
                        productCate.setColorCraftList(craftList2);
                    }
                }
                productCategory.setColorPcList(PCList);
            }
            res.setProductCategoryList(productCategoryList);
            res.setClothList(clothList);
            modelSpecs.setRes(res);
        }
        return modelSpecsList;
    }
}
