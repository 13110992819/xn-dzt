package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.ISwapBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.dao.ISwapDAO;
import com.cdkj.dzt.domain.Swap;
import com.cdkj.dzt.enums.EBoolean;
import com.cdkj.dzt.exception.BizException;

@Component
public class SwapBOImpl extends PaginableBOImpl<Swap> implements ISwapBO {

    @Autowired
    private ISwapDAO swapDAO;

    @Override
    public boolean isSwapExist(String code) {
        Swap condition = new Swap();
        condition.setCode(code);
        if (swapDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void saveSwap(Swap data) {
        swapDAO.insert(data);
    }

    @Override
    public void removeSwap(String code) {
        if (StringUtils.isNotBlank(code)) {
            Swap data = new Swap();
            data.setCode(code);
            swapDAO.delete(data);
        }
    }

    @Override
    public void refreshSwap(Swap data) {
        data.setStatus(EBoolean.YES.getCode());
        swapDAO.update(data);
    }

    @Override
    public List<Swap> querySwapList(Swap condition) {
        return swapDAO.selectList(condition);
    }

    @Override
    public Swap getSwap(String code) {
        Swap data = null;
        if (StringUtils.isNotBlank(code)) {
            Swap condition = new Swap();
            condition.setCode(code);
            data = swapDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "编号不存在");
            }
        }
        return data;
    }

    @Override
    public Long getTotalCount(String lookUser) {
        Swap condition = new Swap();
        condition.setLookUser(lookUser);
        return swapDAO.selectTotalCount(condition);
    }

    @Override
    public List<Swap> queryGroupList(Swap condition) {
        return swapDAO.selectGroupList(condition);
    }

    @Override
    public List<Swap> queryGroupList(Swap condition, int start, int limit) {
        return swapDAO.selectGroupList(condition, start, limit);
    }

    @Override
    public List<Swap> queryBLYList(Swap condition) {
        return swapDAO.selectBLYList(condition);
    }

    @Override
    public List<Swap> queryBLYList(Swap condition, int start, int limit) {
        return swapDAO.selectBLYList(condition, start, limit);
    }
}
