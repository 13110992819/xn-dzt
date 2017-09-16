package com.cdkj.dzt.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.ICraftAO;
import com.cdkj.dzt.bo.ICraftBO;
import com.cdkj.dzt.bo.IInteractBO;
import com.cdkj.dzt.bo.IModelBO;
import com.cdkj.dzt.bo.IModelSpecsBO;
import com.cdkj.dzt.bo.IOrderBO;
import com.cdkj.dzt.bo.IProductCategoryBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.core.StringValidater;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.domain.ProductCategory;
import com.cdkj.dzt.dto.req.XN620040Req;
import com.cdkj.dzt.dto.req.XN620042Req;
import com.cdkj.dzt.dto.res.XN620053Res;
import com.cdkj.dzt.dto.res.XN620054Res;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.enums.EDictType;
import com.cdkj.dzt.enums.EGeneratePrefix;
import com.cdkj.dzt.enums.EInteractCategory;
import com.cdkj.dzt.enums.EInteractType;
import com.cdkj.dzt.enums.EStatus;
import com.cdkj.dzt.exception.BizException;

@Service
public class CraftAOImpl implements ICraftAO {

    @Autowired
    private ICraftBO craftBO;

    @Autowired
    private IModelBO modelBO;

    @Autowired
    private IModelSpecsBO modelSpecsBO;

    @Autowired
    private IInteractBO interactBO;

    @Autowired
    private IProductCategoryBO productCategoryBO;

    @Autowired
    private IOrderBO orderBO;

    @Override
    public String addCraft(XN620040Req req) {
        ModelSpecs modelSpecs = modelSpecsBO.getModelSpecs(req
            .getModelSpecsCode());
        Craft data = new Craft();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.CRAFT
            .getCode());
        data.setCode(code);
        data.setType(req.getType());
        data.setName(req.getName());
        data.setPic(req.getPic());
        data.setSelected(req.getSelected());
        data.setPrice(StringValidater.toLong(EBoolean.NO.getCode()));
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setIsHit(req.getIsHit());
        data.setStatus(EStatus.DRAFT.getCode());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setModelSpecsCode(req.getModelSpecsCode());
        data.setModelCode(modelSpecs.getModelCode());
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
        ModelSpecs modelSpecs = modelSpecsBO.getModelSpecs(req
            .getModelSpecsCode());
        if (EStatus.PUT_ON.getCode().equals(data.getStatus())) {
            throw new BizException("xn0000", "工艺正在上架,不可修改");
        }
        data.setType(req.getType());
        data.setName(req.getName());
        data.setPic(req.getPic());
        data.setSelected(req.getSelected());
        data.setPrice(StringValidater.toLong(EBoolean.NO.getCode()));
        data.setPrice(StringValidater.toLong(req.getPrice()));
        data.setIsHit(req.getIsHit());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setModelSpecsCode(req.getModelSpecsCode());
        data.setModelCode(modelSpecs.getModelCode());
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
    public Craft getCraft(String code) {
        Craft craft = craftBO.getCraft(code);
        List<ProductCategory> productCategoryList = productCategoryBO
            .queryProductCategoryList(null, null, craft.getType(),
                craft.getModelSpecsCode());
        if (CollectionUtils.isNotEmpty(productCategoryList)) {
            craft
                .setProductCategoryName(productCategoryList.get(0).getDvalue());
        }
        return craft;
    }

    @Override
    public XN620053Res getCraft(String code, String userId) {
        XN620053Res res = new XN620053Res();
        Craft craft = craftBO.getCraft(code);
        String isSC = EBoolean.NO.getCode();
        if (StringUtils.isNotBlank(userId)) {
            Long num = interactBO.getTotalCount(EInteractCategory.CRAFT,
                EInteractType.SC, code, userId);
            if (num > 0) {
                isSC = EBoolean.YES.getCode();
            }
        }
        res.setCraft(craft);
        res.setIsSC(isSC);
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

    @Override
    public XN620054Res getCraftList(String modelSpecsCode) {
        XN620054Res res = new XN620054Res();
        ModelSpecs modelSpecs = modelSpecsBO.getModelSpecs(modelSpecsCode);
        List<ProductCategory> productCategoryList = productCategoryBO
            .queryProductCategoryList(EDictType.FIRST.getCode(), null, null,
                modelSpecs.getCode());

        for (ProductCategory productCategory : productCategoryList) {
            List<Craft> craftList = craftBO.queryCraftList(productCategory
                .getDkey());
            productCategory.setCraftList(craftList);
            List<ProductCategory> PCList = productCategoryBO
                .queryProductCategoryList(EDictType.SECOND.getCode(),
                    productCategory.getDkey(), null, modelSpecs.getCode());
            if (CollectionUtils.isNotEmpty(PCList)) {
                for (ProductCategory productCate : PCList) {
                    List<Craft> craftList2 = craftBO.queryCraftList(productCate
                        .getDkey());
                    productCate.setColorCraftList(craftList2);
                }
            }
            productCategory.setColorPcList(PCList);
        }
        List<ProductCategory> pCategoryList = new ArrayList<ProductCategory>();
        List<ProductCategory> pCList = new ArrayList<ProductCategory>();
        for (ProductCategory productCategory : productCategoryList) {
            if (productCategory.getKind().equals("0")) {
                pCategoryList.add(productCategory);
            } else {
                if (productCategory.getKind().equals("1")) {
                    pCList.add(1, productCategory);
                } else if (productCategory.getKind().equals("3")) {
                    pCList.add(0, productCategory);
                } else if (productCategory.getKind().equals("4")) {
                    pCList.add(3, productCategory);
                }
            }
        }
        pCategoryList.addAll(pCList);
        res.setProductCategoryList(pCategoryList);
        return res;
    }
}
