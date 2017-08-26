package com.cdkj.dzt.bo.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.ISYSDictBO;
import com.cdkj.dzt.bo.ISizeDataBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.ISizeDataDAO;
import com.cdkj.dzt.domain.SYSDict;
import com.cdkj.dzt.domain.SizeData;
import com.cdkj.dzt.enums.EMeasureKey;
import com.cdkj.dzt.exception.BizException;

@Component
public class SizeDataBOImpl extends PaginableBOImpl<SizeData> implements
        ISizeDataBO {

    @Autowired
    private ISizeDataDAO sizeDataDAO;

    @Autowired
    private ISYSDictBO sysDictBO;

    @Override
    public boolean isSizeDataExist(String code) {
        SizeData condition = new SizeData();
        condition.setId(code);
        if (sizeDataDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void inputInforValue(String applyUser, Map<String, String> map) {
        Map<String, List<SYSDict>> sysMap = sysDictBO.queryMapSYSDictList(null);

        if (StringUtils.isNotBlank(map.get(EMeasureKey.LW.getCode()))) {
            this.saveLW(applyUser, map.get(EMeasureKey.LW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SW.getCode()))) {
            this.saveSW(applyUser, map.get(EMeasureKey.SW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.ZYW.getCode()))) {
            this.saveZYW(applyUser, map.get(EMeasureKey.ZYW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.KYW.getCode()))) {
            this.saveKYW(applyUser, map.get(EMeasureKey.KYW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TW.getCode()))) {
            this.saveTW(applyUser, map.get(EMeasureKey.TW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DTW.getCode()))) {
            this.saveDTW(applyUser, map.get(EMeasureKey.DTW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TD.getCode()))) {
            this.saveTD(applyUser, map.get(EMeasureKey.TD.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BW.getCode()))) {
            this.saveBW(applyUser, map.get(EMeasureKey.BW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.ZJK.getCode()))) {
            this.saveZJK(applyUser, map.get(EMeasureKey.ZJK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.XC.getCode()))) {
            this.saveXC(applyUser, map.get(EMeasureKey.XC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QJK.getCode()))) {
            this.saveQJK(applyUser, map.get(EMeasureKey.QJK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HYJC.getCode()))) {
            this.saveHYJC(applyUser, map.get(EMeasureKey.HYJC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HYG.getCode()))) {
            this.saveHYG(applyUser, map.get(EMeasureKey.HYG.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HYGH.getCode()))) {
            this.saveHYGH(applyUser, map.get(EMeasureKey.HYGH.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QYJC.getCode()))) {
            this.saveQYJC(applyUser, map.get(EMeasureKey.QYJC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QYG.getCode()))) {
            this.saveQYG(applyUser, map.get(EMeasureKey.QYG.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.KC.getCode()))) {
            this.saveKC(applyUser, map.get(EMeasureKey.KC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.XTW.getCode()))) {
            this.saveXTW(applyUser, map.get(EMeasureKey.XTW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QXK.getCode()))) {
            this.saveQXK(applyUser, map.get(EMeasureKey.QXK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.HBK.getCode()))) {
            this.saveHBK(applyUser, map.get(EMeasureKey.HBK.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.FW.getCode()))) {
            this.saveFW(applyUser, map.get(EMeasureKey.FW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.XBW.getCode()))) {
            this.saveXBW(applyUser, map.get(EMeasureKey.XBW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.QYC.getCode()))) {
            this.saveQYC(applyUser, map.get(EMeasureKey.QYC.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.WW.getCode()))) {
            this.saveWW(applyUser, map.get(EMeasureKey.WW.getCode()));
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TX.getCode()))) {
            this.saveTX(applyUser, map.get(EMeasureKey.TX.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BX.getCode()))) {
            this.saveBX(applyUser, map.get(EMeasureKey.BX.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.ZJ.getCode()))) {
            this.saveZJ(applyUser, map.get(EMeasureKey.ZJ.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.YJ.getCode()))) {
            this.saveYJ(applyUser, map.get(EMeasureKey.YJ.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.BZ.getCode()))) {
            this.saveBZ(applyUser, map.get(EMeasureKey.BZ.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.FS.getCode()))) {
            this.saveFS(applyUser, map.get(EMeasureKey.FS.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DX.getCode()))) {
            this.saveDX(applyUser, map.get(EMeasureKey.DX.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SC.getCode()))) {
            this.saveSC(applyUser, map.get(EMeasureKey.SC.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SB.getCode()))) {
            this.saveSB(applyUser, map.get(EMeasureKey.SB.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.DB.getCode()))) {
            this.saveDB(applyUser, map.get(EMeasureKey.DB.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TUNX.getCode()))) {
            this.saveTUNX(applyUser, map.get(EMeasureKey.TUNX.getCode()),
                sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.GL.getCode()))) {
            this.saveGL(applyUser, map.get(EMeasureKey.GL.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.SG.getCode()))) {
            this.saveSG(applyUser, map.get(EMeasureKey.SG.getCode()), sysMap);
        }
        if (StringUtils.isNotBlank(map.get(EMeasureKey.TZ.getCode()))) {
            this.saveTZ(applyUser, map.get(EMeasureKey.TZ.getCode()), sysMap);
        }
    }

    private void saveLW(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.LW.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveSW(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.SW.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveZYW(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.ZYW.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveKYW(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.KYW.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveTW(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.TW.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveDTW(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.DTW.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveTD(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.TD.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveBW(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.BW.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveZJK(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.ZJK.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveXC(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.XC.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveQJK(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.QJK.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveHYJC(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.HYJC.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveHYG(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.HYG.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveHYGH(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.HYGH.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveQYJC(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.QYJC.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveQYG(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.QYG.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveKC(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.KC.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveXTW(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.XTW.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveQXK(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.QXK.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveHBK(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.HBK.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveFW(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.FW.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveXBW(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.XBW.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveQYC(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.QYC.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveWW(String applyUser, String value) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.WW.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveTX(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.TX.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.TX.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveBX(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.BX.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.BX.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveZJ(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.ZJ.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.ZJ.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveYJ(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.YJ.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.YJ.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveBZ(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.BZ.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.BZ.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveFS(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.FS.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.FS.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveDX(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.DX.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.DX.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveSC(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.SC.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.SC.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveSB(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.SB.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.SB.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveDB(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.DB.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.DB.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveTUNX(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.TUNX.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.TUNX.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveGL(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.GL.getValue());
        List<SYSDict> sysDictList = sysMap.get(EMeasureKey.GL.getCode());
        for (SYSDict sysDict : sysDictList) {
            if (sysDict.getDkey().equals(value)) {
                data.setCvalue(sysDict.getDvalue());
            }
        }
        sizeDataDAO.insert(data);
    }

    private void saveTZ(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.TZ.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    private void saveSG(String applyUser, String value,
            Map<String, List<SYSDict>> sysMap) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(EMeasureKey.SG.getValue());
        data.setCvalue(value);
        sizeDataDAO.insert(data);
    }

    @Override
    public int removeSizeData(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            SizeData data = new SizeData();
            data.setId(code);
            count = sizeDataDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshSizeData(SizeData data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getId())) {
            count = sizeDataDAO.update(data);
        }
        return count;
    }

    @Override
    public List<SizeData> querySizeDataList(SizeData condition) {
        return sizeDataDAO.selectList(condition);
    }

    @Override
    public SizeData getSizeData(String code) {
        SizeData data = null;
        if (StringUtils.isNotBlank(code)) {
            SizeData condition = new SizeData();
            condition.setId(code);
            data = sizeDataDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public List<SizeData> querySizeDataList(String userId) {
        SizeData condition = new SizeData();
        condition.setUserId(userId);
        return sizeDataDAO.selectList(condition);
    }

    @Override
    public void removeSizeDataByUserId(String applyUser) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        sizeDataDAO.deleteByUserId(data);
    }

    @Override
    public void removeSizeDataByKey(String applyUser, String key) {
        SizeData data = new SizeData();
        data.setUserId(applyUser);
        data.setCkey(key);
        sizeDataDAO.deleteByKey(data);
    }

}
