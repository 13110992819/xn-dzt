package com.cdkj.dzt.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.ISwapBO;
import com.cdkj.dzt.bo.base.PaginableBOImpl;
import com.cdkj.dzt.core.OrderNoGenerater;
import com.cdkj.dzt.dao.ISwapDAO;
import com.cdkj.dzt.domain.Swap;
import com.cdkj.dzt.enums.EGeneratePrefix;
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
    public String saveSwap(Swap data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generateM(EGeneratePrefix.SWAP.getCode());
            data.setCode(code);
            swapDAO.insert(data);
        }
        return code;
    }

    @Override
    public int removeSwap(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Swap data = new Swap();
            data.setCode(code);
            count = swapDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshSwap(Swap data) {
        int count = 0;
        if (StringUtils.isNotBlank(data.getCode())) {
            count = swapDAO.update(data);
        }
        return count;
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
}
