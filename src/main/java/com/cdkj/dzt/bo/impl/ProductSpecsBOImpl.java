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
        this.saveSG(orderCode, null, map.get(EMeasureKey.SG.getCode()));
        this.saveTZ(orderCode, null, map.get(EMeasureKey.TZ.getCode()));
    }

    private void saveTZ(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setProductCode(productCode);
        data.setType(EMeasureKey.TZ.getCode());
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSG(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setProductCode(productCode);
        data.setType(EMeasureKey.SG.getCode());
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    @Override
    public void inputInforValue(Order order, Product product,
            Map<String, String> map) {
        String productCode = product.getCode();
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
                map.get(EMeasureKey.TX.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BX.getCode()))) {
            this.saveBX(orderCode, productCode,
                map.get(EMeasureKey.BX.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.ZJ.getCode()))) {
            this.saveZJ(orderCode, productCode,
                map.get(EMeasureKey.ZJ.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.YJ.getCode()))) {
            this.saveYJ(orderCode, productCode,
                map.get(EMeasureKey.YJ.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BZ.getCode()))) {
            this.saveBZ(orderCode, productCode,
                map.get(EMeasureKey.BZ.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.FS.getCode()))) {
            this.saveFS(orderCode, productCode,
                map.get(EMeasureKey.FS.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DX.getCode()))) {
            this.saveDX(orderCode, productCode,
                map.get(EMeasureKey.DX.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SC.getCode()))) {
            this.saveSC(orderCode, productCode,
                map.get(EMeasureKey.SC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SB.getCode()))) {
            this.saveSB(orderCode, productCode,
                map.get(EMeasureKey.SB.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DB.getCode()))) {
            this.saveDB(orderCode, productCode,
                map.get(EMeasureKey.DB.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TUNX.getCode()))) {
            this.saveTUNX(orderCode, productCode,
                map.get(EMeasureKey.TUNX.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.GL.getCode()))) {
            this.saveGL(orderCode, productCode,
                map.get(EMeasureKey.GL.getCode()));
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

    private void saveGL(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.GL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTUNX(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.TUNX.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveDB(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.DB.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSB(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.SB.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSC(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.SC.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveDX(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.DX.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveFS(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.FS.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYJ(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.YJ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveZJ(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.ZJ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveWW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.WW.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveQYC(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.QYC.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXBW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.XBW.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveFW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.FW.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveCXWZ(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.CXWZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBZ(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.BZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBX(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.BX.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
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

    private void saveTX(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.TX.getCode());
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

    private void saveXTW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.XTW.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveKC(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.KC.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveQYG(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.QYG.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveQYJC(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.QYJC.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveHYGH(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.HYGH.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveHYG(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.HYG.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveHYJC(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.HYJC.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveQJK(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.QJK.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXC(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.XC.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveZJK(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.ZJK.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.BW.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTD(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.TD.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveDTW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.DTW.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.TW.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveKYW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.KYW.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveZYW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.ZYW.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.SW.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveLW(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setType(EMeasureKey.LW.getCode());
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

}
