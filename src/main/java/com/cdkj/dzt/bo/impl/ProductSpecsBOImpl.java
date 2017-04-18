package com.cdkj.dzt.bo.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.IProductSpecsBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.IProductSpecsDAO;
import com.cdkj.dzt.domain.ModelSpecs;
import com.cdkj.dzt.domain.Order;
import com.cdkj.dzt.domain.Product;
import com.cdkj.dzt.domain.ProductSpecs;
import com.cdkj.dzt.enums.EMeasureKey;
import com.cdkj.dzt.exception.BizException;

@Component
public class ProductSpecsBOImpl extends PaginableBOImpl<ProductSpecs> implements
        IProductSpecsBO {

    @Autowired
    private IProductSpecsDAO productSpecsDAO;

    @Override
    public List<ProductSpecs> queryProductSpecsList(ProductSpecs condition) {
        return productSpecsDAO.selectList(condition);
    }

    @Override
    public ProductSpecs getProductSpecs(String code) {
        ProductSpecs data = null;
        if (StringUtils.isNotBlank(code)) {
            ProductSpecs condition = new ProductSpecs();
            condition.setCode(code);
            data = productSpecsDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "�� ��Ų�����");
            }
        }
        return data;
    }

    @Override
    public void removeProductSpecs(String productCode) {
        ProductSpecs data = new ProductSpecs();
        data.setProductCode(productCode);
        productSpecsDAO.delete(data);
    }

    @Override
    public void inputInforValue(Order order, Product product,
            Map<String, String> map) {
        String productCode = product.getCode();
        String orderCode = order.getCode();
        this.saveGXCX(orderCode, productCode,
            map.get(EMeasureKey.GXCX.getCode()));

        this.saveLWCL(orderCode, productCode,
            map.get(EMeasureKey.LWCL.getCode()));
        this.saveSWCL(orderCode, productCode,
            map.get(EMeasureKey.SWCL.getCode()));
        this.saveYWCL(orderCode, productCode,
            map.get(EMeasureKey.YWCL.getCode()));
        this.saveTWCL(orderCode, productCode,
            map.get(EMeasureKey.TWCL.getCode()));

        this.saveJKCL(orderCode, productCode,
            map.get(EMeasureKey.JKCL.getCode()));
        this.saveYCCL(orderCode, productCode,
            map.get(EMeasureKey.YCCL.getCode()));
        this.saveXCCL(orderCode, productCode,
            map.get(EMeasureKey.XCCL.getCode()));
        this.saveBWCL(orderCode, productCode,
            map.get(EMeasureKey.BWCL.getCode()));

        this.saveWWCL(orderCode, productCode,
            map.get(EMeasureKey.WWCL.getCode()));
        this.saveLWCY(orderCode, productCode,
            map.get(EMeasureKey.LWCY.getCode()));
        this.saveSWCY(orderCode, productCode,
            map.get(EMeasureKey.SWCY.getCode()));
        this.saveYWCY(orderCode, productCode,
            map.get(EMeasureKey.YWCY.getCode()));
        this.saveTWCY(orderCode, productCode,
            map.get(EMeasureKey.TWCY.getCode()));

        this.saveJKCY(orderCode, productCode,
            map.get(EMeasureKey.JKCY.getCode()));
        this.saveYCCY(orderCode, productCode,
            map.get(EMeasureKey.YCCY.getCode()));

        this.saveXCCY(orderCode, productCode,
            map.get(EMeasureKey.XCCY.getCode()));
        this.saveBWCY(orderCode, productCode,
            map.get(EMeasureKey.BWCY.getCode()));
        this.saveWWCY(orderCode, productCode,
            map.get(EMeasureKey.WWCY.getCode()));
        this.saveNL(orderCode, productCode, map.get(EMeasureKey.NL.getCode()));
        this.saveSG(orderCode, productCode, map.get(EMeasureKey.SG.getCode()));
        this.saveTZ(orderCode, productCode, map.get(EMeasureKey.TZ.getCode()));

    }

    private void saveTZ(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.TZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSG(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.SG.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveNL(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.NL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveWWCY(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.WWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBWCY(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.BWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXCCY(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.XCCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYCCY(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.YCCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveJKCY(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.JKCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTWCY(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.TWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYWCY(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.YWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSWCY(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.SWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveLWCY(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.LWCY.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveWWCL(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.WWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBWCL(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.BWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXCCL(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.XCCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYCCL(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.YCCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveJKCL(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.JKCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTWCL(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.TWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveYWCL(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.YWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSWCL(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.SWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveLWCL(String orderCode, String productCode, String string) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(string);
        data.setParentCode(EMeasureKey.LWCL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveGXCX(String orderCode, String productCode, String value) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(value);
        data.setParentCode(EMeasureKey.GXCX.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    @Override
    public void inputInforCode(Order order, Product product,
            Map<String, String> valueMap, Map<String, ModelSpecs> modelSmap) {

        String productCode = product.getCode();
        String orderCode = order.getCode();

        this.saveCSGG(orderCode, productCode,
            modelSmap.get(EMeasureKey.CSGG.getCode()));
        this.saveCSML(orderCode, productCode,
            modelSmap.get(EMeasureKey.CSML.getCode()));
        this.saveLXXZ(orderCode, productCode,
            modelSmap.get(EMeasureKey.LXXZ.getCode()));
        this.saveXXXZ(orderCode, productCode,
            modelSmap.get(EMeasureKey.XXXZ.getCode()));
        this.saveMZXZ(orderCode, productCode,
            modelSmap.get(EMeasureKey.MZXZ.getCode()));
        this.saveXBXZ(orderCode, productCode,
            modelSmap.get(EMeasureKey.XBXZ.getCode()));
        this.saveSXXZ(orderCode, productCode,
            modelSmap.get(EMeasureKey.SXXZ.getCode()));
        this.saveLKYZ(orderCode, productCode,
            modelSmap.get(EMeasureKey.LKYZ.getCode()));
        this.saveKDXZ(orderCode, productCode,
            modelSmap.get(EMeasureKey.KDXZ.getCode()));
        this.saveNKXZ(orderCode, productCode,
            modelSmap.get(EMeasureKey.NKXZ.getCode()));
        this.saveNKYS(orderCode, productCode,
            modelSmap.get(EMeasureKey.NKYS.getCode()));
        this.saveTX(orderCode, productCode,
            modelSmap.get(EMeasureKey.TX.getCode()));
        this.saveJXL(orderCode, productCode,
            modelSmap.get(EMeasureKey.JXL.getCode()));
        this.saveFBL(orderCode, productCode,
            modelSmap.get(EMeasureKey.FBL.getCode()));
        this.saveBZ(orderCode, productCode,
            modelSmap.get(EMeasureKey.BZ.getCode()));
        this.saveBB(orderCode, productCode,
            modelSmap.get(EMeasureKey.BB.getCode()));

        this.saveCXWZ(orderCode, productCode,
            modelSmap.get(EMeasureKey.CXWZ.getCode()));
        this.saveCDZT(orderCode, productCode,
            modelSmap.get(EMeasureKey.CDZT.getCode()));
        this.saveCSYS(orderCode, productCode,
            modelSmap.get(EMeasureKey.CSYS.getCode()));
    }

    private void saveCSYS(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        // TODO Auto-generated method stub

    }

    private void saveCDZT(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        // TODO Auto-generated method stub

    }

    private void saveCXWZ(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        // TODO Auto-generated method stub

    }

    private void saveCSML(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.CSML.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveLXXZ(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.LXXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXXXZ(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.XXXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveMZXZ(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.MZXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveXBXZ(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.XBXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveSXXZ(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.SXXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveLKYZ(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.LKYZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveKDXZ(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.KDXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveNKXZ(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.NKXZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveNKYS(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.NKYS.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveTX(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.TX.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveJXL(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.JXL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveFBL(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.FBL.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBZ(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.BZ.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveBB(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.BB.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

    private void saveCSGG(String orderCode, String productCode,
            ModelSpecs modelSpecs) {
        ProductSpecs data = new ProductSpecs();
        data.setCode(modelSpecs.getCode());
        data.setName(modelSpecs.getName());
        data.setType(modelSpecs.getType());
        data.setPic(modelSpecs.getPic());
        data.setParentCode(EMeasureKey.CSGG.getCode());
        data.setProductCode(productCode);
        data.setOrderCode(orderCode);
        productSpecsDAO.insert(data);
    }

}
