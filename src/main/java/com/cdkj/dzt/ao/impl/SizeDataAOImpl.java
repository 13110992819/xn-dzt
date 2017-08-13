package com.cdkj.dzt.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.dzt.ao.ISizeDataAO;
import com.cdkj.dzt.bo.ISizeDataBO;
import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.SizeData;
import com.cdkj.dzt.exception.BizException;

@Service
public class SizeDataAOImpl implements ISizeDataAO {

    @Autowired
    private ISizeDataBO sizeDataBO;

    @Override
    public String addSizeData(SizeData data) {
        return sizeDataBO.saveSizeData(data);
    }

    @Override
    public int editSizeData(SizeData data) {
        if (!sizeDataBO.isSizeDataExist(data.getId())) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return sizeDataBO.refreshSizeData(data);
    }

    @Override
    public int dropSizeData(String code) {
        if (!sizeDataBO.isSizeDataExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return sizeDataBO.removeSizeData(code);
    }

    @Override
    public Paginable<SizeData> querySizeDataPage(int start, int limit,
            SizeData condition) {
        return sizeDataBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<SizeData> querySizeDataList(SizeData condition) {
        return sizeDataBO.querySizeDataList(condition);
    }

    @Override
    public SizeData getSizeData(String code) {
        return sizeDataBO.getSizeData(code);
    }
}
