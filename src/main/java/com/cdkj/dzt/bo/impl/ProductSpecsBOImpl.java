package com.cdkj.dzt.bo.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.ISYSDictBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IProductSpecsDAO;
import com.cdkj.dzt.domain.Cloth;
import com.cdkj.dzt.domain.Craft;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.ProductSpecs;
import com.cdkj.dzt.domain.SYSDict;
import com.cdkj.dzt.enums.EMeasureKey;

@Component
public class ProductSpecsBOImpl extends PaginableBOImpl<ProductSpecs> implements
        IProductSpecsBO {

    @Autowired
    private IProductSpecsDAO productSpecsDAO;

    @Autowired
    private ISYSDictBO sysDictBO;

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
        this.saveSG(orderCode, null, map.get(EMeasureKey.SG.getCode()));
        this.saveTZ(orderCode, null, map.get(EMeasureKey.TZ.getCode()));
    }

    private void saveTZ(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setProductCode(productCode);
        data.setType(EMeasureKey.TZ.getCode());
        data.setName(EMeasureKey.TZ.getValue());
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSG(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setProductCode(productCode);
        data.setType(EMeasureKey.SG.getCode());
        data.setName(EMeasureKey.SG.getValue());
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    @Override
    public void inputInforValue(Order order, Product product,
            Map<String, String> map) {
        Map<String, List<SYSDict>> sysMap = sysDictBO.queryMapSYSDictList(null);
        String productCode = null;
        if (null != product) {
            productCode = product.getCode();
        }
        String orderCode = order.getCode();
        if (StringUtils.isNotBlank(map.get(EMeasureKey.LW.getCode()))) {
            this.saveLW(orderCode, productCode,
                map.get(EMeasureKey.LW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SW.getCode()))) {
            this.saveSW(orderCode, productCode,
                map.get(EMeasureKey.SW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.ZYW.getCode()))) {
            this.saveZYW(orderCode, productCode,
                map.get(EMeasureKey.ZYW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.KYW.getCode()))) {
            this.saveKYW(orderCode, productCode,
                map.get(EMeasureKey.KYW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TW.getCode()))) {
            this.saveTW(orderCode, productCode,
                map.get(EMeasureKey.TW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DTW.getCode()))) {
            this.saveDTW(orderCode, productCode,
                map.get(EMeasureKey.DTW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TD.getCode()))) {
            this.saveTD(orderCode, productCode,
                map.get(EMeasureKey.TD.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BW.getCode()))) {
            this.saveBW(orderCode, productCode,
                map.get(EMeasureKey.BW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.ZJK.getCode()))) {
            this.saveZJK(orderCode, productCode,
                map.get(EMeasureKey.ZJK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.XC.getCode()))) {
            this.saveXC(orderCode, productCode,
                map.get(EMeasureKey.XC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QJK.getCode()))) {
            this.saveQJK(orderCode, productCode,
                map.get(EMeasureKey.QJK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HYJC.getCode()))) {
            this.saveHYJC(orderCode, productCode,
                map.get(EMeasureKey.HYJC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HYG.getCode()))) {
            this.saveHYG(orderCode, productCode,
                map.get(EMeasureKey.HYG.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HYGH.getCode()))) {
            this.saveHYGH(orderCode, productCode,
                map.get(EMeasureKey.HYGH.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QYJC.getCode()))) {
            this.saveQYJC(orderCode, productCode,
                map.get(EMeasureKey.QYJC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QYG.getCode()))) {
            this.saveQYG(orderCode, productCode,
                map.get(EMeasureKey.QYG.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.KC.getCode()))) {
            this.saveKC(orderCode, productCode,
                map.get(EMeasureKey.KC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.XTW.getCode()))) {
            this.saveXTW(orderCode, productCode,
                map.get(EMeasureKey.XTW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QXK.getCode()))) {
            this.saveQXK(orderCode, productCode,
                map.get(EMeasureKey.QXK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HBK.getCode()))) {
            this.saveHBK(orderCode, productCode,
                map.get(EMeasureKey.HBK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.FW.getCode()))) {
            this.saveFW(orderCode, productCode,
                map.get(EMeasureKey.FW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.XBW.getCode()))) {
            this.saveXBW(orderCode, productCode,
                map.get(EMeasureKey.XBW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QYC.getCode()))) {
            this.saveQYC(orderCode, productCode,
                map.get(EMeasureKey.QYC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.WW.getCode()))) {
            this.saveWW(orderCode, productCode,
                map.get(EMeasureKey.WW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TX.getCode()))) {
            this.saveTX(orderCode, productCode,
                map.get(EMeasureKey.TX.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BX.getCode()))) {
            this.saveBX(orderCode, productCode,
                map.get(EMeasureKey.BX.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.ZJ.getCode()))) {
            this.saveZJ(orderCode, productCode,
                map.get(EMeasureKey.ZJ.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.YJ.getCode()))) {
            this.saveYJ(orderCode, productCode,
                map.get(EMeasureKey.YJ.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BZ.getCode()))) {
            this.saveBZ(orderCode, productCode,
                map.get(EMeasureKey.BZ.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.FS.getCode()))) {
            this.saveFS(orderCode, productCode,
                map.get(EMeasureKey.FS.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DX.getCode()))) {
            this.saveDX(orderCode, productCode,
                map.get(EMeasureKey.DX.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SC.getCode()))) {
            this.saveSC(orderCode, productCode,
                map.get(EMeasureKey.SC.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SB.getCode()))) {
            this.saveSB(orderCode, productCode,
                map.get(EMeasureKey.SB.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DB.getCode()))) {
            this.saveDB(orderCode, productCode,
                map.get(EMeasureKey.DB.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TUNX.getCode()))) {
            this.saveTUNX(orderCode, productCode,
                map.get(EMeasureKey.TUNX.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.GL.getCode()))) {
            this.saveGL(orderCode, productCode,
                map.get(EMeasureKey.GL.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.GXCX.getCode()))) {
            this.saveGXCX(orderCode, productCode,
                map.get(EMeasureKey.GXCX.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.YJDZ.getCode()))) {
            this.saveYJDZ(orderCode, productCode,
                map.get(EMeasureKey.YJDZ.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BEIZHU.getCode()))) {
            this.saveBEIZHU(orderCode, productCode,
                map.get(EMeasureKey.BEIZHU.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.CXWZ.getCode()))) {
            this.saveCXWZ(orderCode, productCode,
                map.get(EMeasureKey.CXWZ.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SG.getCode()))) {
            this.saveSG(orderCode, productCode,
                map.get(EMeasureKey.SG.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TZ.getCode()))) {
            this.saveTZ(orderCode, productCode,
                map.get(EMeasureKey.TZ.getCode()));
        }
    }

    private void saveGL(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.GL.getCode());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.GL.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setName(sysDict.getDvalue());
            }
        }
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTUNX(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.TUNX.getCode());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.TUNX.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setName(sysDict.getDvalue());
            }
        }
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveDB(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.DB.getCode());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.DB.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setName(sysDict.getDvalue());
            }
        }
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSB(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.SB.getCode());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.SB.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setName(sysDict.getDvalue());
            }
        }
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSC(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.SC.getCode());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.SC.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setName(sysDict.getDvalue());
            }
        }
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveDX(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.DX.getCode());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.DX.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setName(sysDict.getDvalue());
            }
        }
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveFS(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.FS.getCode());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.FS.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setName(sysDict.getDvalue());
            }
        }
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYJ(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.YJ.getCode());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.YJ.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setName(sysDict.getDvalue());
            }
        }
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveZJ(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.ZJ.getCode());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.ZJ.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setName(sysDict.getDvalue());
            }
        }
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveWW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.WW.getCode());
        // data.setName(EMeasureKey.WW.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveQYC(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.QYC.getCode());
        // data.setName(EMeasureKey.QYC.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXBW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.XBW.getCode());
        // data.setName(EMeasureKey.XBW.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveFW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.FW.getCode());
        // data.setName(EMeasureKey.FW.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveCXWZ(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.CXWZ.getCode());
        // data.setName(EMeasureKey.CXWZ.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBZ(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.BZ.getCode());
        // data.setName(EMeasureKey.BZ.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBX(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.BX.getCode());
        // data.setName(EMeasureKey.BX.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBEIZHU(String orderCode, String productCode, String value) {
        if (StringUtils.isNotBlank(value)) {
            ProductSpecs data = new ProductSpecs();
            data.setCode(value);
            data.setType(EMeasureKey.BEIZHU.getCode());
            // data.setName(EMeasureKey.BEIZHU.getValue());
            data.setProductCode(productCode);
            data.setOrderCode(orderCode);
            productSpecsDAO.insert(data);
        }
    }

    private void saveQXK(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.QXK.getCode());
        // data.setName(EMeasureKey.QXK.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveHBK(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.HBK.getCode());
        // data.setName(EMeasureKey.HBK.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTX(String orderCode, String productCode, String value,
            Map<String, List<SYSDict>> sysMap) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.TX.getCode());
        // data.setName(EMeasureKey.TX.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYJDZ(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.YJDZ.getCode());
        // data.setName(EMeasureKey.YJDZ.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXTW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.XTW.getCode());
        // data.setName(EMeasureKey.XTW.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveKC(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.KC.getCode());
        // data.setName(EMeasureKey.KC.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveQYG(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.QYG.getCode());
        // data.setName(EMeasureKey.QYG.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveQYJC(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.QYJC.getCode());
        // data.setName(EMeasureKey.QYJC.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveHYGH(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.HYGH.getCode());
        // data.setName(EMeasureKey.HYGH.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveHYG(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.HYG.getCode());
        // data.setName(EMeasureKey.HYG.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveHYJC(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.HYJC.getCode());
        // data.setName(EMeasureKey.HYJC.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveQJK(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.QJK.getCode());
        // data.setName(EMeasureKey.QJK.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXC(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.XC.getCode());
        // data.setName(EMeasureKey.XC.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveZJK(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.ZJK.getCode());
        // data.setName(EMeasureKey.ZJK.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.BW.getCode());
        // data.setName(EMeasureKey.BW.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTD(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.TD.getCode());
        // data.setName(EMeasureKey.TD.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveDTW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.DTW.getCode());
        // data.setName(EMeasureKey.DTW.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.TW.getCode());
        // data.setName(EMeasureKey.TW.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveKYW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.KYW.getCode());
        // data.setName(EMeasureKey.KYW.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveZYW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.ZYW.getCode());
        // data.setName(EMeasureKey.ZYW.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.SW.getCode());
        // data.setName(EMeasureKey.SW.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveLW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.LW.getCode());
        // data.setName(EMeasureKey.LW.getValue());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveGXCX(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.GXCX.getCode());
        // data.setName(EMeasureKey.GXCX.getValue());
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
            data.setType(EMeasureKey.CSML.getCode());
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

    @Override
    public void removeProductSpecs(String type, String orderCode) {
        ProductSpecs condition = new ProductSpecs();
        condition.setType(type);
        condition.setOrderCode(orderCode);
        productSpecsDAO.deleteProductSpecs(condition);
    }

}
