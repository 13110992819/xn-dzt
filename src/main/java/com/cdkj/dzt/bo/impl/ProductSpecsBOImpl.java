package com.cdkj.dzt.bo.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IProductSpecsDAO;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.ProductSpecs;
import com.cdkj.dzt.enums.EMeasureKey;

@Component
public class ProductSpecsBOImpl extends PaginableBOImpl<ProductSpecs> implements
        IProductSpecsBO {

    @Autowired
    private IProductSpecsDAO productSpecsDAO;

    @Override
    public void saveProductSpecs(String code, String name, String type,
            String pic, String brand, String modelNum, String advPic,
            String color, String flowers, String form, Integer weight,
            String yarn, Long price, String productCode, String orderCode) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(code);
        data.setName(name);
        data.setType(type);
        data.setPic(pic);
        data.setBrand(brand);

        data.setModelNum(modelNum);
        data.setAdvPic(advPic);
        data.setColor(color);
        data.setFlowers(flowers);
        data.setForm(form);

        data.setWeight(weight);
        data.setYarn(yarn);
        data.setPrice(price);
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    @Override
    public void removeProductSpecs(String productCode) {
        ProductSpecs data = new ProductSpecs();
        data.setProductCode(productCode);
        productSpecsDAO.delete(data);
    }

    @Override
    public void inputInforValue(Order order, Map<String, String> map) {
        String orderCode = order.getCode();
        this.saveSG(orderCode, map.get(EMeasureKey.SG.getCode()));
        this.saveTZ(orderCode, map.get(EMeasureKey.TZ.getCode()));
    }

    private void saveTZ(String orderCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.TZ.getCode());
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSG(String orderCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.SG.getCode());
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    @Override
    public void inputInforValue(Order order, Product product,
            Map<String, String> map) {
        String productCode = product.getCode();
        String orderCode = order.getCode();
        if (StringUtils.isNotBlank(map.get(EMeasureKey.LWCL.getCode()))) {
            this.saveLWCL(orderCode, productCode,
                map.get(EMeasureKey.LWCL.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SWCL.getCode()))) {
            this.saveSWCL(orderCode, productCode,
                map.get(EMeasureKey.SWCL.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.YWCL.getCode()))) {
            this.saveYWCL(orderCode, productCode,
                map.get(EMeasureKey.YWCL.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TWCL.getCode()))) {
            this.saveTWCL(orderCode, productCode,
                map.get(EMeasureKey.TWCL.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.JKCL.getCode()))) {
            this.saveJKCL(orderCode, productCode,
                map.get(EMeasureKey.JKCL.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.YCCL.getCode()))) {
            this.saveYCCL(orderCode, productCode,
                map.get(EMeasureKey.YCCL.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.XCCL.getCode()))) {
            this.saveXCCL(orderCode, productCode,
                map.get(EMeasureKey.XCCL.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BWCL.getCode()))) {
            this.saveBWCL(orderCode, productCode,
                map.get(EMeasureKey.BWCL.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.WWCL.getCode()))) {
            this.saveWWCL(orderCode, productCode,
                map.get(EMeasureKey.WWCL.getCode()));
        }

        if (StringUtils.isNotBlank(map.get(EMeasureKey.LWCY.getCode()))) {
            this.saveLWCY(orderCode, productCode,
                map.get(EMeasureKey.LWCY.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SWCY.getCode()))) {
            this.saveSWCY(orderCode, productCode,
                map.get(EMeasureKey.SWCY.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.YWCY.getCode()))) {
            this.saveYWCY(orderCode, productCode,
                map.get(EMeasureKey.YWCY.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TWCY.getCode()))) {
            this.saveTWCY(orderCode, productCode,
                map.get(EMeasureKey.TWCY.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.JKCY.getCode()))) {
            this.saveJKCY(orderCode, productCode,
                map.get(EMeasureKey.JKCY.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.YCCY.getCode()))) {
            this.saveYCCY(orderCode, productCode,
                map.get(EMeasureKey.YCCY.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.XCCY.getCode()))) {
            this.saveXCCY(orderCode, productCode,
                map.get(EMeasureKey.XCCY.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BWCY.getCode()))) {
            this.saveBWCY(orderCode, productCode,
                map.get(EMeasureKey.BWCY.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.WWCY.getCode()))) {
            this.saveWWCY(orderCode, productCode,
                map.get(EMeasureKey.WWCY.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QXK.getCode()))) {
            this.saveQXK(orderCode, productCode,
                map.get(EMeasureKey.QXK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HBK.getCode()))) {
            this.saveHBK(orderCode, productCode,
                map.get(EMeasureKey.HBK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.ZTW.getCode()))) {
            this.saveZTW(orderCode, productCode,
                map.get(EMeasureKey.ZTW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.GXCX.getCode()))) {
            this.saveGXCX(orderCode, productCode,
                map.get(EMeasureKey.GXCX.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.NL.getCode()))) {
            this.saveNL(orderCode, productCode,
                map.get(EMeasureKey.NL.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.YJDZ.getCode()))) {
            this.saveYJDZ(orderCode, productCode,
                map.get(EMeasureKey.YJDZ.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BEIZHU.getCode()))) {
            this.saveBEIZHU(orderCode, productCode,
                map.get(EMeasureKey.BEIZHU.getCode()));
        }
    }

    private void saveBEIZHU(String orderCode, String productCode, String value) {
        if (StringUtils.isNotBlank(value)) {
            ProductSpecs data = new ProductSpecs();
            data.setCode(value);
            data.setType(EMeasureKey.BEIZHU.getCode());
            data.setProductCode(productCode);
            data.setOrderCode(orderCode);
            productSpecsDAO.insert(data);
        }
    }

    private void saveQXK(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.QXK.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveHBK(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.HBK.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveZTW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.ZTW.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYJDZ(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.YJDZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveNL(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.NL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveWWCY(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.WWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBWCY(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.BWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXCCY(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.XCCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYCCY(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.YCCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveJKCY(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.JKCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTWCY(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.TWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYWCY(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.YWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSWCY(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.SWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveLWCY(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.LWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveWWCL(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.WWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBWCL(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.BWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXCCL(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.XCCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYCCL(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.YCCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveJKCL(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.JKCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTWCL(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.TWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYWCL(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.YWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSWCL(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.SWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveLWCL(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.LWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveGXCX(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.GXCX.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    /*
     * @Override public void inputInforCraft(Order order, Product product,
     * Map<String, String> valueMap, Map<String, Craft> modelSmap) { String
     * productCode = product.getCode(); String orderCode = order.getCode(); if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.CSGG.getCode()))) {
     * this.saveCSGG(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.CSGG.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.CSML.getCode()))) {
     * this.saveCSML(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.CSML.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.LXXZ.getCode()))) {
     * this.saveLXXZ(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.LXXZ.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.XXXZ.getCode()))) {
     * this.saveXXXZ(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.XXXZ.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.MJXZ.getCode()))) {
     * this.saveMZXZ(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.MJXZ.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.XBXZ.getCode()))) {
     * this.saveXBXZ(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.XBXZ.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.SXXZ.getCode()))) {
     * this.saveSXXZ(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.SXXZ.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.LKYZ.getCode()))) {
     * this.saveLKYZ(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.LKYZ.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.KDXZ.getCode()))) {
     * this.saveKDXZ(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.KDXZ.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.NKXZ.getCode()))) {
     * this.saveNKXZ(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.NKXZ.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.NKYS.getCode()))) {
     * this.saveNKYS(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.NKYS.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.TX.getCode()))) {
     * this.saveTX(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.TX.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.JXL.getCode()))) {
     * this.saveJXL(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.JXL.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.FBL.getCode()))) {
     * this.saveFBL(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.FBL.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.BZ.getCode()))) {
     * this.saveBZ(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.BZ.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.BB.getCode()))) {
     * this.saveBB(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.BB.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.CXWZ.getCode()))) {
     * this.saveCXWZ(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.CXWZ.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.CXZT.getCode()))) {
     * this.saveCXZT(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.CXZT.getCode()))); } if
     * (StringUtils.isNotBlank(valueMap.get(EMeasureKey.CXYS.getCode()))) {
     * this.saveCXYS(orderCode, productCode,
     * modelSmap.get(valueMap.get(EMeasureKey.CXYS.getCode()))); } }
     */

    private void saveCXYS(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.CXYS.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveCXZT(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.CXZT.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveCXWZ(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.CXWZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveCSML(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.CSML.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveLXXZ(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.LXXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXXXZ(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.XXXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveMZXZ(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.MJXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXBXZ(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.XBXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSXXZ(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.SXXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveLKYZ(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.LKYZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveKDXZ(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.KDXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveNKXZ(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.NKXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveNKYS(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.NKYS.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTX(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.TX.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveJXL(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.JXL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveFBL(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.FBL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBZ(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.BZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBB(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.BB.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveCSGG(String orderCode, String productCode, Craft modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setPic(modelSpecs.getPic());
        data.setType(EMeasureKey.CSGG.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    @Override
    public List<ProductSpecs> queryProductSpecsList(String productCode) {
        ProductSpecs condition = new ProductSpecs();
        condition.setProductCode(productCode);
        return productSpecsDAO.selectList(condition);
    }

    /*
     * @Override public void inputInforCloth(Order order, Product product,
     * Map<String, String> map, Map<String, Cloth> clothSmap) { }
     */

    @Override
    public List<ProductSpecs> queryPSByOrderCodeList(String orderCode) {
        ProductSpecs condition = new ProductSpecs();
        condition.setOrderCode(orderCode);
        return productSpecsDAO.selectList(condition);
    }

    @Override
    public void refreshProductCode(String orderCode, String productCode) {
        ProductSpecs data = new ProductSpecs();
        data.setOrderCode(orderCode);
        data.setProductCode(productCode);
        productSpecsDAO.update(data);
    }

    @Override
    public void inputInforCloth(Order order, Product product,
            List<Cloth> clothList) {
        for (Cloth cloth : clothList) {
            ProductSpecs data = new ProductSpecs();
            data.setCode(cloth.getCode());
            data.setType(cloth.getType());
            data.setPic(cloth.getPic());
            data.setBrand(cloth.getBrand());

            data.setModelNum(cloth.getModelNum());
            data.setAdvPic(cloth.getAdvPic());
            data.setColor(cloth.getColor());
            data.setFlowers(cloth.getFlowers());
            data.setForm(cloth.getForm());

            data.setWeight(cloth.getWeight());
            data.setYarn(cloth.getYarn());
            data.setPrice(cloth.getPrice());
            data.setProductCode(product.getCode());
            data.setOrderCode(order.getCode());
            productSpecsDAO.insert(data);
        }
    }

    @Override
    public void inputInforCraft(Order order, Product product,
            List<Craft> craftList) {
        for (Craft craft : craftList) {
            ProductSpecs data = new ProductSpecs();
            data.setCode(craft.getCode());
            data.setType(craft.getType());
            data.setName(craft.getName());
            data.setPic(craft.getPic());
            data.setPrice(craft.getPrice());
            data.setProductCode(product.getCode());
            data.setOrderCode(order.getCode());
            productSpecsDAO.insert(data);
        }
    }

}
