package com.cdkj.dzt.ao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.cdkj.dzt.bo.base.Paginable;
import com.cdkj.dzt.domain.Swap;

@Component
public interface ISwapAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addSwap(Swap data);

    public int dropSwap(String code);

    public int editSwap(Swap data);

    public Paginable<Swap> querySwapPage(int start, int limit, Swap condition);

    public List<Swap> querySwapList(Swap condition);

    public Swap getSwap(String code);

}
